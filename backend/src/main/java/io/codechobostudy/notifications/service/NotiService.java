package io.codechobostudy.notifications.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.notifications.domain.NotiView;
import io.codechobostudy.notifications.repository.MockNotiViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotiService {
    @Autowired
    private MockNotiViewRepository mockNotiViewRepository;

    public NotiView getNotiView() {
        return mockNotiViewRepository.getNotiView();
    }

    public String getNotiViewJsonString() throws IOException {
        NotiView notiView = getNotiView();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(notiView);
    }
}
