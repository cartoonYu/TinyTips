package com.cartoon.tinytips.HomePage.Favorite;

import android.text.SpannableString;

import java.io.File;
import java.util.List;

public class FavoriteItem {
    private File userImage;
    private String userName;
    private String title;
    private List<SpannableString> content;
    private int NumOfFavorite;
    private int NumOfRecommend;
    private int NumOfCollection;
    private int NumOfShare;
    private String time;

    public FavoriteItem(){
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfRecommend() {
        return NumOfRecommend;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(List<SpannableString> content) {
        this.content = content;
    }

    public void setNumOfRecommend(int numOfRecommend) {
        NumOfRecommend = numOfRecommend;
    }

    public File getUserImage() {
        return userImage;
    }

    public int getNumOfCollection() {
        return NumOfCollection;
    }

    public int getNumOfFavorite() {
        return NumOfFavorite;
    }

    public List<SpannableString> getContent() {
        return content;
    }

    public int getNumOfShare() {
        return NumOfShare;
    }

    public String getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }

    public void setNumOfFavorite(int numOfFavorite) {
        NumOfFavorite = numOfFavorite;
    }

    public void setNumOfCollection(int numOfCollection) {
        NumOfCollection = numOfCollection;
    }

    public void setNumOfShare(int numOfShare) {
        NumOfShare = numOfShare;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
