package io.codechobostudy.common.service;

import java.util.List;

import io.codechobostudy.board.domain.Board;
import io.codechobostudy.common.domain.BoardAttachment;
import io.codechobostudy.common.domain.FileAttachInfo;
import io.codechobostudy.common.repository.BoardAttachmentRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class FileUploadService {

	@Autowired
    private BoardAttachmentRepository baordaAttachmentRepository;

	//TODO
	public boolean isValidFile(FileAttachInfo fileAttachInfo) {
		return true;
	}

	//TODO
	public FileAttachInfo uploadFile(FileAttachInfo fileAttachInfo) {
		MultipartFile multipartFile = fileAttachInfo.getAttachFile();
		//upload
		
		fileAttachInfo.setRealFileName(multipartFile.getOriginalFilename());
		fileAttachInfo.setFileType(multipartFile.getContentType());
		fileAttachInfo.setFileSize(multipartFile.getSize());	
		fileAttachInfo.setAttachFile(null);
		
		return fileAttachInfo;
	}
	
	/**
	 * boardType(or id) 에 따라 별도의 path로 파일을 update한다.
	 * @param fileAttachInfo
	 * @return
	 */
	public BoardAttachment addFileAttachInfo(FileAttachInfo fileAttachInfo) {
		//add
		BoardAttachment boardAttachment= new BoardAttachment(fileAttachInfo);
		baordaAttachmentRepository.save(boardAttachment);
		
		return boardAttachment;
	}

	public List<BoardAttachment> getBoardAttachFileList() {
		List<BoardAttachment> arlBoardAttachments = baordaAttachmentRepository.findAll();
		return arlBoardAttachments;
	}
	
	public List<BoardAttachment> getBoardAttachFile(FileAttachInfo fileAttachInfo) {
		List<BoardAttachment> arlBoardAttachments = baordaAttachmentRepository.findAll();
		return arlBoardAttachments;
	}
	
	

    public void boardDelete(BoardAttachment boardAttachment){
        BoardAttachment findOneBoard = baordaAttachmentRepository.findOne(boardAttachment.getFileId());
//TODO
//        board.setStatus("D");
//        board.setId(findOneBoard.getId());
//
//        boardRepository.save(board);
    }
    
	


}
