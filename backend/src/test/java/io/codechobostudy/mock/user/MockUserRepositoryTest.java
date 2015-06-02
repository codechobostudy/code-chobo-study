package io.codechobostudy.mock.user;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_First;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_Second;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MockUserRepositoryTest {
    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private MockUserRepository mockUserRepository;

    @Autowired
    private NotiRepository notiRepository;

    private MockUser given_user_1;
    private MockUser given_user_2;
    private MockUserDTO given_userDTO_1;
    private MockUserDTO given_userDTO_2;

    @Before
    public void setup() {
        notiRepository.deleteAll();
        notiCntRepository.deleteAll();
        mockUserRepository.deleteAll();

        given_userDTO_1 = new UserDTOBuilderData_First().buildData();
        given_user_1 = new MockUserDTO().toDomain(given_userDTO_1);

        given_userDTO_2 = new UserDTOBuilderData_Second().buildData();
        given_user_2 = new MockUserDTO().toDomain(given_userDTO_2);
    }

    @Test
    public void convert_userDTO_toDomain() {
        // when
        MockUser user_1 = new MockUserDTO().toDomain(given_userDTO_1);

        // then
        assertThat(user_1, is(notNullValue()));
    }

    @Test
    public void convert_userDTO_toDTO() {
        // given
        mockUserRepository.save(given_user_1);
        MockUser dbUser = mockUserRepository.findByUserId(given_user_1.getUserId());

        // when
        MockUserDTO resultUserDTO_1 = new MockUserDTO().toDTO(dbUser);

        // then
        assertThat(resultUserDTO_1, is(notNullValue()));
        assertThat(resultUserDTO_1.getUserId(), is(given_user_1.getUserId()));
        assertThat(resultUserDTO_1.getUserName(), is(given_user_1.getUserName()));
        assertThat(resultUserDTO_1.getNotiDTOList(), is(nullValue()));
    }


    @Test
    public void convert_userDTO_toDTOList() {
        // when
        List<MockUser> userList = new ArrayList<>();
        userList.add(given_user_1);
        userList.add(given_user_2);

        // then
        List<MockUserDTO> userDTOList = new MockUserDTO().toDTOList(userList);
        assertThat(userDTOList, is(notNullValue()));
        assertThat(userDTOList.size(), is(2));
        assertThat(userDTOList.get(0).getUserId(), is(given_user_1.getUserId()));
    }

    @Test
    public void insert_MockUser() {
        // when
        mockUserRepository.save(given_user_1);
        mockUserRepository.save(given_user_2);
        MockUser dbUser_1 = mockUserRepository.findByUserId(given_user_1.getUserId());
        MockUser dbUser_2 = mockUserRepository.findByUserId(given_user_2.getUserId());

        // then
        assertThat(dbUser_1, is(notNullValue()));
        assertThat(dbUser_1.getUserId(), is(given_user_1.getUserId()));
        assertThat(dbUser_2, is(notNullValue()));
        assertThat(dbUser_2.getUserId(), is(given_user_2.getUserId()));
    }

    @Test
    public void insert_userDTO_toDomain() {
        // when
        mockUserRepository.save(new MockUserDTO().toDomain(given_userDTO_1));

        // then
        MockUser dbUser = mockUserRepository.findByUserId(given_userDTO_1.getUserId());
        assertThat(dbUser, is(notNullValue()));
        assertThat(dbUser.getUserId(), is(given_userDTO_1.getUserId()));
    }
}
