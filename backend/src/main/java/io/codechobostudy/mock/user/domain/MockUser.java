package io.codechobostudy.mock.user.domain;

import io.codechobostudy.notifications.domain.Noti;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class MockUser implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter@Setter
    private int idx;

    @Column
    @Getter@Setter
    private String userId;

    @Column
    @Getter@Setter
    private String userName;

    @OneToMany(mappedBy="users", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @Getter@Setter
    List<Noti> notiList = new ArrayList<>();
}
