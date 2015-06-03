package io.codechobostudy.notifications.fixture;

import io.codechobostudy.mock.user.dto.MockUserDTO;

public abstract class UserDTOBuilderData {
    protected MockUserDTO userDTO;

    public MockUserDTO buildData() {
        userDTO = new MockUserDTO();
        buildIdx();
        buildUserId();
        buildUserName();
        return userDTO;
    }

    public abstract void buildIdx();
    public abstract void buildUserId();
    public abstract void buildUserName();
}
