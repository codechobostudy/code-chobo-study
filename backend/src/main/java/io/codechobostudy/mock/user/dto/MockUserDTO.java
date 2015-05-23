package io.codechobostudy.mock.user.dto;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.dto.NotiDTO;

import java.util.ArrayList;
import java.util.List;

public class MockUserDTO {
    private int idx;
    private String userId;
    private String userName;
    List<NotiDTO> notiDTOList = new ArrayList<>();

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<NotiDTO> getNotiDTOList() {
        return notiDTOList;
    }

    public void setNotiDTOList(List<NotiDTO> notiDTOList) {
        this.notiDTOList = notiDTOList;
    }

    public MockUserDTO toDTO(MockUser user){
        MockUserDTO mockUserDTO = new MockUserDTO();
        mockUserDTO.idx = user.getIdx();
        mockUserDTO.userId = user.getUserId();
        mockUserDTO.userName = user.getUserName();
//        mockUserDTO.notiDTOList = new NotiDTO().toDTOList(user.getNotiList());  // TODO: error message: org.hibernate.LazyInitializationException
        mockUserDTO.notiDTOList = null;
        return mockUserDTO;
    }

    public MockUser toDomain(MockUserDTO userDTO){
        MockUser mockUser = new MockUser();
        mockUser.setIdx(userDTO.getIdx());
        mockUser.setUserId(userDTO.getUserId());
        mockUser.setUserName(userDTO.getUserName());
        if (userDTO.getNotiDTOList() != null){
            mockUser.setNotiList(new NotiDTO().toDomainList(userDTO.getNotiDTOList()));
        }
        return mockUser;
    }

    public List<MockUserDTO> toDTOList(List<MockUser> userList){
        List<MockUserDTO> userDTOList = new ArrayList<>();

        for (MockUser user : userList){
            userDTOList.add(this.toDTO(user));
        }

        return userDTOList;
    }
}
