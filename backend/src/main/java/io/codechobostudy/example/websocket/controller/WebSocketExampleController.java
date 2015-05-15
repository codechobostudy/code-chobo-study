package io.codechobostudy.example.websocket.controller;

import io.codechobostudy.example.websocket.domain.Greeting;
import io.codechobostudy.example.websocket.domain.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * WebSocket Example
 *  MainPage Url: http://localhost:8080/example/websocket
 *  PushData Url: http://localhost:8080/example/websocket/pushdata
 */
@Controller
@RequestMapping(value = "/example")
public class WebSocketExampleController {
    @Autowired
    private SimpMessagingTemplate simpMsgTemplate;

    @RequestMapping(value = "/websocket")
    public ModelAndView webSocket() {
        return new ModelAndView("/example/websocket/webSocketExample");
    }

    @MessageMapping(value = "/helloMessageData")
    @SendTo(value="/subscribe/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(2000);
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value = "/websocket/pushdata")
    @ResponseBody
    public String webSocketPushData() throws Exception {
        String content = "Goooooood";
        this.simpMsgTemplate.convertAndSend("/subscribe/greetings", new Greeting(content));
        return "input Data: " + content;
    }
}