package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="NOTI")
@Getter @Setter
public class Noti implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notiNo;

    @Column
    private String contents;

    @Column
    private String url;

    @Column
    private String module;

    @ManyToOne
    @JoinColumn(name="USER_IDX")
    private MockUser users;     // TODO: users > user로 변경
}
