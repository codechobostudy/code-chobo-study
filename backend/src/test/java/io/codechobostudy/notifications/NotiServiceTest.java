package io.codechobostudy.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.Application;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.domain.NotiView;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.MockNotiRepository;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import io.codechobostudy.notifications.service.NotiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    private MockNotiRepository mockNotiRepository;

    @Autowired
    private NotiService notiService;

    @Test
    public void testGetNotiData() throws IOException, CloneNotSupportedException {
        // given
        mockNotiRepository.insertInitData();
        String jsonStr = notiService.getNotiData();

        // when
        ObjectMapper objectMapper = new ObjectMapper();
        NotiView notiView = objectMapper.readValue(jsonStr, NotiView.class);

        // then
        assertThat(notiBuilder.buildNotiData(1).getContents(), is(notiView.getNotiList().get(0).getContents()));
    }
}
