package io.codechobostudy.board.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

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
