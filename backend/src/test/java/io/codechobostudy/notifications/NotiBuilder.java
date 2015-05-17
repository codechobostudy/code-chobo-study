package io.codechobostudy.notifications;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.domain.Noti;
import org.springframework.stereotype.Service;

@Service
public class NotiBuilder {
    public MockUser buildUserData(int dataIdx) {
        MockUser user = new MockUser();
        if (dataIdx == 1) {
            user.setUserId("id_Jinhyun");
            user.setUserName("jinhyun");
        } else if (dataIdx == 2){
            user.setUserId("id_Changhwaoh");
            user.setUserName("changhwaoh");
        } else {
            throw new IllegalArgumentException();
        }
        return user;
    }

    public Noti buildNotiData(int dataIdx){
        return getNotiData(dataIdx);
    }

    public Noti buildNotiData(int dataIdx, MockUser user){
        Noti noti = getNotiData(dataIdx);
        noti.setUsers(user);
        return noti;
    }

    private Noti getNotiData(int dataIdx){
        Noti noti = new Noti();
        if (dataIdx == 1){
            noti.setModule("board");
            noti.setToUserName("jinhyun");
            noti.setContents("jeonyb님이 sukkyu.oh님의 게시물에 댓글을 남겼습니다.");
            noti.setUrl("http://localhost:8080/noti/main");
        } else if (dataIdx == 2){
            noti.setModule("board");
            noti.setToUserName("jinhyun");
            noti.setContents("changhwaoh, urosaria님이 cdy212님의 게시물에 답변을 남겼습니다.");
            noti.setUrl("http://localhost:8080/noti/main");
        } else if (dataIdx == 3){
            noti.setModule("board");
            noti.setToUserName("jinhyun");
            noti.setContents("kangyong.choi님이 sam님의 게시물에 댓글을 남겼습니다.");
            noti.setUrl("http://localhost:8080/noti/main");
        } else if (dataIdx == 4){
            noti.setModule("qna");
            noti.setToUserName("jinhyun");
            noti.setContents("writer0713님이 youngit 질문답변에 답변을 남겼습니다.");
            noti.setUrl("http://localhost:8080/noti/main");

        } else if (dataIdx == 10){
            noti.setModule("qna");
            noti.setToUserName("changhwaoh");
            noti.setContents("sam님이 kangyong.choi 질문답변에 답변을 남겼습니다.");
            noti.setUrl("http://localhost:8080/noti/main");
        } else {
            throw new IllegalArgumentException();
        }
        return noti;
    }
}
