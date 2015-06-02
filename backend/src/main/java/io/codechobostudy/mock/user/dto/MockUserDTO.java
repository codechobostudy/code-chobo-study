package io.codechobostudy.mock.user.dto;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.notifications.dto.NotiDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MockUserDTO {
    private int idx;
    private String userId;
    private String userName;
    List<NotiDTO> notiDTOList = new ArrayList<>();

    public MockUserDTO() {
    }

    public MockUserDTO(String userId) {
        this.userId = userId;
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
