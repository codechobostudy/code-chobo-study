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


    public Board create(Board board){
        return boardRepository.save(board);
    }

    public List<Board> getList(){
        return  boardRepository.findAll();
    }

}
