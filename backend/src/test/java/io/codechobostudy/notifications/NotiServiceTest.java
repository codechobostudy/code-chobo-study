package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.dto.NotiCntDTO;
import io.codechobostudy.notifications.dto.NotiDTO;
import io.codechobostudy.notifications.dto.NotiViewDTO;
import io.codechobostudy.notifications.fixture.NotiDTOBuilderData_BoardFirst;
import io.codechobostudy.notifications.fixture.NotiDTOBuilderData_BoardSecond;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_First;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_Second;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import io.codechobostudy.notifications.service.MockNotiService;
import io.codechobostudy.notifications.service.NotiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NotiServiceTest {
    @Autowired
    private NotiRepository notiRepository;

    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private MockUserRepository mockUserRepository;

    @Autowired
    private MockNotiBuilder notiBuilder;

    @Autowired
    private NotiService notiService;

    @Autowired
    private MockNotiService mockNotiService;

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
    public void testRelayNoti() throws IOException {
        notiService.relayNoti("");
    }

    @Test
    public void testRegisterNotiUsers() throws IOException {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        List<MockUserDTO> watchUserList = new ArrayList<>();
        watchUserList.add(new MockUserDTO().toDTO(db_user_1));
        watchUserList.add(new MockUserDTO().toDTO(db_user_2));

        // when
        List<NotiViewDTO> notiList = notiService.registerNotiUsers(notiDTO, watchUserList);

        // then
        assertThat(notiList, is(notNullValue()));
        assertThat(notiList.size(), is(watchUserList.size()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNotiUsers_userIdxNull() throws IOException {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        List<MockUserDTO> watchUserList = new ArrayList<>();
        given_userDTO_1.setIdx(0);
        watchUserList.add(given_userDTO_1);

        // when
        List<NotiViewDTO> notiList = notiService.registerNotiUsers(notiDTO, watchUserList);
    }

    @Test
    public void testSaveNoti() throws IOException {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        MockUserDTO userDTO = new MockUserDTO().toDTO(db_user_1);

        // when
        notiService.saveNoti(notiDTO, userDTO);

        // then
        List <Noti> dbNotiList = notiRepository.findAll();
        Noti dbNoti = dbNotiList.get(0);
        assertThat(dbNotiList.size(), is(1));
        assertThat(dbNoti.getContents(), is("jeonyb님이 sukkyu.oh님의 게시물에 댓글을 남겼습니다."));
        assertThat(dbNoti.getUser().getUserId(), is("Id_jinhyun"));
        // TODO: noti.user의 idx 검증필요
    }

    @Test
    public void testSaveNotiCnt() throws IOException {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        MockUserDTO userDTO = new MockUserDTO().toDTO(db_user_1);
        notiService.saveNoti(notiDTO, userDTO);

        // when
        NotiCntDTO notiCntDTO = notiService.saveNotiCnt(new MockUserDTO().toDTO(db_user_1));

        // then
        NotiCnt dbNotiCnt = notiCntRepository.findByUser(db_user_1);
        assertThat(dbNotiCnt.getTotalCnt(), is(1));
        assertThat(dbNotiCnt.getBoardCnt(), is(1));
        assertThat(dbNotiCnt.getQnaCnt(), is(0));
    }

    @Test
    public void testPushUpdatedData() throws IOException {
        List<MockUserDTO> watchUserList = new ArrayList<>();
        watchUserList.add(new MockUserDTO().toDTO(db_user_1));
        watchUserList.add(new MockUserDTO().toDTO(db_user_2));

        // when
        notiService.pushUpdatedData(watchUserList);

        // then
        // ...
    }

    @Test
    public void testGetNotiDTOList() throws IOException {
        NotiDTO notiDTO_1 = new NotiDTOBuilderData_BoardFirst().buildData();
        MockUserDTO userDTO = new MockUserDTO().toDTO(db_user_1);
        notiService.saveNoti(notiDTO_1, userDTO);

        NotiDTO notiDTO_2 = new NotiDTOBuilderData_BoardSecond().buildData();
        notiService.saveNoti(notiDTO_2, userDTO);

        // when
        List<NotiDTO> notiDTOList = notiService.getNotiDTOList(userDTO);

        // then
        assertThat(notiDTOList, is(notNullValue()));
        assertThat(notiDTOList.size(), is(2));
    }

    @Test
    public void testGetNotiCntDTO() throws IOException {
        NotiDTO notiDTO_1 = new NotiDTOBuilderData_BoardFirst().buildData();
        MockUserDTO userDTO = new MockUserDTO().toDTO(db_user_1);
        notiService.saveNoti(notiDTO_1, userDTO);

        NotiDTO notiDTO_2 = new NotiDTOBuilderData_BoardFirst().buildData();
        notiService.saveNoti(notiDTO_2, userDTO);
        notiService.saveNotiCnt(userDTO);

        // when
        NotiCntDTO notiCntDTO = notiService.getNotiCntDTO(userDTO);

        // then
        NotiCnt dbNotiCnt = notiCntRepository.findByUser(db_user_1);
        assertThat(notiCntDTO, is(notNullValue()));
        assertThat(notiCntDTO.getTotalCnt(), is(dbNotiCnt.getTotalCnt()));
    }

    @Test
    public void testGetNotiData() throws IOException {
        // when
        notiService.getNotiData();

        // then
        // ...
    }

    @Test
    public void testLazilyError_NotiList() throws IOException {
        // given
        MockUserDTO userDTO = new MockUserDTO().toDTO(db_user_1);

        NotiDTO notiDTO_1 = new NotiDTOBuilderData_BoardFirst().buildData();
        notiService.saveNoti(notiDTO_1, userDTO);

        NotiDTO notiDTO_2 = new NotiDTOBuilderData_BoardSecond().buildData();
        notiService.saveNoti(notiDTO_2, userDTO);

        List<NotiDTO> notiDTOList = notiService.getNotiDTOList(userDTO);

        // when
        List<NotiDTO> resultNotiDTOList = notiService.lazilyError_NotiList(notiDTOList);

        // then
        assertThat(resultNotiDTOList, is(notNullValue()));
        for (NotiDTO notiDTO : resultNotiDTOList){
            assertThat(notiDTO.getUserDTO(), is(nullValue()));
        }
    }

    @Test
    public void testLazilyError_NotiCnt() throws IOException {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        MockUserDTO userDTO = new MockUserDTO().toDTO(db_user_1);
        notiService.saveNoti(notiDTO, userDTO);
        notiService.saveNotiCnt(new MockUserDTO().toDTO(db_user_1));
        NotiCntDTO notiCntDTO = notiService.getNotiCntDTO(userDTO);

        // when
        NotiCntDTO resultNotiCntDTO = notiService.lazilyError_NotiCnt(notiCntDTO);

        // then
        assertThat(resultNotiCntDTO, is(notNullValue()));
        assertThat(notiCntDTO.getUserDTO(), is(nullValue()));
    }

    /*
     [] 특정 내용의 지켜보기 설정
        [v] Watch Entity 설정 및 Insert WatchRepository
        [] Service Method
     [] 특정 내용의 지켜보기 해제
        [] 로그인한 사용자의 특정 내용의 지켜보기 조회
     [] 특정 내용의 지켜보기를 설정한 모든 사용자목록 조회
     */
    @Test
    public void testSetupWatch() {

    }

    @Test
    public void testClearWatch() {

    }
}
