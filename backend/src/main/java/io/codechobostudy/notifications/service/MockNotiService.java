package io.codechobostudy.notifications.service;

import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import io.codechobostudy.notifications.fixture.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockNotiService {
    @Autowired
    private MockUserRepository mockUserRepository;
    @Autowired
    private NotiService notiService;

    public void insertInitData_NotiAndNotiCnt() {
        insertInitData_Noti();
        insertInitData_NotiCnt();
    }

    public void insertInitData_Noti() {
        MockUserDTO userDTO_1 = new UserDTOBuilderData_First().buildData();
        MockUserDTO userDTO_2 = new UserDTOBuilderData_Second().buildData();

        MockUser dbUser1 = mockUserRepository.findByUserId(userDTO_1.getUserId());
        MockUser dbUser2 = mockUserRepository.findByUserId(userDTO_2.getUserId());

        notiService.saveNoti(new NotiDTOBuilderData_BoardFirst().buildData(), userDTO_1.toDTO(dbUser1));
        notiService.saveNoti(new NotiDTOBuilderData_BoardSecond().buildData(), userDTO_1.toDTO(dbUser1));
        notiService.saveNoti(new NotiDTOBuilderData_BoardThird().buildData(), userDTO_1.toDTO(dbUser1));
        notiService.saveNoti(new NotiDTOBuilderData_QnaFirst().buildData(), userDTO_2.toDTO(dbUser2));
    }

    public void insertInitData_NotiCnt() {
        List<MockUser> userList = mockUserRepository.findAll();
        List<MockUserDTO> userDTOList = new MockUserDTO().toDTOList(userList);

        for (MockUserDTO userDTO : userDTOList){
            notiService.saveNotiCnt(userDTO);
        }
    }
}
