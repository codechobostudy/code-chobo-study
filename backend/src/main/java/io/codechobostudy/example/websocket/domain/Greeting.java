package io.codechobostudy.example.websocket.domain;

public class Greeting {
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}