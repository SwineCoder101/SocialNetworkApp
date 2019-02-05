package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import model.Message;

import java.util.*;

@Service
public class RequestService {

    private final static String OVEREXCEEDED_MESSAGE="Message over exceeded 140 characters please re-enter";
    private final static String USER_NON_EXISTENT="User is non-existent";
    private final static int CHAR_LIMIT=140;
    protected static Map<String,User> userMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);
    protected User currentUser;
    private String outputViewStr;


    private boolean userExists(String name){
        return (userMap.containsKey(name));
    }

    private void addUserIfNonExistent(String name){
        if (!userExists(name)){
            currentUser= new User(name);
            logger.info("Adding new user: " + name);
        }
        else{
            currentUser=userMap.get(name);
            logger.info("User: " + name + " is existent!");
        }
    }

    private Boolean validateMessage(Message message){
        if (message.getMessageStr().length() > CHAR_LIMIT) {
            message.setMessageStr(OVEREXCEEDED_MESSAGE);
            logger.error(OVEREXCEEDED_MESSAGE);
            return false;
        }
        return true;
    }

    private List<Message> setOrderforListOfMessages(List<Message> messages){
        messages.sort((m1,m2)-> m2.getTimeStamp().compareTo(m1.getTimeStamp()));
        return messages;
    }

    public void addMessageForUser(String currentUserName, Message message){
        if (validateMessage(message)){
            addUserIfNonExistent(currentUserName);
            currentUser.addMessage(message);

            outputViewStr="Details for current user: " + currentUser.toString();
            logger.info(outputViewStr);
            userMap.put(currentUserName,currentUser);
        }
    }

    public void viewCurrentUserWall(String currentUserName){
        if(userExists(currentUserName)){
            currentUser = userMap.get(currentUserName);
            List<Message> newOrderedMessages = setOrderforListOfMessages(currentUser.getListOfMessages());
            currentUser.setListOfMessages(newOrderedMessages);

            outputViewStr="Details for current user: " + currentUser.toString();
            logger.info(outputViewStr);
        }
        else{
            logger.info(USER_NON_EXISTENT);
        }
    }

    public void setUserToFollowRecipient(String currentUserName, String recipientName){
        if(userExists(currentUserName) && userExists(recipientName)){
            currentUser=userMap.get(currentUserName);
            currentUser.addUserToFollow(recipientName);
            userMap.put(currentUserName,currentUser);

            outputViewStr="Details for current user: " + currentUser.toString();
            logger.info(outputViewStr);
        }
        else{
            logger.info(USER_NON_EXISTENT);
        }
    }

    public void viewAllMessagesFromFollowedUsers(String currentUserName){

        if(userExists(currentUserName)){

            List<Message> allFollowedUserMessages = new ArrayList<>();
            currentUser=userMap.get(currentUserName);

            for(String followedUserName : currentUser.getFollowingUsersList()){
                allFollowedUserMessages.addAll(userMap.get(followedUserName).getListOfMessages());
            }
            allFollowedUserMessages = setOrderforListOfMessages(allFollowedUserMessages);

            outputViewStr="";
            for (Message message : allFollowedUserMessages){
                outputViewStr+=message.toString();
            }
        }
        else{
            logger.info(USER_NON_EXISTENT);
        }
    }

    public String getOutputViewStr(){
        return outputViewStr;
    }

}
