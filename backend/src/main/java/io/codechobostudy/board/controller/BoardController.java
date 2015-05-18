package io.codechobostudy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.board.domain.Board;
import io.codechobostudy.board.repository.BoardRepository;
import io.codechobostudy.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @RequestMapping(value = ("/create"),method = RequestMethod.GET)
    public String createBoard (Board board){

        Board board1 = new Board();

        board1.setRegDate("2015-05-18");
        board1.setRegId("urosaria");
        board1.setRegName("Yu rosaria");
        board1.setSubject("title");
        board1.setContent("content");

        boardRepository.save(board1);

        System.out.println("test: " + boardRepository.findAll());

        return "board create " ;


    }


}
