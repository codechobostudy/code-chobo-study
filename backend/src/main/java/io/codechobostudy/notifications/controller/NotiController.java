package io.codechobostudy.notifications.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.codechobostudy.notifications.domain.RelayNoti;
import io.codechobostudy.notifications.service.MockNotiService;
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
    private NotiService notiService;
    @Autowired
    private MockNotiService mockNotiService;

    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;

    @RequestMapping(value="/main")
    public ModelAndView main(){
        return new ModelAndView("/notifications/notiMain");
    }

    @RequestMapping(value="/admin")
    public ModelAndView admin(){
        return new ModelAndView("/notifications/notiAdmin");
    }

    @RequestMapping(value = "/deleteAllData")
    @ResponseBody
    public String deleteAllData() {
        notiService.deleteAllData();
        return "success";
    }

    @RequestMapping(value = "/insertInitData")
    @ResponseBody
    public String insertInitData() {
        mockNotiService.insertInitData_NotiAndNotiCnt();
        return "success";
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
