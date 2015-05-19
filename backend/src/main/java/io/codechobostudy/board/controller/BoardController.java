package io.codechobostudy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.board.domain.Board;
import io.codechobostudy.board.repository.BoardRepository;
import io.codechobostudy.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = ("/create"),method = RequestMethod.POST)
    public String boardCreate (Board board, Model model){

        model.addAttribute("board", boardService.boardCreate(board));
        return "board create " ;

    }

    @RequestMapping(value = ("/list"),method = RequestMethod.GET)
    public String boardList (Board board, Model model){

        boardService.boardList();
        return "board list " ;

    }

    @RequestMapping(value = ("/show"),method = RequestMethod.GET)
    public String boardShow (Board board, Model model){

        boardService.boardShow(board);
        return "board show " ;

    }


    @RequestMapping(value = ("/update/{id}"),method = RequestMethod.PUT)
    public String boardUpdate (Board board, Model model,
                               @PathVariable("id") Long id){

        boardService.boardUpdate(board);
        return "board update " ;

    }

    @RequestMapping(value = ("/delete/{id}"),method = RequestMethod.DELETE)
    public String boardDelete (Board board, Model model,
                               @PathVariable("id") Long id){

        boardService.boardDelete(board);
        return "board delete " ;

    }

}
