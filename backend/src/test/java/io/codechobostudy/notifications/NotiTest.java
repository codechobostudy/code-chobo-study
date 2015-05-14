package io.codechobostudy.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.notifications.domain.NotiView;
import io.codechobostudy.notifications.service.NotiService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NotiTest {
    @Autowired
    private NotiService notiService;

    @Test
    public void testGetNotiViewJson() throws IOException {
        notiService = new NotiService();    // Autowired 설정했는데도 오류
        String jsonString = notiService.getNotiViewJsonString();

        ObjectMapper objectMapper = new ObjectMapper();
        NotiView jsonObject = objectMapper.readValue(jsonString, NotiView.class);

        assertThat(notiService.firstContents, is(jsonObject.getNotiList().get(0).getContents()));
        assertThat(notiService.secondContents, is(jsonObject.getNotiList().get(1).getContents()));
        assertThat(notiService.totalCnt, is(jsonObject.getNotiCnt().getTotalCnt()));
    }
}
