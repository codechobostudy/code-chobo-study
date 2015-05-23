package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;

import javax.persistence.*;

@Entity
@Table(name="NOTI")
public class Noti implements Cloneable {
    public Noti() {}

    public Noti(String contents) {
        this.contents = contents;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notiNo;

    @Column
    private String contents;

    @Column
    private String url;

    @Column
    private String module;

    @ManyToOne
    @JoinColumn(name="USER_IDX")
    private MockUser users;     // TODO: users > users로 변경

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public int getNotiNo() {
        return notiNo;
    }

    public void setNotiNo(int notiNo) {
        this.notiNo = notiNo;
    }

    public MockUser getUsers() {
        return users;
    }

    public void setUsers(MockUser users) {
        this.users = users;
    }
}
