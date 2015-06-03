package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="WATCH")
@Setter @Getter
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int watchIdx;

    @Column
    private String watchModuleName;

    @Column
    private int watchModuleIdx;

    @ManyToOne
    @JoinColumn(name="USER_IDX")
    private MockUser watchUser;
}
