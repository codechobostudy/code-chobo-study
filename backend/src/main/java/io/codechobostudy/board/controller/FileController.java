package io.codechobostudy.board.controller;

import io.codechobostudy.board.domain.Board;
import io.codechobostudy.board.domain.FileAttachInfo;
import io.codechobostudy.common.service.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardAttach")
public class FileController {
    
    @Autowired
    FileUploadService fileService;
    
	@RequestMapping(value = ("/{boardId}/fileUpload"),method = RequestMethod.POST)
    public FileAttachInfo fileUpload (FileAttachInfo fileAttachInfo, Model model, @PathVariable("boardId") Long boardId){
    	fileService.isValidFile(fileAttachInfo);
    	return fileService.uploadFile(fileAttachInfo);
    }
}
