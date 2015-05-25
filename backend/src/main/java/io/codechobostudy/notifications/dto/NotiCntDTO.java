package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.NotiCnt;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NotiCntDTO {
    private int notiCntIdx;
    private int totalCnt;
    private int boardCnt;
    private int qnaCnt;
    private MockUserDTO userDTO;

    public NotiCntDTO toDTO(NotiCnt notiCnt) {
        if (notiCnt == null) {
            notiCnt = new NotiCnt();
        }
        NotiCntDTO notiCntDTO = new NotiCntDTO();
        notiCntDTO.notiCntIdx = notiCnt.getNotiCntIdx();
        notiCntDTO.totalCnt = notiCnt.getTotalCnt();
        notiCntDTO.boardCnt = notiCnt.getBoardCnt();
        notiCntDTO.qnaCnt = notiCnt.getQnaCnt();
        if (notiCnt.getUser() != null){
            notiCntDTO.userDTO = new MockUserDTO().toDTO(notiCnt.getUser());
        }
        return notiCntDTO;
    }


    public NotiCnt toDomain(NotiCntDTO notiCntDTO) {
        NotiCnt notiCnt = new NotiCnt();
        notiCnt.setNotiCntIdx(notiCntDTO.getNotiCntIdx());
        notiCnt.setTotalCnt(notiCntDTO.getTotalCnt());
        notiCnt.setBoardCnt(notiCntDTO.getBoardCnt());
        notiCnt.setQnaCnt(notiCntDTO.getQnaCnt());
        if (notiCntDTO.getUserDTO() != null){
            notiCnt.setUser(new MockUserDTO().toDomain(notiCntDTO.getUserDTO()));
        }
        return notiCnt;
    }

    public List<NotiCntDTO> toDTOList(List<NotiCnt> notiCntList){
        List<NotiCntDTO> notiCntDTOList = new ArrayList<>();
        for (NotiCnt notiCnt : notiCntList){
            notiCntDTOList.add(this.toDTO(notiCnt));
        }
        return notiCntDTOList;
    }

    public List<NotiCnt> toDomainList(List<NotiCntDTO> notiCntDTOList){
        List<NotiCnt> notiCntList = new ArrayList<>();
        for (NotiCntDTO notiCntDTO : notiCntDTOList){
            notiCntList.add(this.toDomain(notiCntDTO));
        }
        return notiCntList;
    }
}
