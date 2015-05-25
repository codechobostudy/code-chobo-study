package io.codechobostudy.mock.user.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
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

    public MockUserDTO getUser(String userId) {
        return new MockUserDTO().toDTO(mockUserRepository.findByUserId(userId));
    }

    public List<MockUser> insertInitData() {
        List<MockUser> userList = new ArrayList<>();

        MockUserDTO user1 = notiBuilder.buildUserData(1);
        userList.add(mockUserRepository.save(new MockUserDTO().toDomain(user1)));

        MockUserDTO user2 = notiBuilder.buildUserData(2);
        userList.add(mockUserRepository.save(new MockUserDTO().toDomain(user2)));

        return userList;
    }

    public void deleteAllData() {
        mockUserRepository.deleteAll();
    }

    public List<MockUserDTO> getUserList() {
        List<MockUser> userList = mockUserRepository.findAll();
        List<MockUserDTO> resultUserList = new ArrayList<>();

        for (MockUser user : userList){
            MockUserDTO mockUserDTO = new MockUserDTO().toDTO(user);
            resultUserList.add(mockUserDTO);
        }

        return resultUserList;
    }
}
