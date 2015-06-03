package io.codechobostudy.notifications.fixture;

public class UserDTOBuilderData_First extends UserDTOBuilderData{
    @Override
    public void buildIdx() {
        userDTO.setIdx(1);
    }

    @Override
    public void buildUserId() {
        userDTO.setUserId("Id_jinhyun");
    }

    @Override
    public void buildUserName() {
        userDTO.setUserName("jinhyun");
    }
}
