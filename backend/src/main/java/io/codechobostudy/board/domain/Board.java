package io.codechobostudy.board.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String subject;
    @Column
    private String content;
    @Column
    private String regName;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;
    @Column
    private String regId;
    @Column
    private String status="S";
    @Column
    private Long boardId;
}
