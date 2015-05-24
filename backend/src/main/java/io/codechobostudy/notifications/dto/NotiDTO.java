package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.Noti;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class NotiDTO {
    @Getter@Setter
    private int notiNo;

    @Getter@Setter
    private String contents;

    @Getter@Setter
    private String url;

    @Getter@Setter
    private String module;

    @Getter@Setter
    private MockUserDTO usersDTO;

    public NotiDTO toDTO(Noti noti) {
        NotiDTO notiDTO = new NotiDTO();

        notiDTO.notiNo = noti.getNotiNo();
        notiDTO.contents = noti.getContents();
        notiDTO.url = noti.getUrl();
        notiDTO.module = noti.getModule();
        notiDTO.usersDTO = new MockUserDTO().toDTO(noti.getUsers());
        return notiDTO;
    }

    public Noti toDomain(NotiDTO notiDTO){
        Noti noti = new Noti();

        noti.setNotiNo(notiDTO.getNotiNo());
        noti.setContents(notiDTO.getContents());
        noti.setUrl(notiDTO.getUrl());
        noti.setModule(notiDTO.getModule());
        if (notiDTO.getUsersDTO() != null){
            noti.setUsers(new MockUserDTO().toDomain(notiDTO.getUsersDTO()));
        }
        return noti;
    }

    public List<NotiDTO> toDTOList(List<Noti> notiList){
        List<NotiDTO> notiDTOList = new ArrayList<>();
        for(Noti noti : notiList){
            notiDTOList.add(this.toDTO(noti));
        }
        return notiDTOList;
    }

    public List<Noti> toDomainList(List<NotiDTO> notiDTOList){
        List<Noti> notiList = new ArrayList<>();
        for(NotiDTO notiDTO : notiDTOList){
            notiList.add(this.toDomain(notiDTO));
        }
        return notiList;
    }
}
