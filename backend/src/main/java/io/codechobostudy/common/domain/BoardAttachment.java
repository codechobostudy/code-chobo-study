package io.codechobostudy.common.domain;

import io.codechobostudy.board.domain.Board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@ToString
@Entity
@Data
public class BoardAttachment {

	public BoardAttachment() {
	}
	
    public BoardAttachment(FileAttachInfo fileAttachInfo) {
    	this.realFileName = fileAttachInfo.getRealFileName();
    	this.saveFileName = fileAttachInfo.getSaveFileName();
    	this.saveFolder = fileAttachInfo.getSaveFolder();
    	this.fileType = fileAttachInfo.getFileType();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;
    
	@Column
    private String realFileName;
	@Column
    private String saveFileName;
	@Column
    private String saveFolder;
	@Column
    private String fileType;
    
}
