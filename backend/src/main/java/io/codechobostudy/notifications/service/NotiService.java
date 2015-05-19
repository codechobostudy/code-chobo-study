package io.codechobostudy.notifications.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.domain.NotiView;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.MockNotiViewRepository;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

@Service
@Transactional
public class NotiService {
    @Autowired
    private MockNotiViewRepository mockNotiViewRepository;

    @Autowired
    private NotiRepository notiRepository;

    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;

    @Autowired
    private MockUserRepository mockUserRepository;

    public NotiView getNotiView() {
        return mockNotiViewRepository.getNotiView();
    }

    public String getNotiViewJsonString() throws IOException {
        NotiView notiView = getNotiView();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(notiView);
    }

    @Autowired
    private MockNotiBuilder notiBuilder;

    // 알림 중계 서비스
    public void relayNoti(String jsonData){
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
        // TODO: temp Methord (not exist jsonData)
        noti = notiBuilder.buildNotiData(4);

        List <MockUser> watchUserList = new ArrayList<>();
        /*
        ... watch 테이블에서 module의 pk가 등록되어있는 사용자 조회
            watchUserList =
         */

        // TODO: temp Method (not exist Watch Table)
        watchUserList = mockUserRepository.findAll();

        List <NotiView> notiViewList = registerNotiUsers(noti, watchUserList);
        pushUpdatedData(watchUserList);
    }

    // 알림관련 정보 저장
    public List <NotiView> registerNotiUsers(Noti noti, List<MockUser> watchUserList) {
        List <NotiView> resultNotiViewList = new ArrayList<>();

        for (MockUser user : watchUserList){
            noti.setUsers(user);            // 데이터에 유저정보를 넣는다.
            notiRepository.save(noti);      // 노티에 저장 / save to noti

            int totalCnt = notiRepository.countByUsers(user);   // 노티에서 읽지않은 카운트 검색 / getNoReadCnt(user) from noti
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
                notiCntRepository.saveAndFlush(notiCnt);    // 노티카운트에 카운트 수정 / updateCnt(user) to notiCnt
            } else {
                notiCntRepository.save(notiCnt);    // 노티카운트에 카운트 수정 / updateCnt(user) to notiCnt
            }

            NotiView notiView = new NotiView();
            notiView.setNotiCnt(notiCnt);
            notiView.setNoti(noti);
            resultNotiViewList.add(notiView);
        }

        return resultNotiViewList;
    }

    public void pushUpdatedData(List<MockUser> watchUserList) {
        for (MockUser user : watchUserList){
            List <Noti> dbNotiList = notiRepository.findByUsers(user);
            NotiCnt dbNotiCnt = notiCntRepository.findByUser(user);

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
            // Temp Solution Start
            List <Noti> notiList = new ArrayList<>();
            for (Noti noti : dbNotiList){
                noti.setUsers(null);
                notiList.add(noti);
            }
            dbNotiCnt.setUser(null);
            // Temp Solution End

            NotiView notiView = new NotiView();
            notiView.setNotiList(dbNotiList);
            notiView.setNotiCnt(dbNotiCnt);

//            this.simpMsgTemplate.convertAndSend("/subscribe/notiData/" + user.getUserId(), notiView);
            simpMsgTemplate.convertAndSend("/subscribe/notiData", notiView);
            System.out.println("Data Check");
        }
    }
}
