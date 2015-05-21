package io.codechobostudy.notifications.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.codechobostudy.notifications.domain.RelayNoti;
import io.codechobostudy.notifications.repository.MockNotiRepository;
import io.codechobostudy.notifications.service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/noti")
public class NotiController {
    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;
    @Autowired
    private NotiService notiService;
    @Autowired
    private MockNotiRepository mockNotiRepository;

    @RequestMapping(value="/init")
    @ResponseBody
    public String insertInitData(){
        // TODO: 서버 가동시 메소드 실행시킬수 있는 방법은?
        mockNotiRepository.insertInitData();
        return "Success Insert InitData";
    }

    @RequestMapping(value="/main")
    public ModelAndView main(){
        return new ModelAndView("/notifications/notiMain");
    }

    @RequestMapping(value = "/getNotiData")
    @ResponseBody
    public String getNotiData() {
        String notiData = "";
        try {
            notiData = notiService.getNotiData();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return notiData;
    }

    @RequestMapping(value = "/sample/pushData")
    @ResponseBody
    public String webSocketPushData() throws Exception {
        this.simpMsgTemplate.convertAndSend("/subscribe/notiData", notiService.getNotiView());
        return "success";
    }

    // url이 마음에 안드네..
    @RequestMapping(value = "/call/relayNoti")
    @ResponseBody
    public String relayNoti(@RequestBody RelayNoti relayNoti) {
        try {
            notiService.relayNoti("");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
