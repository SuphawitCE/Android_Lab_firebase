package com.example.lab7_home;

public class MsgClass2
{
    private String title;
    private String myMessage;

    public MsgClass2(String title, String myMessage) {
        this.title = title;
        this.myMessage = myMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }
}
