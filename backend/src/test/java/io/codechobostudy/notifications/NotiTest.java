package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.service.MockUserService;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiView;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import io.codechobostudy.notifications.service.NotiService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NotiTest {
    @Autowired
    private NotiService notiService;

    @Autowired
    private MockNotiBuilder notiBuilder;

    @Autowired
    private MockUserService mockUserService;

    @Autowired
    private NotiRepository notiRepository;

    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;

    @Test
    public void testRelayNoti() throws CloneNotSupportedException {
        notiService.relayNoti("");
    }

    @Test
    public void testRegisterNotiUsers() throws CloneNotSupportedException {
        // given
        MockUser insertedUser = mockUserService.insertUser(notiBuilder.buildUserData(1));
        Noti notiData = notiBuilder.buildNotiData(1);

        MockUser dbUser = mockUserService.getUserByUserId("id_Jinhyun");
        List <MockUser> watchUserList = new ArrayList<>();
        watchUserList.add(dbUser);

        // when
        List <NotiView> notiViewList = notiService.registerNotiUsers(notiData, watchUserList);

        // then
        assertThat(notiData.getContents(), is(notiViewList.get(0).getNoti().getContents()));
    }

    @Test
    public void testPushUpdatedData() throws CloneNotSupportedException {
        // given
        MockUser insertedUser = mockUserService.insertUser(notiBuilder.buildUserData(1));
        Noti notiData = notiBuilder.buildNotiData(1);

        MockUser dbUser = mockUserService.getUserByUserId("id_Jinhyun");
        List <MockUser> watchUserList = new ArrayList<>();
        watchUserList.add(dbUser);

        List <NotiView> notiViewList = notiService.registerNotiUsers(notiData, watchUserList);

        // when
        notiService.pushUpdatedData(watchUserList);

        // then
        // websocket은 웹에서만 확인이 가능한가..
    }
}
