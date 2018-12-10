package com.cartoon.tinytips.bean;


/**
 * description
 * 社交Bean类
 *
 * notice
 * 1.type有三个值:Collect、Forward、Love
 */

public class Social {

    private String type;

    private long noteId;       //笔记编号

    private long userId;        //用户id

    private String date;        //时间

    public String getType() {
        return type;
    }

    public long getNoteId() {
        return noteId;
    }

    public long getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
