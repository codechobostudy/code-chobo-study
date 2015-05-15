package io.codechobostudy.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninContoller {

    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }

}
