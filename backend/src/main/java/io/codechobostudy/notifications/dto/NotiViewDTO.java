package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Todo: Page와 Json용으로 만들었는데 클래스의 용도가 명확하지 않아서 해결방안 필요
public class NotiViewDTO {
    public NotiViewDTO() {}
    public NotiViewDTO(NotiDTO notiDTO, NotiCntDTO notiCntDTO) {
        this.notiCntDTO = notiCntDTO;
        this.notiDTO = notiDTO;
    }

    @Getter@Setter
    private List<NotiDTO> notiList;

    @Getter@Setter
    private NotiCntDTO notiCntDTO;

    @Getter@Setter
    private MockUserDTO userDTO;

    @Getter@Setter
    private NotiDTO notiDTO;
}
