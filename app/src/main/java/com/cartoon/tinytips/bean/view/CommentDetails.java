package com.cartoon.tinytips.bean.view;

import com.cartoon.tinytips.ValueCallBack;

import java.io.File;
import java.util.List;

/**
 * 评论详情
 * @author cartoon
 * @version 1.0
 */

public class CommentDetails {

    private long noteId;       //笔记编号

    private long userId;        //用户id

    private String date;        //时间

    private String details;     //评论详情

    private File headPortrait;  //头像

    private String headPortraitName; //头像文件名

    private String headPortraitResource;    //头像资源

    private String nickName;  //昵称

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
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

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHeadPortraitResource() {
        return headPortraitResource;
    }

    public void setHeadPortraitResource(String headPortraitResource) {
        this.headPortraitResource = headPortraitResource;
    }

    public void setHeadPortrait(File headPortrait) {
        this.headPortrait = headPortrait;
    }

    public File getHeadPortrait() {
        return headPortrait;
    }

    public String getHeadPortraitName() {
        return headPortraitName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setHeadPortraitName(String headPortraitName) {
        this.headPortraitName = headPortraitName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
