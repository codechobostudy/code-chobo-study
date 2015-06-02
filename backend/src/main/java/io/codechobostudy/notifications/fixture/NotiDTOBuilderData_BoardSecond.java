package io.codechobostudy.notifications.fixture;

public class NotiDTOBuilderData_BoardSecond extends NotiDTOBuilderData {
    @Override
    public void buildContents() {
        notiDTO.setContents("changhwaoh, urosaria님이 cdy212님의 게시물에 답변을 남겼습니다.");
    }

    @Override
    public void buildUrl() {
        notiDTO.setUrl("http://localhost:8080/noti/main");
    }

    @Override
    public void buildModule() {
        notiDTO.setModule("board");
    }

    @Override
    public void buildUserDTO() {
        notiDTO.setUserDTO(null);
    }
}
