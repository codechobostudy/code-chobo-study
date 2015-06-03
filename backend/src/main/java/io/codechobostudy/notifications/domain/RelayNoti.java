package io.codechobostudy.notifications.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RelayNoti {
    //TODO: Domain이 아니므로 DTO로 변경
    private String module;
    private String primaryKey;
}
