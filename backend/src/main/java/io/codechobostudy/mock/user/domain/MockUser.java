package io.codechobostudy.mock.user.domain;

import io.codechobostudy.notifications.domain.Noti;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class MockUser implements Cloneable {
    public MockUser(){}

    public MockUser(String userName) {
        this.userName = userName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column
    private String userId;

    @Column
    private String userName;

    @OneToMany(mappedBy="users", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    List<Noti> notiList = new ArrayList<>();

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public List<Noti> getNotiList() {
        return notiList;
    }

    public void setNotiList(List<Noti> notiList) {
        this.notiList = notiList;
    }

    public MockUser clone()throws CloneNotSupportedException{
        return (MockUser) super.clone();
    }
}
