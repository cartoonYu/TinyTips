package com.cartoon.tinytips.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class CommentDetails implements Serializable{

    private String nickName;    //昵称

    private String date;        //时间

    private File headPro;

    private String details;     //评论详情

    private long userId;

    private long noteId;       //笔记编号

    public CommentDetails(){

    }

    public long getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public long getNoteId() {
        return noteId;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public File getHeadPro() {
        return headPro;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setHeadPro(File headPro) {
        this.headPro = headPro;
    }

}
