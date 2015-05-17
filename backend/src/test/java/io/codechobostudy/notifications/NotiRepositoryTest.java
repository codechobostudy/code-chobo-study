package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
    private NotiBuilder notiBuilder;

    /*
    Service
    - [ ] Make NotiView Json
        public String getNotiViewData(){
            // Make NotiView Json
            return NotiView Json String;
        }

    다른 서비스에서 알림호출시 생각해볼 문제들
    - 직접 알림서비스를 선언하고 호출
    - 인터페이스를 통한 호출
    - URL을 통한 호출 (먼저 진행)
    - AOP를 통한 호출

    Controller
    - [ ] notiData input: request jsonData
    - [ ] notiData output: use simpMsgTemplate
        @RquestMapping(value='/pushData')
        @ResponseBody
        public String pushData(){
            // request jsonData
            // push message To websocket(/subscribe/notiData)
            return 'success'
        }
    */
    @Test
    public void insertNoti() {
        String contents = "jeonyb님이 sukkyu.oh님의 게시물에 댓글을 남겼습니다.";
        String url = "http://localhost:8080/noti/main";
        String module = "board";
        String toUserId = "id_Jinhyun";

        Noti noti = new Noti();
        noti.setContents(contents);
        noti.setUrl(url);
        noti.setModule(module);
        noti.setToUserName(toUserId);

        notiRepository.save(noti);
        Noti dbNoti = notiRepository.findOne(1);
        assertThat(contents, is(dbNoti.getContents()));
    }

    @Test
    public void insertUserAndNoti() {
        // Insert into User
        MockUser user = notiBuilder.buildUserData(1);
        mockUserRepository.save(user);

        MockUser dbUser = mockUserRepository.findOne(1);
        assertThat(user.getUserId(), is(dbUser.getUserId()));

        // Insert into Noti
        Noti noti1 = notiBuilder.buildNotiData(1);
        noti1.setUser(dbUser);
        notiRepository.save(noti1);

        Noti noti2 = notiBuilder.buildNotiData(2);
        noti2.setUser(dbUser);
        notiRepository.save(noti2);

        Noti dbNoti1 = notiRepository.findOne(1);
        Noti dbNoti2 = notiRepository.findOne(2);

        assertThat(dbNoti1, is(notNullValue()));
        assertThat(dbNoti2, is(notNullValue()));
        assertThat(noti1.getContents(), is(dbNoti1.getContents()));
        assertThat(noti2.getContents(), is(dbNoti2.getContents()));

        System.out.println("Data Check");
    }

    @Test
    public void insertNotiAndNotiCnt() {
        // Insert into User
        MockUser user1 = notiBuilder.buildUserData(1);
        mockUserRepository.save(user1);
        MockUser user2 = notiBuilder.buildUserData(2);
        mockUserRepository.save(user2);

        MockUser dbUser = mockUserRepository.findOne(1);

        // Insert into Noti
        Noti noti1 = notiBuilder.buildNotiData(1);
        noti1.setUser(dbUser);
        notiRepository.save(noti1);

        Noti noti2 = notiBuilder.buildNotiData(2);
        noti2.setUser(dbUser);
        notiRepository.save(noti2);

        Noti noti4 = notiBuilder.buildNotiData(4);
        noti4.setUser(dbUser);
        notiRepository.save(noti4);

        List<Noti> dbNoti = notiRepository.findByUsers(dbUser);

        assertThat(dbNoti.size(), is(3));
        assertThat(dbUser.getIdx(), is(dbNoti.get(0).getUser().getIdx()));

        // Insert into NotiCnt
        // Case 1. Db
        int totalCnt = notiRepository.countByUsers(dbUser);
        int boardCnt = notiRepository.countByUsersAndModule(dbUser, "board");
        int qnaCnt = notiRepository.countByUsersAndModule(dbUser, "qna");
        assertThat(2, is(boardCnt));
        assertThat(1, is(qnaCnt));
        // Case 2. Java

        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setTotalCnt(totalCnt);
        notiCnt.setBoardCnt(boardCnt);
        notiCnt.setUser(dbUser);

        notiCntRepository.save(notiCnt);

        NotiCnt dbNotiCnt = notiCntRepository.findOne(1);
        assertThat(totalCnt, is(dbNotiCnt.getTotalCnt()));

        System.out.println("Data Check");
    }
}
