package io.codechobostudy.user.repository;

import io.codechobostudy.Application;
import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.fixture.UserFixture;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback
    public void shouldEqualSavedUserToFindUserByNickname() {
        User savedUser = UserFixture.getUsers().get(0);
        User foundUser = userRepository.findByNickname(savedUser.getNickname());
        Assert.assertThat(foundUser, CoreMatchers.nullValue());

        userRepository.save(savedUser);

        foundUser = userRepository.findByNickname(savedUser.getNickname());
        Assert.assertThat(foundUser, CoreMatchers.notNullValue());

        Assert.assertThat(foundUser.getIdx(), CoreMatchers.is(savedUser.getIdx()));
    }

}
