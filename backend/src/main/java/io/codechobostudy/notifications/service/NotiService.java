package io.codechobostudy.notifications.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.notifications.domain.Noti;
import io.codechobostudy.notifications.domain.NotiCnt;
import io.codechobostudy.notifications.domain.NotiView;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotiService {
    private NotiView notiView;

    public NotiView getNotiView() {
        this.notiView = initData();
        return notiView;
    }

    public String getNotiViewJsonString() throws IOException {
        NotiView notiView = getNotiView();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(notiView);
    }

    // TODO: Refactor (Temp Source)
    public static String firstContents = "jinhyun님이 changhwaoh님의 게시물에 댓글을 남겼습니다.";
    public static String secondContents = "sam님이 youngit님의 게시물에 답변을 남겼습니다.";
    public static int totalCnt = 6;
    public NotiView initData() {
        NotiView notiView = new NotiView();
        NotiCnt notiCnt = new NotiCnt();
        List<Noti> notiList = new ArrayList<>();

        Noti noti = new Noti();
        noti.setContents(firstContents);
        noti.setUrl("http://localhost:8080/noti/main");
        noti.setModule("board");
        notiList.add(noti);

        noti = new Noti();
        noti.setContents(secondContents);
        noti.setUrl("http://localhost:8080/noti/main");
        noti.setModule("board");
        notiList.add(noti);

        notiCnt.setTotalCnt(totalCnt);
        notiCnt.setBoardCnt(5);
        notiCnt.setQnaCnt(1);

        notiView.setNotiList(notiList);
        notiView.setNotiCnt(notiCnt);
        return notiView;
    }
}
