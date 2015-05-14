package io.codechobostudy.user.service;

import io.codechobostudy.Application;
import io.codechobostudy.user.domain.UserDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    //TODO
    //builder pattern.

    @Test
    @Rollback
    public void test_user_create() throws Exception {

        UserDomain userDomain = new UserDomain();
        userDomain.setUserId("scarfunk");
        userDomain.setEmail("scarfunk@naver.com");
        //when
        UserDomain resultUserDomain = userService.createUser(userDomain);

        //then
        assertNotNull(resultUserDomain.getId());
        assertNotNull(resultUserDomain.getUserId());
        assertEquals(resultUserDomain.getEmail(), (userDomain.getEmail()));

    }
}