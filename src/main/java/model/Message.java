package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Message {

    private String messageStr;
    private Timestamp timeStamp;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public Message(){
        this.messageStr="";
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Message(String messageStr){
        this.messageStr=messageStr;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }


    public void setMessageStr(String messageStr){
        this.messageStr=messageStr;
    }

    public String getMessageStr(){
        return messageStr;
    }

    public Timestamp getTimeStamp(){
        return timeStamp;
    }

    public String toString(){
        return( sdf.format(this.timeStamp) + "    " + this.messageStr + "\n");
    }

}
