package io.codechobostudy.notifications;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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
    private MockNotiBuilder notiBuilder;

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
        Noti noti = notiBuilder.buildNotiData(1);
        notiRepository.save(noti);

        Noti dbNoti = notiRepository.findOne(1);
        assertThat(noti.getContents(), is(dbNoti.getContents()));
    }

    @Test
    public void insertUserAndNoti() {
        // Insert into User
        MockUser user = notiBuilder.buildUserData(1);
        mockUserRepository.save(user);

        MockUser dbUser = mockUserRepository.findOne(1);
        assertThat(user.getUserId(), is(dbUser.getUserId()));

        // Insert into Noti
        Noti noti1 = notiBuilder.buildNotiData(1, dbUser);
        Noti noti2 = notiBuilder.buildNotiData(2, dbUser);
        notiRepository.save(noti1);
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
        mockUserRepository.save(notiBuilder.buildUserData(2));

        MockUser dbUser = mockUserRepository.findByUserId(user1.getUserId());

        // Insert into Noti
        Noti noti1 = notiBuilder.buildNotiData(1, dbUser);
        notiRepository.save(noti1);
        notiRepository.save(notiBuilder.buildNotiData(2, dbUser));
        notiRepository.save(notiBuilder.buildNotiData(4, dbUser));

        List<Noti> dbNoti = notiRepository.findByUsers(dbUser);

        assertThat(dbNoti.size(), is(3));
        assertThat(noti1.getUsers().getUserId(), is(dbNoti.get(0).getUsers().getUserId()));

        // Insert into NotiCnt
        // Case 1. Db
        int totalCnt = notiRepository.countByUsers(dbUser);
        int boardCnt = notiRepository.countByUsersAndModule(dbUser, "board");
        int qnaCnt = notiRepository.countByUsersAndModule(dbUser, "qna");
        // Case 2. Java
        assertThat(2, is(boardCnt));
        assertThat(1, is(qnaCnt));

        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setTotalCnt(totalCnt);
        notiCnt.setBoardCnt(boardCnt);
        notiCnt.setUser(dbUser);
        notiCntRepository.save(notiCnt);

        NotiCnt dbNotiCnt = notiCntRepository.findOne(1);
        assertThat(totalCnt, is(dbNotiCnt.getTotalCnt()));

        System.out.println("Data Check");
    }

    @Test
    public void jpaPermanenceExample() throws CloneNotSupportedException {
        Noti noti = new Noti("This is contents");

        List<MockUser> userList = new ArrayList<>();
        userList.add(new MockUser("elsa"));
        userList.add(new MockUser("olaf"));

        // Case 1. Success
        for(MockUser user: userList){
            Noti cloneNoti = noti.clone();
            notiRepository.save(cloneNoti);
        }
        assertThat(2, is(notiRepository.findAll().size()));

        // Case 2. Fail
        for(MockUser user: userList){
            notiRepository.save(noti);
            /* 2번째 인덱스부터는 Insert 되지 않음
               객체를 저장하게되면 그 객체 또한 Hibernate Entity로 변경되는듯함 */
        }
        assertThat(4, is(notiRepository.findAll().size()));
    }
}
