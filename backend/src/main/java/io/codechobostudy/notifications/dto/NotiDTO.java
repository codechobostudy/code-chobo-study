package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.Noti;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NotiDTO {
    private int notiNo;
    private String contents;
    private String url;
    private String module;
    private MockUserDTO userDTO;

    public NotiDTO() {
    }

    public NotiDTO(String contents) {
        this.contents = contents;
    }

    public NotiDTO(NotiDTOBuilder builder){
        this.notiNo = builder.notiNo;
        this.contents = builder.contents;
        this.url = builder.url;
        this.module = builder.module;
        this.userDTO = builder.userDTO;
    }

    public NotiDTO toDTO(Noti noti) {
        NotiDTO notiDTO = new NotiDTO();

        notiDTO.notiNo = noti.getNotiNo();
        notiDTO.contents = noti.getContents();
        notiDTO.url = noti.getUrl();
        notiDTO.module = noti.getModule();
        notiDTO.userDTO = new MockUserDTO().toDTO(noti.getUser());
        return notiDTO;
    }

    public Noti toDomain(NotiDTO notiDTO){
        Noti noti = new Noti();

        noti.setNotiNo(notiDTO.getNotiNo());
        noti.setContents(notiDTO.getContents());
        noti.setUrl(notiDTO.getUrl());
        noti.setModule(notiDTO.getModule());
        if (notiDTO.getUserDTO() != null){
            noti.setUser(new MockUserDTO().toDomain(notiDTO.getUserDTO()));
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

    public static class NotiDTOBuilder {
        private int notiNo;
        private String contents;
        private String url;
        private String module;
        private MockUserDTO userDTO;

        public NotiDTOBuilder withContents(String contents){
            this.contents = contents;
            return this;
        }

        public NotiDTOBuilder withUrl(String url){
            this.url = url;
            return this;
        }

        public NotiDTOBuilder withModule(String module){
            this.module = module;
            return this;
        }

        public NotiDTOBuilder withUserDTO(MockUserDTO userDTO){
            this.userDTO = userDTO;
            return this;
        }

        private void validNotiDTOObject() {
            // do your stuff here

            // Module Name
        }

        public NotiDTO build() {
            NotiDTO notiDTO = new NotiDTO(this);
            validNotiDTOObject();
            return notiDTO;
        }
    }
}
