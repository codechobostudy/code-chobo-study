package io.codechobostudy.notifications.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.dto.NotiCntDTO;
import io.codechobostudy.notifications.dto.NotiDTO;
import io.codechobostudy.notifications.repository.MockNotiBuilder;
import io.codechobostudy.notifications.repository.NotiCntRepository;
import io.codechobostudy.notifications.repository.NotiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockNotiService {
    @Autowired
    private MockUserRepository mockUserRepository;
    @Autowired
    private NotiRepository notiRepository;
    @Autowired
    private NotiCntRepository notiCntRepository;

    @Autowired
    private MockNotiBuilder notiBuilder;
    @Autowired
    private NotiService notiService;

    public void insertInitData_NotiAndNotiCnt() {
        insertInitData_Noti();
        insertInitData_NotiCnt();
    }

    public List<NotiDTO> insertInitData_Noti() {
        // TODO: NotiService.getNotiData 와 같이 수정
        List<NotiDTO> notiDTOList = new ArrayList<>();
        MockUserDTO userDTO1 = new MockUserDTO();
        userDTO1.setUserId(notiBuilder.buildUserData(1).getUserId());
        MockUser user1 = userDTO1.toDomain(userDTO1);

        MockUserDTO userDTO2 = new MockUserDTO();
        userDTO2.setUserId(notiBuilder.buildUserData(2).getUserId());
        MockUser user2 = userDTO1.toDomain(userDTO2);

        MockUser dbUser1 = mockUserRepository.findByUserId(user1.getUserId());
        MockUser dbUser2 = mockUserRepository.findByUserId(user2.getUserId());

        notiDTOList.add(notiService.saveNoti(notiBuilder.buildNotiData(1, "board"), new MockUserDTO().toDTO(dbUser1)));
        notiDTOList.add(notiService.saveNoti(notiBuilder.buildNotiData(2, "board"), new MockUserDTO().toDTO(dbUser1)));
        notiDTOList.add(notiService.saveNoti(notiBuilder.buildNotiData(3, "board"), new MockUserDTO().toDTO(dbUser1)));

        notiDTOList.add(notiService.saveNoti(notiBuilder.buildNotiData(10, "qna"), new MockUserDTO().toDTO(dbUser2)));

        return notiDTOList;
    }

    public List<NotiCntDTO> insertInitData_NotiCnt() {
        // TODO: NotiService.getNotiData 와 같이 수정
        List<NotiCntDTO> notiCntDTOList = new ArrayList<>();
        List<MockUser> userList = mockUserRepository.findAll();
        List<MockUserDTO> userDTOList = new MockUserDTO().toDTOList(userList);

        for (MockUserDTO userDTO : userDTOList){
            notiCntDTOList.add(notiService.saveNotiCnt(userDTO));
        }
        return notiCntDTOList;
    }
}
