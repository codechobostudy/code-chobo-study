package io.codechobostudy.user.fixture;

import io.codechobostudy.user.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserFixture {

    private static final String NICKNAME_PREFIX = "test";

    private static final List<User> users;

    static {
        users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = new User();
            user.setIdx(i);
            user.setUserId(Integer.toString(i));
            user.setNickname(NICKNAME_PREFIX + i);
            user.setProviderId(i % 2 == 0 ? "twitter" : "facebook");
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            users.add(user);
        }
    }

    public static List<User> getUsers() {
        return users;
    }

}
