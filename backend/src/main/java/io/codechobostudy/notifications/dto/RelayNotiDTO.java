package io.codechobostudy.notifications.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RelayNotiDTO {
    private String module;
    private int primaryKey;

    public WatchDTO toWatchDTO(RelayNotiDTO relayNotiDTO) {
        WatchDTO watchDTO = new WatchDTO();
        watchDTO.setWatchModuleIdx(relayNotiDTO.getPrimaryKey());
        watchDTO.setWatchModuleName(relayNotiDTO.getModule());
        return watchDTO;
    }
}
