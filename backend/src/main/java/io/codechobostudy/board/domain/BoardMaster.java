package io.codechobostudy.board.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BoardMaster {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;
    @Column
    private String boardNm;
    @Column
    private String masterId;
    @Column
    private String boardEtc;
    @Column
    private String boardType;
    @Column
    private String status="S";
}
