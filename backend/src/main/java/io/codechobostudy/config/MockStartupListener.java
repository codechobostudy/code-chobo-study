package io.codechobostudy.config;

import io.codechobostudy.notifications.repository.MockNotiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MockStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MockNotiRepository mockNotiRepository;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        System.out.println(">>> Noti Insert Init Data <<<");
        mockNotiRepository.insertInitData();
    }
}