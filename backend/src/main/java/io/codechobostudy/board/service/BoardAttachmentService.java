package io.codechobostudy.board.service;

import org.springframework.beans.factory.annotation.Autowired;

import io.codechobostudy.board.domain.BoardAttachment;
import io.codechobostudy.board.domain.FileAttachInfo;
import io.codechobostudy.common.service.FileUploadService;

public class BoardAttachmentService {

	@Autowired
	FileUploadService fileService;
	
	public void addBoardAttachment(FileAttachInfo fileAttachInfo) {
		if(fileService.isValidFile(fileAttachInfo)){
			fileService.uploadFile(fileAttachInfo);
		}else{
			throw new RuntimeException();//TODO
		};
	}

}
