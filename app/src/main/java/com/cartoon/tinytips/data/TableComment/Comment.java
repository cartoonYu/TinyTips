package com.cartoon.tinytips.data.TableComment;

import java.util.Date;

/**
 * Created by cartoon on 2018/2/1.
 * 分享的笔记收到的评论类
 */

public class Comment {
    private String nickName;  //昵称
    private String content;   //评论内容
    private int pointPraise;  //点赞
    private Date date;           //时间
    private int pointDislike; //点灭

    public String getNickName() {
        return nickName;
    }

    public String getContent() {
        return content;
    }

    public int getPointPraise() {
        return pointPraise;
    }

    public Date getDate() {
        return date;
    }

    public int getPointDislike() {
        return pointDislike;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPointPraise(int pointPraise) {
        this.pointPraise = pointPraise;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPointDislike(int pointDislike) {
        this.pointDislike = pointDislike;
    }
}
