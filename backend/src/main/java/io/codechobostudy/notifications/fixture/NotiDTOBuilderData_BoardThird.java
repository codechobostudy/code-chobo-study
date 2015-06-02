package io.codechobostudy.notifications.fixture;

public class NotiDTOBuilderData_BoardThird extends NotiDTOBuilderData {
    @Override
    public void buildContents() {
        notiDTO.setContents("kangyong.choi님이 sam님의 게시물에 댓글을 남겼습니다.");
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
