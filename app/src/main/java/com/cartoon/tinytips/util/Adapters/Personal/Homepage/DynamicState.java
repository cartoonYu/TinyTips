package com.cartoon.tinytips.util.Adapters.Personal.Homepage;

public class DynamicState {
    private String title;
    private String date;
    private int like;
    private int comment;
    private int collect;
    private int forward;

    public DynamicState(String title,String date,int like,int comment,int collect,int forward){
        this.title=title;
        this.date=date;
        this.like=like;
        this.comment=comment;
        this.collect=collect;
        this.forward=forward;
    }

    public int getCollect() {
        return collect;
    }

    public int getComment() {
        return comment;
    }

    public int getForward() {
        return forward;
    }

    public int getLike() {
        return like;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

}
