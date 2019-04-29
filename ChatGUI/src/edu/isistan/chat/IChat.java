package edu.isistan.chat;

public interface IChat {

    void sendMsg(String text);
    void sendMsg(String to, String text);

}
