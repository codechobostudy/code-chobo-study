package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.domain.Watch;
import io.codechobostudy.notifications.dto.WatchDTO;
import io.codechobostudy.notifications.dto.NotiDTO;
import io.codechobostudy.notifications.fixture.NotiDTOBuilderData_BoardFirst;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_First;
import io.codechobostudy.notifications.fixture.UserDTOBuilderData_Second;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import io.codechobostudy.notifications.repository.WatchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NotiRepositoryTest {
    @Autowired
    private NotiRepository notiRepository;

    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private MockUserRepository mockUserRepository;

    @Autowired
    private MockNotiBuilder notiBuilder;

    @Autowired
    private WatchRepository watchRepository;

    // TODO: delete given_user_1, given_user_2
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
    public void insert_Noti() {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        Noti noti = new NotiDTO().toDomain(notiDTO);

        // when
        notiRepository.save(noti);

        // then
        List <Noti> dbNotiList = notiRepository.findAll();
        Noti dbNoti = dbNotiList.get(0);
        assertThat(dbNotiList.size(), is(1));
        assertThat(dbNoti.getModule(), is("board"));
        assertThat(dbNoti.getContents(), is("jeonyb님이 sukkyu.oh님의 게시물에 댓글을 남겼습니다."));
    }

    @Test
    public void insert_UserAndNoti() {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        notiDTO.setUserDTO(new MockUserDTO().toDTO(db_user_1));
        Noti noti = new NotiDTO().toDomain(notiDTO);

        // when
        notiRepository.save(noti);

        // then
        Noti dbNoti = notiRepository.findOne(1);
        assertThat(dbNoti.getUser().getIdx(), is(1));
        assertThat(dbNoti.getUser().getUserId(), is("Id_jinhyun"));
        assertThat(dbNoti.getModule(), is("board"));
        assertThat(dbNoti.getContents(), is("jeonyb님이 sukkyu.oh님의 게시물에 댓글을 남겼습니다."));
    }

    @Test
    public void insert_NotiAndNotiCnt() {
        NotiDTO notiDTO = new NotiDTOBuilderData_BoardFirst().buildData();
        notiDTO.setUserDTO(new MockUserDTO().toDTO(db_user_1));
        notiRepository.save(new NotiDTO().toDomain(notiDTO));

        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setTotalCnt(notiRepository.countByUser(db_user_1));
        notiCnt.setBoardCnt(notiRepository.countByUserAndModule(db_user_1, "board"));
        notiCnt.setQnaCnt(notiRepository.countByUserAndModule(db_user_1, "qna"));
        notiCnt.setUser(db_user_1);

        // when
        notiCntRepository.save(notiCnt);

        // then
        NotiCnt dbNotiCnt = notiCntRepository.findOne(1);
        assertThat(dbNotiCnt.getQnaCnt(), is(0));
        assertThat(dbNotiCnt.getBoardCnt(), is(1));
        assertThat(dbNotiCnt.getTotalCnt(), is(1));
    }

    @Test
    public void convert_notiDTO_toDTO() {

    }

    @Test
    public void convert_notiDTO_toDomain() {

    }

}
