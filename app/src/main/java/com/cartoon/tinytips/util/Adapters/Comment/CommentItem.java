package com.cartoon.tinytips.util.Adapters.Comment;

public class CommentItem {
    private int  userImage;
    private String username;
    private String content;
    private String time;

    public CommentItem(int userImage, String username, String content,String time) {
        this.userImage = userImage;
        this.username = username;
        this.content = content;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getuserName() {
        return username;
    }

    public int getuserImage() {
        return userImage;
    }

    public String getcontent() {
        return content;
    }
}
