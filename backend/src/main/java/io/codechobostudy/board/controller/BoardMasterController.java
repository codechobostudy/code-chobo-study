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

        model.addAttribute("boardMaster", boardMasterService.boardCreate(master));
        return "boardMaster create " ;

    }

    @RequestMapping(value = ("/list"),method = RequestMethod.GET)
    public String boardList (BoardMaster master, Model model){

        boardMasterService.boardList();
        return "boardMaster list " ;

    }

    @RequestMapping(value = ("/show"),method = RequestMethod.GET)
    public String boardShow (BoardMaster master, Model model){

        boardMasterService.boardShow(master);
        return "boardMaster show " ;

    }


    @RequestMapping(value = ("/update/{id}"),method = RequestMethod.PUT)
    public String boardUpdate (BoardMaster master, Model model,
                               @PathVariable("id") Long id){

        boardMasterService.boardUpdate(master);
        return "boardMaster update " ;

    }

    @RequestMapping(value = ("/delete/{id}"),method = RequestMethod.DELETE)
    public String boardDelete (BoardMaster master, Model model,
                               @PathVariable("id") Long id){

        boardMasterService.boardDelete(master);
        return "boaboardMasterrd delete " ;

    }
}
