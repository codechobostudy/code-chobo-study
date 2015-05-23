package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;

import java.util.List;

// Todo: Page와 Json용으로 만들었는데 클래스의 용도가 명확하지 않아서 해결방안 필요
public class NotiViewDTO {
    public NotiViewDTO() {}
    public NotiViewDTO(NotiDTO notiDTO, NotiCntDTO notiCntDTO) {
        this.notiCntDTO = notiCntDTO;
        this.notiDTO = notiDTO;
    }

    private List<NotiDTO> notiList;
    private NotiCntDTO notiCntDTO;
    private MockUserDTO userDTO;

    private NotiDTO notiDTO;

    public void setNotiList(List<NotiDTO> notiList) {
        this.notiList = notiList;
    }

    public List<NotiDTO> getNotiList() {
        return notiList;
    }

    public void setNotiCnt(NotiCntDTO notiCntDTO) {
        this.notiCntDTO = notiCntDTO;
    }

    public NotiCntDTO getNotiCnt() {
        return notiCntDTO;
    }

    public MockUserDTO getUser() {
        return userDTO;
    }

    public void setUserDTO(MockUserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public NotiDTO getNotiDTO() {
        return notiDTO;
    }

    public void setNoti(NotiDTO notiDTO) {
        this.notiDTO = notiDTO;
    }
}
