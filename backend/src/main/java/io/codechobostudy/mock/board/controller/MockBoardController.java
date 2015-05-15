package io.codechobostudy.mock.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/board")
public class MockBoardController {
    @RequestMapping(value = "/main")
    public ModelAndView main() {
        return new ModelAndView("/mock/board/boardMain");
    }

    @RequestMapping(value = "registerAnswer")
    @ResponseBody
    public String registerAnswer(){
        return "success, Register Answer";
    }
}
