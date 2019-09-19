package com.example.itaykan.fbfb4;
public class Message {
    public Message()
    {

    }
    private String sender;
    private String msg;

    public Message(String sender, String msg)
    {
        this.sender = sender;
        this.msg = msg;
    }

    public String getSender()
    {
        return this.sender;
    }

    public void setSender(String value)
    {
        this.sender = value;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public void setMsg(String value)
    {
        this.msg = value;
    }
}
