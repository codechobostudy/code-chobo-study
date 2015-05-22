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

    public void insertInitData_NotiAndNotiCnt() {
        insertInitData_Noti();
        insertInitData_NotiCnt();
    }

    public List<Noti> insertInitData_Noti() {
        List<Noti> notiList = new ArrayList<>();
        MockUser user1 = mockUserRepository.findByUserId(notiBuilder.buildUserData(1).getUserId());
        MockUser user2 = mockUserRepository.findByUserId(notiBuilder.buildUserData(2).getUserId());

        notiList.add(notiRepository.save(notiBuilder.buildNotiData(1, user1)));
        notiList.add(notiRepository.save(notiBuilder.buildNotiData(2, user1)));
        notiList.add(notiRepository.save(notiBuilder.buildNotiData(3, user1)));
        notiList.add(notiRepository.save(notiBuilder.buildNotiData(10, user2)));

        return notiList;
    }

    public List<NotiCnt> insertInitData_NotiCnt() {
        List<NotiCnt> notiCntList = new ArrayList<>();

        List<MockUser> userList = new ArrayList<>();
        userList.add(mockUserRepository.findByUserId(notiBuilder.buildUserData(1).getUserId()));
        userList.add(mockUserRepository.findByUserId(notiBuilder.buildUserData(2).getUserId()));

        for (MockUser user : userList){
            NotiCnt notiCnt = new NotiCnt();
            notiCnt.setTotalCnt(notiRepository.countByUsers(user));
            notiCnt.setBoardCnt(notiRepository.countByUsersAndModule(user, "board"));
            notiCnt.setQnaCnt(notiRepository.countByUsersAndModule(user, "qna"));
            notiCnt.setUser(user);
            notiCntList.add(notiCntRepository.save(notiCnt));
        }
        return notiCntList;
    }
}
