package io.codechobostudy.mock.user.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MockUserService {
    @Autowired
    private MockUserRepository mockUserRepository;

    public MockUser insertUser(MockUser user) {
        return mockUserRepository.save(user);
    }

    public MockUser getUserByUserId(String userId) {
        return mockUserRepository.findByUserId(userId);
    }
}
