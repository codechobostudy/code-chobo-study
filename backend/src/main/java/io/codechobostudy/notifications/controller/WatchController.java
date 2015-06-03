package io.codechobostudy.notifications.controller;

import io.codechobostudy.mock.user.dto.MockUserDTO;
import io.codechobostudy.mock.user.service.MockUserService;
import io.codechobostudy.notifications.dto.WatchDTO;
import io.codechobostudy.notifications.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/watch")
public class WatchController {
    @Autowired
    WatchService watchService;
    @Autowired
    MockUserService userService;

    @RequestMapping(value = "/status")
    @ResponseBody
    public String watchStatus(@RequestBody WatchDTO watchDTO){
        MockUserDTO userDTO = userService.getUser("Id_jinhyun");
        return watchService.watchStatus(watchDTO, userDTO);
    }

    @RequestMapping(value = "/setup")
    @ResponseBody
    public String setupWatch(@RequestBody WatchDTO watchDTO){
        MockUserDTO userDTO = userService.getUser("Id_jinhyun");
        watchService.setupWatch(watchDTO, userDTO);
        return "success";
    }
}
