package io.codechobostudy.notifications.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.domain.NotiView;
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
        noti = notiBuilder.buildNotiData(4);

        List <MockUser> watchUserList = new ArrayList<>();
        /*
        ... watch 테이블에서 module의 pk가 등록되어있는 사용자 조회
            watchUserList =
         */

        // TODO: temp Method (not exist Watch Table) - watch 구현전까지 등록되어있는 모든 사용자에게 알림처리
        watchUserList = mockUserRepository.findAll();

        List <NotiView> notiViewList = registerNotiUsers(noti, watchUserList);
        pushUpdatedData(watchUserList);
    }

    // 알림관련 정보 저장
    public List <NotiView> registerNotiUsers(Noti paramNoti, List<MockUser> watchUserList) throws CloneNotSupportedException {
        List <NotiView> resultNotiViewList = new ArrayList<>();

        for (MockUser user : watchUserList){
            Noti noti = paramNoti.clone();
            noti.setUsers(user);

            notiRepository.save(noti);

            int totalCnt = notiRepository.countByUsers(user);
            int boardCnt = notiRepository.countByUsersAndModule(user, "board");
            int qnaCnt = notiRepository.countByUsersAndModule(user, "qna");

            NotiCnt dbNotiCnt = notiCntRepository.findByUser(user);
            NotiCnt notiCnt = new NotiCnt();
            if (dbNotiCnt != null) {
                notiCnt.setNotiCntIdx(dbNotiCnt.getNotiCntIdx());
            }
            notiCnt.setTotalCnt(totalCnt);
            notiCnt.setBoardCnt(boardCnt);
            notiCnt.setQnaCnt(qnaCnt);
            notiCnt.setUser(user);

            if (dbNotiCnt != null) {
                notiCntRepository.saveAndFlush(notiCnt);
            } else {
                notiCntRepository.save(notiCnt);
            }

            NotiView notiView = new NotiView();
            notiView.setNotiCnt(notiCnt);
            notiView.setNoti(noti);
            resultNotiViewList.add(notiView);
        }

        return resultNotiViewList;
    }

    public void pushUpdatedData(List<MockUser> watchUserList) throws CloneNotSupportedException {
        for (MockUser user : watchUserList){
            if (!user.getUserId().equals("id_Jinhyun")){
                continue;
            }
            List <Noti> dbNotiList = notiRepository.findByUsers(user);
            NotiCnt dbNotiCnt = notiCntRepository.findByUser(user);

            List<Noti> notiList = lazilyError_NotiList(dbNotiList);
            NotiCnt notiCnt = lazilyError_NotiCnt(dbNotiCnt);

            NotiView notiView = new NotiView();
            notiView.setNotiList(notiList);
            notiView.setNotiCnt(notiCnt);

//            this.simpMsgTemplate.convertAndSend("/subscribe/notiData/" + user.getUserId(), notiView);
            simpMsgTemplate.convertAndSend("/subscribe/notiData", notiView);
        }
    }

    public String getNotiData() throws JsonProcessingException, CloneNotSupportedException {
        MockUser user = mockUserRepository.findByUserId("id_Jinhyun");
        // ... get session UserInfo

        // ... data가 없는 경우

        List<Noti> notiList = lazilyError_NotiList(notiRepository.findByUsers(user));
        NotiCnt notiCnt = lazilyError_NotiCnt(notiCntRepository.findByUser(user));
        notiCnt.setUser(null);

        NotiView notiView = new NotiView();
        notiView.setNotiList(notiList);
        notiView.setNotiCnt(notiCnt);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(notiView);
    }

    /*
    problem message:
    failed to lazily initialize a collection of role
    io.codechobostudy.mock.user.domain.MockUser.notiList, could not initialize proxy
        io.codechobostudy.notifications.domain.NotiView["notiList"]
        io.codechobostudy.notifications.domain.Noti["users"]

    io.codechobostudy.mock.user.domain.MockUser.notiList, could not initialize proxy
        io.codechobostudy.notifications.domain.NotiView["notiCnt"]
        io.codechobostudy.notifications.domain.NotiCnt["user"]
    */
    // Jpa 오류 해결방안 필요
    public List<Noti> lazilyError_NotiList(List<Noti> dbNotiList) throws CloneNotSupportedException {
        List<Noti> notiList = new ArrayList<>();
        for (Noti dbNoti : dbNotiList){
            Noti noti = dbNoti.clone();     // 캐시된 객체를 수정하면 자동으로 업데이트 쿼리 발생
            noti.setUsers(null);
            notiList.add(noti);
        }
        return notiList;
    }

    // Jpa 오류 해결방안 필요
    public NotiCnt lazilyError_NotiCnt(NotiCnt dbNotiCnt) throws CloneNotSupportedException {
        NotiCnt notiCnt = dbNotiCnt.clone();    // 캐시된 객체를 수정하면 자동으로 업데이트 쿼리 발생
        notiCnt.setUser(null);
        return notiCnt;
    }

    public void deleteAllData() {
        notiRepository.deleteAll();
        notiCntRepository.deleteAll();
    }
}
