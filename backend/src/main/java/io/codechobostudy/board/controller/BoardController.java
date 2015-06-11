package io.codechobostudy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.board.domain.Board;
import io.codechobostudy.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = ("/{boardId}/createPage"))
    public ModelAndView boardCreatePage (Board board, Model model, @PathVariable("boardId") Long boardId){

        board.setBoardId(boardId);

        return new ModelAndView("/board/create");

    }

    @RequestMapping(value = ("/{boardId}/create"),method = RequestMethod.POST)
    public String boardCreate (Board board, Model model, @PathVariable("boardId") Long boardId){

        board.setBoardId(boardId);

        model.addAttribute("board", boardService.boardCreate(board));
        return "board create " ;

    }

    @RequestMapping(value = ("/{boardId}/list"),method = RequestMethod.GET)
    public ModelAndView boardList (Board board, Model model, @PathVariable("boardId") Long boardId){

        board.setBoardId(boardId);
        model.addAttribute("board", boardService.boardList());
        model.addAttribute("boardId", boardId);

        return new ModelAndView("/board/list");
    }

    @RequestMapping(value = ("/{boardId}/show/{id}"),method = RequestMethod.GET)
    public ModelAndView boardShow (Board board, Model model, @PathVariable("boardId") Long boardId,@PathVariable("id") Long id){

        board.setBoardId(boardId);

        model.addAttribute("board", boardService.boardShow(id));

        return new ModelAndView("/board/show");

    }

    @RequestMapping(value = ("/{boardId}/updatePage/{id}"))
    public ModelAndView boardUpdatePage (Board board, Model model, @PathVariable("id") Long id
                                        , @PathVariable("boardId") Long boardId){

        board.setId(id);
        model.addAttribute("board", boardService.boardShow(id));
        model.addAttribute("boardId", boardId);
        model.addAttribute("id", id);

        return new ModelAndView("/board/update");

    }

    @RequestMapping(value = ("/{boardId}/update/{id}"),method = RequestMethod.PUT)
    public String boardUpdate (Board board, Model model,
                               @PathVariable("id") Long id,
                               @PathVariable("boardId") Long boardId){

        board.setBoardId(boardId);
        board.setId(id);
        boardService.boardUpdate(board);
        return "board update " ;

    }

    @RequestMapping(value = ("/{boardId}/delete/{id}"),method = RequestMethod.DELETE)
    public String boardDelete (Board board, Model model,
                               @PathVariable("id") Long id,
                               @PathVariable("boardId") Long boardId){
        board.setBoardId(boardId);

        boardService.boardDelete(board);
        return "board delete " ;

    }

}
