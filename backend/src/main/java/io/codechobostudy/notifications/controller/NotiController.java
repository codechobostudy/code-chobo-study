package io.codechobostudy.notifications.controller;

import io.codechobostudy.notifications.service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/noti")
public class NotiController {
    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;
    @Autowired
    private NotiService notiService;

    @RequestMapping(value="/main")
    public ModelAndView main(){
        return new ModelAndView("/notifications/notiMain");
    }

    @RequestMapping(value = "/sample/pushData")
    @ResponseBody
    public String webSocketPushData() throws Exception {
        this.simpMsgTemplate.convertAndSend("/subscribe/notiData", notiService.getNotiView());
        return "success";
    }

    @RequestMapping(value = "/call/relayNoti")
    @ResponseBody
    public String relayNoti(@RequestParam(value = "data", required = false) String data) {
        notiService.relayNoti("");
        return "success";
    }
}
