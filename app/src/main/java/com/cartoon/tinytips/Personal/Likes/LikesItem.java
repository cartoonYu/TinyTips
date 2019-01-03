package com.cartoon.tinytips.Personal.Likes;

public class LikesItem {
    private String Title;
    private String Tags;
    private String Time;

    public LikesItem(String title,String tags,String time){
        this.Title = title;
        this.Tags = tags;
        this.Time = time;
    }

    public String getTags() {
        return Tags;
    }

    public String getTime() {
        return Time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public void setTime(String time) {
        Time = time;
    }
}
