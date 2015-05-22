package io.codechobostudy.mock.user.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MockUserService {
    @Autowired
    private MockUserRepository mockUserRepository;

    @Autowired
    private MockNotiBuilder notiBuilder;

    public MockUser insertUser(MockUser user) {
        return mockUserRepository.save(user);
    }

    public MockUser getUserByUserId(String userId) {
        return mockUserRepository.findByUserId(userId);
    }

    public List<MockUser> insertInitData() {
        List<MockUser> userList = new ArrayList<>();

        MockUser user1 = notiBuilder.buildUserData(1);
        userList.add(mockUserRepository.save(user1));

        MockUser user2 = notiBuilder.buildUserData(2);
        userList.add(mockUserRepository.save(user2));

        return userList;
    }

    public void deleteAllData() {
        mockUserRepository.deleteAll();
    }
}
