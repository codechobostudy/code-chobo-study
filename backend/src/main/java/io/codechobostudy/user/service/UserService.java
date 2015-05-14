package io.codechobostudy.user.service;

import io.codechobostudy.user.domain.UserDomain;
import io.codechobostudy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDomain createUser(UserDomain userDomain){
        return userRepository.save(userDomain);
    }

    public List<UserDomain> getAllUser(){
        return userRepository.findAll();
    }
}
