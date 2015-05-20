package io.codechobostudy.user.service;


import io.codechobostudy.user.domain.User;
import org.springframework.social.connect.ConnectionKey;

public interface UserService {

    User findByNickname(String nickname);

    User findByProvider(ConnectionKey connectionKey);

    void save(User user);

    User createUser(User user, ConnectionKey connectionKey);

}
