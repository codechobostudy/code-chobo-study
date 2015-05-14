package io.codechobostudy.websocket.controller;

import io.codechobostudy.websocket.domain.Greeting;
import io.codechobostudy.websocket.domain.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/websocket")
public class WebSocketSampleController {
    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;

    @RequestMapping(value = "/sample")
    public ModelAndView webSocket() {
        return new ModelAndView("/websocket/webSocketSample");
    }

    @MessageMapping(value = "/helloMessageData")
    @SendTo(value="/subscribe/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(2000);
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value = "/sample/pushData")
    @ResponseBody
    public String webSocketPushData() throws Exception {
        String content = "Goooooood";
        this.simpMsgTemplate.convertAndSend("/subscribe/greetings", new Greeting(content));
        return "input Data: " + content;
    }
}