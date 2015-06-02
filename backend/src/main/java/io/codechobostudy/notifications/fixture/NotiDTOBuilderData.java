package io.codechobostudy.notifications.fixture;

import io.codechobostudy.notifications.dto.NotiDTO;

public abstract class NotiDTOBuilderData {
    protected NotiDTO notiDTO;

    public NotiDTO buildData() {
        notiDTO = new NotiDTO();
        buildContents();
        buildUrl();
        buildModule();
        buildUserDTO();
        return notiDTO;
    }

    public abstract void buildContents();
    public abstract void buildUrl();
    public abstract void buildModule();
    public abstract void buildUserDTO();
}
