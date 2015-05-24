package io.codechobostudy.notifications.domain;

import io.codechobostudy.mock.user.domain.MockUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="NOTI_CNT")
public class NotiCnt implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter@Setter
    private int notiCntIdx;

    @Column
    @Getter@Setter
    private int totalCnt;

    @Column
    @Getter@Setter
    private int boardCnt;

    @Column
    @Getter@Setter
    private int qnaCnt;

    /*
    // Noti의 userId 와 매핑하는 방법은?
    @OneToMany(mappedBy = "notiCnts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Noti> notiList = new ArrayList<>();
    */

    @OneToOne
    @Getter@Setter
    private MockUser user;
}
