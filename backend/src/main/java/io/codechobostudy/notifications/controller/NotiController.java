package io.codechobostudy.notifications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/noti")
public class NotiController {
    @RequestMapping(value="/main")
    public ModelAndView main(){
        return new ModelAndView("/notifications/notiMain");
    }
}
