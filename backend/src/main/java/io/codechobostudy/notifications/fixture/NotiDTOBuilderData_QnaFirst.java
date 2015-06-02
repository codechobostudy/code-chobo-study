package io.codechobostudy.notifications.fixture;

public class NotiDTOBuilderData_QnaFirst extends NotiDTOBuilderData {
    @Override
    public void buildContents() {
        notiDTO.setContents("sam님이 kangyong.choi 질문답변에 답변을 남겼습니다.");
    }

    @Override
    public void buildUrl() {
        notiDTO.setUrl("http://localhost:8080/noti/main");
    }

    @Override
    public void buildModule() {
        notiDTO.setModule("qna");
    }

    @Override
    public void buildUserDTO() {
        notiDTO.setUserDTO(null);
    }
}
