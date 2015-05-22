package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;

import java.util.List;

// Todo: Page와 Json용으로 만들었는데 클래스의 용도가 명확하지 않아서 해결방안 필요
public class NotiView {
    public NotiView() {}

    public NotiView(Noti noti, NotiCnt notiCnt) {
        this.notiCnt = notiCnt;
        this.noti = noti;
    }

    private List<Noti> notiList;
    private NotiCnt notiCnt;
    private MockUser user;
    private Noti noti;

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

    public MockUser getUser() {
        return user;
    }

    public void setUser(MockUser user) {
        this.user = user;
    }

    public Noti getNoti() {
        return noti;
    }

    public void setNoti(Noti noti) {
        this.noti = noti;
    }
}
