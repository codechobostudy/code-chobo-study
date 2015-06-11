package io.codechobostudy.board.domain;


import lombok.Data;
import lombok.ToString;

import org.springframework.web.multipart.MultipartFile;
@Data
@ToString
public class FileAttachInfo {
    MultipartFile attachFile;
    
    private String realFileName;
    
    private String saveFileName;
    
    private String saveFolder;
    
    private String fileType;

    private Long fileSize;
    
    private String contentType;
    
}
