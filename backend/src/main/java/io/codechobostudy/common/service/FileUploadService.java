package io.codechobostudy.common.service;

import io.codechobostudy.board.domain.FileAttachInfo;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadService {
	//TODO
	public boolean isValidFile(FileAttachInfo fileAttachInfo) {
		return true;
	}

	//TODO
	public FileAttachInfo uploadFile(FileAttachInfo fileAttachInfo) {
		MultipartFile multipartFile = fileAttachInfo.getAttachFile();
		//upload
		
		fileAttachInfo.setContentType(multipartFile.getOriginalFilename());
		fileAttachInfo.setContentType(multipartFile.getContentType());
		fileAttachInfo.setFileSize(multipartFile.getSize());	
		fileAttachInfo.setAttachFile(null);
		
		return fileAttachInfo;
	}



}
