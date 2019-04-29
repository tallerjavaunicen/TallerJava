package edu.isistan.chat;

public interface ChatGUI {

    public void addNewGeneralMsg(String from, String text);
    public void addNewMsg(String from, String text);
    public void addUser(String usr);
    public void removeUser(String usr);
    public void chatWith(String usr);
}
