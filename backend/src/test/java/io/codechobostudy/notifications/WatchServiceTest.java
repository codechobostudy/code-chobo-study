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
import io.codechobostudy.notifications.service.WatchService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WatchServiceTest {
    @Autowired
    private NotiRepository notiRepository;
    @Autowired
    private NotiCntRepository notiCntRepository;
    @Autowired
    private MockUserRepository mockUserRepository;
    @Autowired
    private WatchRepository watchRepository;
    @Autowired
    private WatchService watchService;

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
    public void testSetupWatch(){
        // given
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchModuleIdx(1);
        watchDTO.setWatchModuleName("board");

        MockUserDTO userDTO = given_userDTO_1;

        // when
        watchService.setupWatch(watchDTO, userDTO);

        // then
        List<Watch> watchList = watchRepository.findAll();
        assertThat(watchList.size(), is(1));
        assertThat(watchList.get(0).getWatchModuleIdx(), is(1));
        assertThat(watchList.get(0).getWatchModuleName(), is("board"));
        assertThat(watchList.get(0).getWatchUser().getUserName(), is("jinhyun"));
    }

    @Test
    public void testGetWatchUser() {
        // given
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchModuleIdx(1);
        watchDTO.setWatchModuleName("board");
        watchDTO.setWatchUserDTO(given_userDTO_1);

        watchRepository.save(new WatchDTO().toDomain(watchDTO));

        // when
        WatchDTO dbWatchDTO = watchService.getWatchUser(watchDTO, given_userDTO_1);

        // then
        assertThat(dbWatchDTO.getWatchModuleIdx(), is(1));
        assertThat(dbWatchDTO.getWatchModuleName(), is("board"));
        assertThat(dbWatchDTO.getWatchUserDTO().getUserName(), is("jinhyun"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetupWatch_null(){
        // TODO: DTO null 검증 로직구현: object가 null 이거나 변수가 null 확인
        // given
        WatchDTO watchDTO = null;
        MockUserDTO userDTO = null;

        // when
        watchService.setupWatch(watchDTO, userDTO);
    }

    @Test
    public void testWatchStatus_on() {
        // given
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchModuleIdx(1);
        watchDTO.setWatchModuleName("board");

        MockUserDTO userDTO = given_userDTO_1;
        watchService.setupWatch(watchDTO, userDTO);

        // when
        String watchStatus = watchService.watchStatus(watchDTO, userDTO);

        // then
        assertThat(watchStatus, is("on"));
    }

    @Test
    public void testWatchStatus_off() {
        // given
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchModuleIdx(1);
        watchDTO.setWatchModuleName("board");

        MockUserDTO userDTO = given_userDTO_1;

        // when
        String watchStatus = watchService.watchStatus(watchDTO, userDTO);

        // then
        assertThat(watchStatus, is("off"));
    }

}
