package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="NOTI")
public class Noti implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter@Setter
    private int notiNo;

    @Column
    @Getter@Setter
    private String contents;

    @Column
    @Getter@Setter
    private String url;

    @Column
    @Getter@Setter
    private String module;

    @ManyToOne
    @JoinColumn(name="USER_IDX")
    @Getter@Setter
    private MockUser users;     // TODO: users > user로 변경
}
