package io.codechobostudy.notifications.dto;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.notifications.domain.Watch;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class WatchDTO {
    private int watchIdx;
    private String watchModuleName;
    private int watchModuleIdx;
    private MockUserDTO watchUserDTO;

    public Watch toDomain(WatchDTO watchDTO) {
        Watch watch = new Watch();
        watch.setWatchIdx(watchDTO.getWatchIdx());
        watch.setWatchModuleName(watchDTO.getWatchModuleName());
        watch.setWatchModuleIdx(watchDTO.getWatchModuleIdx());
        if (null != watchDTO.getWatchUserDTO()){
            watch.setWatchUser(new MockUserDTO().toDomain(watchDTO.getWatchUserDTO()));
        }
        return watch;
    }

    public WatchDTO toDTO(Watch watch) {
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchIdx(watch.getWatchIdx());
        watchDTO.setWatchModuleIdx(watch.getWatchModuleIdx());
        watchDTO.setWatchModuleName(watch.getWatchModuleName());
        if (null != watch.getWatchUser()){
            watchDTO.setWatchUserDTO(new MockUserDTO().toDTO(watch.getWatchUser()));
        }
        return watchDTO;
    }
}
