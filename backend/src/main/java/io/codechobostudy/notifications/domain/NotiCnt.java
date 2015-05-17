package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="NOTI_CNT")
public class NotiCnt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notiCntIdx;

    @Column
    private int totalCnt;

    @Column
    private int boardCnt;

    @Column
    private int qnaCnt;

    /*
    // Noti의 userId 와 매핑하는 방법은?
    @OneToMany(mappedBy = "notiCnts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Noti> notiList = new ArrayList<>();
    */

    @OneToOne
    private MockUser user;

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setBoardCnt(int boardCnt) {
        this.boardCnt = boardCnt;
    }

    public int getBoardCnt() {
        return boardCnt;
    }

    public void setQnaCnt(int qnaCnt) {
        this.qnaCnt = qnaCnt;
    }

    public int getQnaCnt() {
        return qnaCnt;
    }

    public int getNotiCntIdx() {
        return notiCntIdx;
    }

    public void setNotiCntIdx(int notiCntIdx) {
        this.notiCntIdx = notiCntIdx;
    }

    public MockUser getUser() {
        return user;
    }

    public void setUser(MockUser user) {
        this.user = user;
    }
}
