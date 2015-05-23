package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.NotiCnt;

public class NotiCntDTO {
    private int notiCntIdx;
    private int totalCnt;
    private int boardCnt;
    private int qnaCnt;
    private MockUserDTO userDTO;

    public int getNotiCntIdx() {
        return notiCntIdx;
    }

    public void setNotiCntIdx(int notiCntIdx) {
        this.notiCntIdx = notiCntIdx;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getBoardCnt() {
        return boardCnt;
    }

    public void setBoardCnt(int boardCnt) {
        this.boardCnt = boardCnt;
    }

    public int getQnaCnt() {
        return qnaCnt;
    }

    public void setQnaCnt(int qnaCnt) {
        this.qnaCnt = qnaCnt;
    }

    public MockUserDTO getUser() {
        return userDTO;
    }

    public void setUser(MockUserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public NotiCntDTO toDTO(NotiCnt notiCnt) {
        NotiCntDTO notiCntDTO = new NotiCntDTO();
        notiCntDTO.notiCntIdx = notiCnt.getNotiCntIdx();
        notiCntDTO.totalCnt = notiCnt.getTotalCnt();
        notiCntDTO.boardCnt = notiCnt.getBoardCnt();
        notiCntDTO.qnaCnt = notiCnt.getQnaCnt();
        notiCntDTO.userDTO = new MockUserDTO().toDTO(notiCnt.getUser());
        return notiCntDTO;
    }


    public NotiCnt toDomain(NotiCntDTO notiCntDTO) {
        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setNotiCntIdx(notiCntDTO.getNotiCntIdx());
        notiCnt.setTotalCnt(notiCntDTO.getTotalCnt());
        notiCnt.setBoardCnt(notiCntDTO.getBoardCnt());
        notiCnt.setQnaCnt(notiCntDTO.getQnaCnt());
        notiCnt.setUser(new MockUserDTO().toDomain(notiCntDTO.getUser()));
        return notiCnt;
    }
}
