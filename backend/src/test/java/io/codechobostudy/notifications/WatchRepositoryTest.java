package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Watch;
import io.codechobostudy.notifications.dto.WatchDTO;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_First;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_Second;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import io.codechobostudy.notifications.repository.WatchRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WatchRepositoryTest {
    @Autowired
    private NotiRepository notiRepository;
    @Autowired
    private NotiCntRepository notiCntRepository;
    @Autowired
    private MockUserRepository mockUserRepository;
    @Autowired
    private WatchRepository watchRepository;

    // user
    private MockUser given_user_1;
    private MockUser given_user_2;
    private MockUserDTO given_userDTO_1;
    private MockUserDTO given_userDTO_2;
    private MockUser db_user_1;
    private MockUser db_user_2;

    @Before
    public void setup() {
        notiRepository.deleteAll();
        notiCntRepository.deleteAll();
        mockUserRepository.deleteAll();

        given_userDTO_1 = new UserDTOBuilderData_First().buildData();
        given_user_1 = new MockUserDTO().toDomain(given_userDTO_1);

        given_userDTO_2 = new UserDTOBuilderData_Second().buildData();
        given_user_2 = new MockUserDTO().toDomain(given_userDTO_2);

        mockUserRepository.save(given_user_1);
        mockUserRepository.save(given_user_2);

        db_user_1 = mockUserRepository.findByUserId(given_user_1.getUserId());
        db_user_2 = mockUserRepository.findByUserId(given_user_2.getUserId());
    }

    @Test
    public void convert_watchDTO_toDomain() {
        // given
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchIdx(1);
        watchDTO.setWatchModuleName("board");
        watchDTO.setWatchModuleIdx(1);
        watchDTO.setWatchUserDTO(given_userDTO_1);

        // when
        Watch watch = watchDTO.toDomain(watchDTO);

        // then
        assertThat(watch.getWatchIdx(), is(1));
        assertThat(watch.getWatchModuleName(), is("board"));
        assertThat(watch.getWatchModuleIdx(), is(1));
        assertThat(watch.getWatchUser().getIdx(), is(given_userDTO_1.getIdx()));
        assertThat(watch.getWatchUser().getUserId(), is(given_userDTO_1.getUserId()));
        assertThat(watch.getWatchUser().getUserName(), is(given_userDTO_1.getUserName()));
    }

    @Test
    public void insert_watch() {
        // given
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchModuleName("board");
        watchDTO.setWatchModuleIdx(1);
        watchDTO.setWatchUserDTO(new MockUserDTO().toDTO(db_user_1));
        Watch watch = watchDTO.toDomain(watchDTO);

        // when
        watchRepository.save(watch);

        // then
        List<Watch> watchList = watchRepository.findAll();
        assertThat(watchList.size(), is(1));
        assertThat(watchList.get(0).getWatchIdx(), is(1));
        assertThat(watchList.get(0).getWatchModuleName(), is("board"));
        assertThat(watchList.get(0).getWatchUser().getUserId(), is("Id_jinhyun"));
    }
}
