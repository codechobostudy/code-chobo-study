package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.Noti;

import java.util.ArrayList;
import java.util.List;

public class NotiDTO {
    private int notiNo;
    private String contents;
    private String url;
    private String module;
    private MockUserDTO usersDTO;

    public int getNotiNo() {
        return notiNo;
    }

    public void setNotiNo(int notiNo) {
        this.notiNo = notiNo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public MockUserDTO getUsersDTO() {
        return usersDTO;
    }

    public void setUsersDTO(MockUserDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

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
