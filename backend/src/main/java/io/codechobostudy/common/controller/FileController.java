package io.codechobostudy.common.controller;

import io.codechobostudy.board.domain.Board;
import io.codechobostudy.common.domain.BoardAttachment;
import io.codechobostudy.common.domain.FileAttachInfo;
import io.codechobostudy.common.service.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    FileUploadService fileService;
    
	@RequestMapping(value = ("/{boardId}/fileUpload"),method = RequestMethod.POST)
    public BoardAttachment fileUpload (FileAttachInfo fileAttachInfo, Model model, @PathVariable("boardId") Long boardId){
    	FileAttachInfo fileSavedInfo = fileService.uploadFile(fileAttachInfo);
    	
    	return fileService.addFileAttachInfo(fileSavedInfo);
    }
	
	
	
	@RequestMapping(value = ("/{boardId}/attachment/create"))
    public ModelAndView boardCreatePage (Board board, Model model, @PathVariable("boardId") Long boardId){
        board.setBoardId(boardId);
        return new ModelAndView("/board/attachment/create");

    }
	
}
