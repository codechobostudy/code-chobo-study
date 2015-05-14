package io.codechobostudy.notifications.domain;

public class Noti {
    private String contents;
    private String url;
    private String module;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }
}
