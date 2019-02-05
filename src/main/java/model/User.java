package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Message> listOfMessages;
    private List<String> followingUsersList;

    public User(String name){
        this.name =name;
        this.listOfMessages=new ArrayList<>();
        this.followingUsersList=new ArrayList<>();
    }

    public void setName(String name){
        this.name=name;
    }

    public void setListOfMessages(List<Message> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }

    public String getName(){
        return this.name;
    }

    public List<Message> getListOfMessages(){
        return this.listOfMessages;
    }

    public List<String> getFollowingUsersList(){
        return this.followingUsersList;
    }

    public void addMessage(Message message){
        this.listOfMessages.add(message);
    }

    public void addUserToFollow(String name){
        this.followingUsersList.add(name);
    }

    public String toString(){
        String logout=this.name + "\n";

        logout+=followingUsersList.toString() + "\n";

        for (Message m : this.listOfMessages){
            logout+= m.toString() + "\n";
        }
        return(logout);
    }


}
