package io.codechobostudy.notifications.domain;

import java.util.List;

public class NotiView {
    private List<Noti> notiList;
    private NotiCnt notiCnt;

    public void setNotiList(List<Noti> notiList) {
        this.notiList = notiList;
    }

    public List<Noti> getNotiList() {
        return notiList;
    }

    public void setNotiCnt(NotiCnt notiCnt) {
        this.notiCnt = notiCnt;
    }

    public NotiCnt getNotiCnt() {
        return notiCnt;
    }
}
