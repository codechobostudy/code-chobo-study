package io.codechobostudy.notifications.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockNotiService {
    @Autowired
    private MockUserRepository mockUserRepository;
    @Autowired
    private NotiRepository notiRepository;
    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private MockNotiBuilder notiBuilder;
    @Autowired
    private NotiService notiService;

    public void insertInitData_NotiAndNotiCnt() throws CloneNotSupportedException {
        insertInitData_Noti();
        insertInitData_NotiCnt();
    }

    public List<Noti> insertInitData_Noti() throws CloneNotSupportedException {
        List<Noti> notiList = new ArrayList<>();
        MockUser user1 = mockUserRepository.findByUserId(notiBuilder.buildUserData(1).getUserId());
        MockUser user2 = mockUserRepository.findByUserId(notiBuilder.buildUserData(2).getUserId());

        notiList.add(notiService.saveNoti(notiBuilder.buildNotiData(1), user1));
        notiList.add(notiService.saveNoti(notiBuilder.buildNotiData(2), user1));
        notiList.add(notiService.saveNoti(notiBuilder.buildNotiData(3), user1));
        notiList.add(notiService.saveNoti(notiBuilder.buildNotiData(10), user2));

        return notiList;
    }

    public List<NotiCnt> insertInitData_NotiCnt() throws CloneNotSupportedException {
        List<NotiCnt> notiCntList = new ArrayList<>();
        List<MockUser> userList = mockUserRepository.findAll();

        for (MockUser user : userList){
            notiCntList.add(notiService.saveNotiCnt(user));
        }
        return notiCntList;
    }
}
