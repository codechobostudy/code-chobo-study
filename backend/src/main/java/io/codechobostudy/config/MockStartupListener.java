package io.codechobostudy.config;

import io.codechobostudy.mock.user.service.MockUserService;
import io.codechobostudy.notifications.service.MockNotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MockStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MockUserService mockUserService;

    @Autowired
    private MockNotiService mockNotiService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        System.out.println(">>> Noti Insert Init Data <<<");
        mockUserService.insertInitData();
        mockNotiService.insertInitData_NotiAndNotiCnt();
    }
}