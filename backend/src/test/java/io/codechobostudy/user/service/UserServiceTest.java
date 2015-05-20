package io.codechobostudy.user.service;

import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.fixture.UserFixture;
import io.codechobostudy.user.repository.UserRepository;
import io.codechobostudy.user.service.impl.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        User savedUser = UserFixture.getUsers().get(0);
        Mockito.when(userRepository.findByNickname(savedUser.getNickname()))
            .thenReturn(savedUser);
    }

    @Test
    public void shouldEqualSavedUserToFindUserByNickname() {
        User savedUser = UserFixture.getUsers().get(0);
        Assert.assertThat(userService.findByNickname(savedUser.getNickname()), CoreMatchers.is(savedUser));
    }

}
