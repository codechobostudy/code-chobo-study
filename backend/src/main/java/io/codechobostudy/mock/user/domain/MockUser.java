package io.codechobostudy.mock.user.domain;

import io.codechobostudy.notifications.domain.Noti;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
@Getter @Setter
public class MockUser implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column
    private String userId;

    @Column
    private String userName;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    List<Noti> notiList = new ArrayList<>();
}
