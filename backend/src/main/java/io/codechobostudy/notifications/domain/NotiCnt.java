package io.codechobostudy.notifications.domain;

public class NotiCnt {
    private int totalCnt;
    private int boardCnt;
    private int qnaCnt;

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
}
