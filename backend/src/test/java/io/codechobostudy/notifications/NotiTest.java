package io.codechobostudy.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.Application;
import io.codechobostudy.notifications.domain.NotiView;
import io.codechobostudy.notifications.repository.MockNotiViewRepository;
import io.codechobostudy.notifications.service.NotiService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NotiTest {
    @Autowired
    private NotiService notiService;

    @Test
    public void testGetNotiViewJson() throws IOException {
        String jsonString = notiService.getNotiViewJsonString();

        ObjectMapper objectMapper = new ObjectMapper();
        NotiView jsonObject = objectMapper.readValue(jsonString, NotiView.class);

        assertThat(MockNotiViewRepository.firstContents, is(jsonObject.getNotiList().get(0).getContents()));
        assertThat(MockNotiViewRepository.secondContents, is(jsonObject.getNotiList().get(1).getContents()));
        assertThat(MockNotiViewRepository.totalCnt, is(jsonObject.getNotiCnt().getTotalCnt()));
    }
}
