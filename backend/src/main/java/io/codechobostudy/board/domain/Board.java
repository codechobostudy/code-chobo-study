package io.codechobostudy.board.domain;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "subject", length = 255)
    private String subject;
    @Column(name = "content", length = 500)
    private String content;
    @Column(name = "regName", length = 50)
    private String regName;
    @Column(name = "regDate", length = 50)
    private String regDate;
    @Column(name = "regId", length = 50)
    private String regId;
    private String status="S";
}
