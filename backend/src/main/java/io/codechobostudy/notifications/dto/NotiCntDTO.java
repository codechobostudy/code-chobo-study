package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.NotiCnt;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotiCntDTO {
    private int notiCntIdx;
    private int totalCnt;
    private int boardCnt;
    private int qnaCnt;
    private MockUserDTO userDTO;

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
        notiCnt.setUser(new MockUserDTO().toDomain(notiCntDTO.getUserDTO()));
        return notiCnt;
    }
}
