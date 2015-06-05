package io.codechobostudy.board.service;

import io.codechobostudy.board.domain.Board;
import io.codechobostudy.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    public Long boardCreate(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> boardList(){
        return  boardRepository.findAll();
    }

    public Board boardShow(Long id){
        return boardRepository.findOne(id);
    }

    public Long boardUpdate(Board board){

        Board findOneBoard = boardRepository.findOne(board.getId());

        board.setId(findOneBoard.getId());

        boardRepository.save(board);

        return board.getId();
    }

    public void boardDelete(Board board){

        Board findOneBoard = boardRepository.findOne(board.getId());

        board.setStatus("D");
        board.setId(findOneBoard.getId());

        boardRepository.save(board);
    }

}
