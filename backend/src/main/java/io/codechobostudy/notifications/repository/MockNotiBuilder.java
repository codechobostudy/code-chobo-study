package io.codechobostudy.notifications.repository;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.dto.NotiDTO;
import org.springframework.stereotype.Service;

@Service
public class MockNotiBuilder {
    public enum Module {
        BOARD("board"), QNA("qna");
        private String value;
        private Module(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public void validModule(String paramModule) {
        boolean existModule = false;
        for (Module module : Module.values()){
            if (module.value == paramModule){
                existModule = true;
                break;
            }
        }
        if (!existModule){
            throw new IllegalArgumentException();
        }
    }

    public MockUserDTO buildUserData(int dataIdx) {
        MockUserDTO userDTO = new MockUserDTO();
        if (dataIdx == 1) {
            userDTO.setUserId("id_Jinhyun");
            userDTO.setUserName("jinhyun");
        } else if (dataIdx == 2){
            userDTO.setUserId("id_Changhwaoh");
            userDTO.setUserName("changhwaoh");
        } else {
            throw new IllegalArgumentException();
        }
        return userDTO;
    }

    public NotiDTO buildNotiData(int dataIdx, String module){
        validModule(module);
        return getNotiData(dataIdx, module);
    }

    public NotiDTO buildNotiData(int dataIdx, String module, MockUserDTO userDTO){
        validModule(module);
        NotiDTO notiDTO = getNotiData(dataIdx, module);
        notiDTO.setUsersDTO(userDTO);
        return notiDTO;
    }

    private NotiDTO getNotiData(int dataIdx, String module){
        NotiDTO notiDTO = new NotiDTO();
        if (dataIdx == 1){
            notiDTO.setModule(module);
            notiDTO.setContents("jeonyb님이 sukkyu.oh님의 게시물에 댓글을 남겼습니다.");
            notiDTO.setUrl("http://localhost:8080/noti/main");
        } else if (dataIdx == 2){
            notiDTO.setModule(module);
            notiDTO.setContents("changhwaoh, urosaria님이 cdy212님의 게시물에 답변을 남겼습니다.");
            notiDTO.setUrl("http://localhost:8080/noti/main");
        } else if (dataIdx == 3){
            notiDTO.setModule(module);
            notiDTO.setContents("kangyong.choi님이 sam님의 게시물에 댓글을 남겼습니다.");
            notiDTO.setUrl("http://localhost:8080/noti/main");
        } else if (dataIdx == 4){
            notiDTO.setModule(module);
            notiDTO.setContents(">> writer0713님이 youngit 질문답변에 답변을 남겼습니다.");
            notiDTO.setUrl("http://localhost:8080/noti/main");

        } else if (dataIdx == 10){
            notiDTO.setModule(module);
            notiDTO.setContents("sam님이 kangyong.choi 질문답변에 답변을 남겼습니다.");
            notiDTO.setUrl("http://localhost:8080/noti/main");
        } else {
            throw new IllegalArgumentException();
        }
        return notiDTO;
    }
}
