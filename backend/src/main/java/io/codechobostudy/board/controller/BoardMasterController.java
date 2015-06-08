package io.codechobostudy.board.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.board.domain.BoardMaster;
import io.codechobostudy.board.service.BoardMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board/master")
public class BoardMasterController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardMasterService boardMasterService;

    @RequestMapping(value = ("/createPage"))
    public ModelAndView boardCreatePage (BoardMaster master, Model model){

        return new ModelAndView("/board/master/create");

    }

    @RequestMapping(value = ("/create"),method = RequestMethod.POST)
    public String boardCreate (BoardMaster master, Model model){

        boardMasterService.boardCreate(master);
        return "boardMaster create " ;

    }

    @RequestMapping(value = ("/list"),method = RequestMethod.GET)
    public ModelAndView boardList (BoardMaster master, Model model){

        model.addAttribute("boardMaster", boardMasterService.boardList());
        return new ModelAndView("/board/master/list");

    }

    @RequestMapping(value = ("/show/{boardId}"),method = RequestMethod.GET)
    public ModelAndView boardShow (@PathVariable("boardId") Long boardId, Model model){

        model.addAttribute("boardMaster", boardMasterService.boardShow(boardId));
        return new ModelAndView("/board/master/show");

    }

    @RequestMapping(value = ("/updatePage/{boardId}"))
    public ModelAndView updatePage (BoardMaster master, Model model, @PathVariable("boardId") Long boardId){

        model.addAttribute("boardMaster", boardMasterService.boardShow(boardId));
        model.addAttribute("boardId", boardId);

        return new ModelAndView("/board/master/update");

    }

    @RequestMapping(value = ("/update/{boardId}"),method = RequestMethod.PUT)
    public String boardUpdate (BoardMaster master, Model model,
                               @PathVariable("boardId") Long boardId){
        master.setBoardId(boardId);
        boardMasterService.boardUpdate(master);
        return "boardMaster update " ;

    }

    @RequestMapping(value = ("/delete/{boardId}"),method = RequestMethod.DELETE)
    public String boardDelete (BoardMaster master, Model model,
                               @PathVariable("boardId") Long boardId){

        master.setBoardId(boardId);
        boardMasterService.boardDelete(master);
        return "boaboardMasterrd delete " ;

    }
}
