package io.codechobostudy.user.service.impl;

import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.repository.UserRepository;
import io.codechobostudy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findByProvider(ConnectionKey connectionKey) {
        return userRepository.findByProviderIdAndUserId(connectionKey.getProviderId(), connectionKey.getProviderUserId());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User createUser(User user, ConnectionKey connectionKey) {
        User newUser = new User();
        Date currentDate = new Date();
        newUser.setNickname(user.getNickname());
        newUser.setUpdatedAt(currentDate);
        newUser.setCreatedAt(currentDate);
        newUser.setProviderId(connectionKey.getProviderId());
        newUser.setUserId(connectionKey.getProviderUserId());
        return newUser;
    }
}
