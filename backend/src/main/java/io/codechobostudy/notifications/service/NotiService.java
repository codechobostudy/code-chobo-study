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
    public void relayNoti(String jsonData) {
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
        notiDTO.setUsersDTO(userDTO);
        Noti noti = notiDTO.toDomain(notiDTO);
        return new NotiDTO().toDTO(notiRepository.save(noti));
    }

    public NotiCntDTO saveNotiCnt(MockUserDTO userDTO) {
        MockUser user = userDTO.toDomain(userDTO);
        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setTotalCnt(notiRepository.countByUser(user));
        notiCnt.setBoardCnt(notiRepository.countByUserAndModule(user, "board"));
        notiCnt.setQnaCnt(notiRepository.countByUserAndModule(user, "qna"));
        notiCnt.setUser(user);

        NotiCntDTO notiCntDTO = getNotiCntDTO(userDTO);
        boolean isExistNotiCntDTO = (notiCntDTO != null) ? true : false;
        if (isExistNotiCntDTO) {
            notiCnt.setNotiCntIdx(notiCntDTO.getNotiCntIdx());
            notiCntRepository.saveAndFlush(notiCnt);
        } else {
            notiCntRepository.save(notiCnt);
        }
        return new NotiCntDTO().toDTO(notiCnt);
    }

    public void pushUpdatedData(List<MockUserDTO> watchUserList) {
        for (MockUserDTO userDTO : watchUserList){
            if (!userDTO.getUserId().equals("id_Jinhyun")){
                continue;
            }
            List<NotiDTO> notiDTOList = getNotiDTOList(userDTO);
            notiDTOList = lazilyError_NotiList(notiDTOList);

            NotiCntDTO notiCntDTO = getNotiCntDTO(userDTO);
            notiCntDTO = lazilyError_NotiCnt(notiCntDTO);

            NotiViewDTO notiViewDTO = new NotiViewDTO();
            notiViewDTO.setNotiList(notiDTOList);
            notiViewDTO.setNotiCntDTO(notiCntDTO);

//            this.simpMsgTemplate.convertAndSend("/subscribe/notiData/" + user.getUserId(), notiView);
            simpMsgTemplate.convertAndSend("/subscribe/notiData", notiViewDTO);
        }
    }

    public List<NotiDTO> getNotiDTOList(MockUserDTO userDTO) {
        List<Noti> notiList = notiRepository.findByUser(new MockUserDTO().toDomain(userDTO));
        return new NotiDTO().toDTOList(notiList);
    }

    public NotiCntDTO getNotiCntDTO(MockUserDTO userDTO) {
        NotiCnt notiCnt = notiCntRepository.findByUser(new MockUserDTO().toDomain(userDTO));
        return new NotiCntDTO().toDTO(notiCnt);
    }

    public String getNotiData() throws JsonProcessingException {
        MockUserDTO userDTO = mockUserService.getUser("id_Jinhyun");
        // ... get session UserInfo

        List<NotiDTO> notiDTOList = getNotiDTOList(userDTO);
        notiDTOList = lazilyError_NotiList(notiDTOList);

        NotiCntDTO notiCntDTO = getNotiCntDTO(userDTO);
        notiCntDTO = lazilyError_NotiCnt(notiCntDTO);

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
    public List<NotiDTO> lazilyError_NotiList(List<NotiDTO> notiDTOList) {
        List<NotiDTO> resultNotiDTOList = new ArrayList<>();
        for (NotiDTO notiDTO : notiDTOList){
            notiDTO.setUsersDTO(null);
            resultNotiDTOList.add(notiDTO);
        }
        return resultNotiDTOList;
    }

    // Jpa 오류 해결방안 필요
    public NotiCntDTO lazilyError_NotiCnt(NotiCntDTO notiCntDTO) {
        if (notiCntDTO != null){
            notiCntDTO.setUserDTO(null);
        }
        return notiCntDTO;
    }

    public void deleteAllData() {
        notiRepository.deleteAll();
        notiCntRepository.deleteAll();
    }
}
