package io.codechobostudy.board.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.ToString;

@ToString
@Entity
public class BoardAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FileId;

    private Long contentId;
    
    private String realFileName;
    
    private String saveFileName;
    
    private String saveFolder;
    
    private String fileType;


    
}
