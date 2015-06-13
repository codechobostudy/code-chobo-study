package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.dto.RelayNotiDTO;
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

    @Test (expected = IllegalArgumentException.class)
    public void testDestoryWatchUser_watchIdxNull() {
        // given
        WatchDTO watchDTO_1 = new WatchDTO();
        watchDTO_1.setWatchModuleIdx(1);
        watchDTO_1.setWatchModuleName("board");
        watchDTO_1.setWatchUserDTO(given_userDTO_1);
        watchRepository.save(new WatchDTO().toDomain(watchDTO_1));

        // when
        watchService.destroyWatchUser(watchDTO_1, given_userDTO_1);
    }

    /*
    로그인 사용자가 선택한 글에 대한 지켜보기 해제
     */
    @Test
    public void testDestroyWatchUser() {
        // given
        WatchDTO watchDTO_1 = new WatchDTO();
        watchDTO_1.setWatchModuleIdx(1);
        watchDTO_1.setWatchModuleName("board");
        watchDTO_1.setWatchUserDTO(given_userDTO_1);
        watchRepository.save(new WatchDTO().toDomain(watchDTO_1));

        WatchDTO watchDTO_2 = new WatchDTO();
        watchDTO_2.setWatchModuleIdx(2);
        watchDTO_2.setWatchModuleName("board");
        watchDTO_2.setWatchUserDTO(given_userDTO_1);
        watchRepository.save(new WatchDTO().toDomain(watchDTO_2));

        WatchDTO watchDTO_3 = new WatchDTO();
        watchDTO_3.setWatchModuleIdx(3);
        watchDTO_3.setWatchModuleName("board");
        watchDTO_3.setWatchUserDTO(given_userDTO_2);
        watchRepository.save(new WatchDTO().toDomain(watchDTO_3));

        // when
        watchService.destroyWatchUser(watchDTO_1, given_userDTO_1);

        // then
        List<Watch> watchList = watchRepository.findAll();
        assertThat(watchList.size(), is(2));
        assertThat(watchList.get(0).getWatchIdx(), is(2));
        assertThat(watchList.get(0).getWatchUser().getUserName(), is("jinhyun"));
        assertThat(watchList.get(1).getWatchIdx(), is(3));
        assertThat(watchList.get(1).getWatchUser().getUserName(), is("changhwaoh"));
    }

    /**
     * 특정 내용의 지켜보기를 설정한 모든 사용자목록 조회 메소드
     */
    @Test
    public void testGetWatchUserList() {
        // given: 하나의 게시글을 2명의 사용자가 지켜보기 설정
        WatchDTO watchDTO_1 = new WatchDTO();
        watchDTO_1.setWatchModuleIdx(1);
        watchDTO_1.setWatchModuleName("board");
        watchDTO_1.setWatchUserDTO(given_userDTO_1);
        watchRepository.save(new WatchDTO().toDomain(watchDTO_1));

        WatchDTO watchDTO_2 = new WatchDTO();
        watchDTO_2.setWatchModuleIdx(1);
        watchDTO_2.setWatchModuleName("board");
        watchDTO_2.setWatchUserDTO(given_userDTO_2);
        watchRepository.save(new WatchDTO().toDomain(watchDTO_2));

        // when
        RelayNotiDTO relayNotiDTO = new RelayNotiDTO();
        relayNotiDTO.setModule("board");
        relayNotiDTO.setPrimaryKey(1);
        List<MockUserDTO> watchList = watchService.getWatchUserList(relayNotiDTO);

        // then
        assertThat(watchList.size(), is(2));
        assertThat(watchList.get(0).getUserName(), is("jinhyun"));
        assertThat(watchList.get(1).getUserName(), is("changhwaoh"));
    }

    @Test
    public void toToWatchDTO() {
        // given
        RelayNotiDTO relayNotiDTO = new RelayNotiDTO();
        relayNotiDTO.setModule("board");
        relayNotiDTO.setPrimaryKey(1);

        // when
        WatchDTO watchDTO = new RelayNotiDTO().toWatchDTO(relayNotiDTO);

        // then
        assertThat(watchDTO.getWatchModuleIdx(), is(1));
        assertThat(watchDTO.getWatchModuleName(), is("board"));
    }
}
