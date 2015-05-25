package io.codechobostudy.notifications.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.mock.user.service.MockUserService;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.dto.NotiCntDTO;
import io.codechobostudy.notifications.dto.NotiDTO;
import io.codechobostudy.notifications.dto.NotiViewDTO;
import io.codechobostudy.notifications.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotiService {
    @Autowired
    private NotiRepository notiRepository;
    @Autowired
    private NotiCntRepository notiCntRepository;
    @Autowired
    private MockUserRepository mockUserRepository;

    @Autowired
    private MockNotiBuilder notiBuilder;
    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;
    @Autowired
    private MockUserService mockUserService;

    // 알림 중계 서비스
    public void relayNoti(String jsonData) throws CloneNotSupportedException {
        /*
        ... jsonData to relayNoti Domain
            String module
            String pk
         */

        Noti noti = new Noti();
        /*
        ... module의 pk로 데이터 검색후 Noti Object에 저장
            noti =
        */
        // TODO: temp Method (not exist jsonData)
        NotiDTO notiDTO = notiBuilder.buildNotiData(4, "qna");

        /*
        ... watch 테이블에서 module의 pk가 등록되어있는 사용자 조회
            watchUserList =
         */

        // TODO: temp Method (not exist Watch Table) - watch 구현전까지 등록되어있는 모든 사용자에게 알림처리
        List <MockUserDTO> watchUserList = mockUserService.getUserList();

        registerNotiUsers(notiDTO, watchUserList);
        pushUpdatedData(watchUserList);
    }

    // 알림관련 정보 저장
    public List <NotiViewDTO> registerNotiUsers(NotiDTO notiDTO, List<MockUserDTO> watchUserList) {
        List <NotiViewDTO> notiViewDTOList = new ArrayList<>();

        for (MockUserDTO userDTO : watchUserList){
            NotiDTO resultNoti = saveNoti(notiDTO, userDTO);
            NotiCntDTO resultNotiCnt = saveNotiCnt(userDTO);

            notiViewDTOList.add(new NotiViewDTO(resultNoti, resultNotiCnt));
        }
        return notiViewDTOList;
    }

    public NotiDTO saveNoti(NotiDTO notiDTO, MockUserDTO userDTO) {
        Noti noti = notiDTO.toDomain(notiDTO);
        MockUser user = userDTO.toDomain(userDTO);

        noti.setUser(user);
        return new NotiDTO().toDTO(notiRepository.save(noti));
    }

    // TODO: refactor
    public NotiCntDTO saveNotiCnt(MockUserDTO userDTO) {
        MockUser user = userDTO.toDomain(userDTO);
        user.setNotiList(null);

        NotiCnt dbNotiCnt = notiCntRepository.findByUser(user);
        boolean existDbNotiCnt = (dbNotiCnt != null) ? true : false;
        NotiCntDTO notiCntDTO = new NotiCntDTO();

        if (existDbNotiCnt){
            notiCntDTO = notiCntDTO.toDTO(dbNotiCnt);
        }

        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setTotalCnt(notiRepository.countByUser(user));
        notiCnt.setBoardCnt(notiRepository.countByUserAndModule(user, "board"));
        notiCnt.setQnaCnt(notiRepository.countByUserAndModule(user, "qna"));
        notiCnt.setUser(user);

        if (existDbNotiCnt) {
            notiCnt.setNotiCntIdx(notiCntDTO.getNotiCntIdx());
            notiCntRepository.saveAndFlush(notiCnt);
        } else {
            notiCntRepository.save(notiCnt);
        }

        return new NotiCntDTO().toDTO(notiCnt);
    }

    public void pushUpdatedData(List<MockUserDTO> watchUserList) throws CloneNotSupportedException {
        for (MockUserDTO userDTO : watchUserList){
            MockUser user = userDTO.toDomain(userDTO);
            if (!userDTO.getUserId().equals("id_Jinhyun")){
                continue;
            }

            List <Noti> dbNotiList = notiRepository.findByUser(user);
            List <NotiDTO> resultNotiDTOList = new ArrayList<>();
            // TODO: Extract Method
            for (Noti noti : dbNotiList){
                resultNotiDTOList.add(new NotiDTO().toDTO(noti));
            }

            NotiCnt dbNotiCnt = notiCntRepository.findByUser(user);
            // TODO: Extract Method
            NotiCntDTO resultNotiCntDTO = new NotiCntDTO().toDTO(dbNotiCnt);

            List<NotiDTO> notiList = lazilyError_NotiList(resultNotiDTOList);
            NotiCntDTO notiCntDTO = lazilyError_NotiCnt(resultNotiCntDTO);

            NotiViewDTO notiViewDTO = new NotiViewDTO();
            notiViewDTO.setNotiList(notiList);
            notiViewDTO.setNotiCntDTO(notiCntDTO);

//            this.simpMsgTemplate.convertAndSend("/subscribe/notiData/" + user.getUserId(), notiView);
            simpMsgTemplate.convertAndSend("/subscribe/notiData", notiViewDTO);
        }
    }

    public String getNotiData() throws JsonProcessingException, CloneNotSupportedException {
        MockUser user = mockUserRepository.findByUserId("id_Jinhyun");
        // ... get session UserInfo

        // ... data가 없는 경우

        // TODO: Extract Method
        List<Noti> notiList = notiRepository.findByUser(user);
        List<NotiDTO> notiDTOList = lazilyError_NotiList(new NotiDTO().toDTOList(notiList));

        // TODO: Extract Method
        NotiCnt notiCnt = notiCntRepository.findByUser(user);
        NotiCntDTO notiCntDTO = lazilyError_NotiCnt(new NotiCntDTO().toDTO(notiCnt));

        NotiViewDTO notiViewDTO = new NotiViewDTO();
        notiViewDTO.setNotiList(notiDTOList);
        notiViewDTO.setNotiCntDTO(notiCntDTO);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(notiViewDTO);
    }

    /*
    problem message:
    failed to lazily initialize a collection toDomain role
    io.codechobostudy.mock.user.domain.MockUser.notiList, could not initialize proxy
        io.codechobostudy.notifications.dto.NotiView["notiList"]
        io.codechobostudy.notifications.domain.Noti["user"]

    io.codechobostudy.mock.user.domain.MockUser.notiList, could not initialize proxy
        io.codechobostudy.notifications.dto.NotiView["notiCnt"]
        io.codechobostudy.notifications.domain.NotiCnt["user"]
    */
    // Jpa 오류 해결방안 필요
    public List<NotiDTO> lazilyError_NotiList(List<NotiDTO> notiDTOList) throws CloneNotSupportedException {
        List<NotiDTO> resultNotiDTOList = new ArrayList<>();
        for (NotiDTO notiDTO : notiDTOList){
            notiDTO.setUsersDTO(null);      // 캐시된 객체를 수정하면 자동으로 업데이트 쿼리 발생
            resultNotiDTOList.add(notiDTO);
        }
        return resultNotiDTOList;
    }

    // Jpa 오류 해결방안 필요
    public NotiCntDTO lazilyError_NotiCnt(NotiCntDTO notiCntDTO) throws CloneNotSupportedException {
        notiCntDTO.setUserDTO(null);    // 캐시된 객체를 수정하면 자동으로 업데이트 쿼리 발생
        return notiCntDTO;
    }

    public void deleteAllData() {
        notiRepository.deleteAll();
        notiCntRepository.deleteAll();
    }
}
