package io.codechobostudy.board.service;

import io.codechobostudy.board.domain.BoardMaster;
import io.codechobostudy.board.repository.BoardMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardMasterService {

    @Autowired
    private BoardMasterRepository boardMasterRepository;


    public Long boardCreate(BoardMaster master){
        boardMasterRepository.save(master);
        return master.getBoardId();
    }

    public List<BoardMaster> boardList(){
        return  boardMasterRepository.findAll();
    }

    public BoardMaster boardShow(BoardMaster master){
        return boardMasterRepository.findOne(master.getBoardId());
    }

    public Long boardUpdate(BoardMaster master){

        BoardMaster findOneBoard = boardMasterRepository.findOne(master.getBoardId());

        master.setBoardId(findOneBoard.getBoardId());

        boardMasterRepository.save(master);

        return master.getBoardId();
    }

    public void boardDelete(BoardMaster master){

        BoardMaster findOneBoard = boardMasterRepository.findOne(master.getBoardId());

        master.setStatus("D");
        master.setBoardId(findOneBoard.getBoardId());

        boardMasterRepository.save(master);
    }
}
