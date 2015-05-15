package io.codechobostudy.user.service.impl;

import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.service.UserService;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByNickname(String nickname) {
        return null;
    }

    @Override
    public User findByProvider(ConnectionKey connectionKey) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
