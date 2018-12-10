package com.cartoon.tinytips.util.Adapters.Comment;

import java.io.File;

public class CommentItem {

    private File userImage;
    private String username;
    private String content;
    private String time;

    public CommentItem(int userImage, String username, String content,String time) {
        this.username = username;
        this.content = content;
        this.time = time;
    }

    public CommentItem(){
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public File getUserImage() {
        return userImage;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }


}
