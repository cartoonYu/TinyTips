package com.cartoon.tinytips.bean;

import java.util.Date;

public class CommentDetails {

    private String nickName;    //昵称，主键，个人信息外键

    private Date date;        //时间，主键

    private String headPro;     //头像，个人信息外键

    private String details;     //评论详情

    private long noteId;       //笔记编号，用于关联用户

    public CommentDetails(){
        //时间的赋值
    }

    public String getNickName() {
        return nickName;
    }

    public long getNoteId() {
        return noteId;
    }

    public Date getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getHeadPro() {
        return headPro;
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

    public void setHeadPro(String headPro) {
        this.headPro = headPro;
    }

}
