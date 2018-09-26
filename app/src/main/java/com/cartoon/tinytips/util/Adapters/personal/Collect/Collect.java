package com.cartoon.tinytips.util.Adapters.Personal.Collect;

import android.graphics.drawable.Drawable;

public class Collect {
    private String title;
    private Drawable headPro;
    private String nickName;
    private int like;
    private int comment;
    private int collect;
    private int forward;

    public Collect(String title,Drawable headPro,String nickName,int like,int comment,int collect,int forward){
        this.title=title;
        this.headPro=headPro;
        this.nickName=nickName;
        this.like=like;
        this.comment=comment;
        this.collect=collect;
        this.forward=forward;
    }
    public String getTitle() {
        return title;
    }

    public Drawable getHeadPro() {
        return headPro;
    }

    public String getNickName() {
        return nickName;
    }

    public int getLike() {
        return like;
    }

    public int getComment() {
        return comment;
    }

    public int getCollect() {
        return collect;
    }

    public int getForward() {
        return forward;
    }
}
