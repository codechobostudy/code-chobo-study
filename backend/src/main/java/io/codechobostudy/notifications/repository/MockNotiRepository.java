package io.codechobostudy.notifications.repository;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.NotiCnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MockNotiRepository {
    @Autowired
    private MockNotiBuilder notiBuilder;

    @Autowired
    private MockUserRepository mockUserRepository;

    @Autowired
    private NotiRepository notiRepository;

    @Autowired
    private NotiCntRepository notiCntRepository;

    public void insertInitData() {
        // Insert into User
        MockUser user1 = notiBuilder.buildUserData(1);
        mockUserRepository.save(user1);

        MockUser user2 = notiBuilder.buildUserData(2);
        mockUserRepository.save(user2);

        // Insert into Noti
        notiRepository.save(notiBuilder.buildNotiData(1, user1));
        notiRepository.save(notiBuilder.buildNotiData(2, user1));
        notiRepository.save(notiBuilder.buildNotiData(3, user1));
        notiRepository.save(notiBuilder.buildNotiData(10, user2));

        List<MockUser> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        // Insert into NotiCnt
        for (MockUser user : userList){
            NotiCnt notiCnt = new NotiCnt();
            notiCnt.setTotalCnt(notiRepository.countByUsers(user));
            notiCnt.setBoardCnt(notiRepository.countByUsersAndModule(user, "board"));
            notiCnt.setQnaCnt(notiRepository.countByUsersAndModule(user, "qna"));
            notiCnt.setUser(user);
            notiCntRepository.save(notiCnt);
        }

    }
}
