package com.cartoon.tinytips.HomePage.Recommend;

import android.text.SpannableString;

import java.io.File;
import java.util.List;

public class RecommendItem {
    private File userImage;
    private String username;
    private String title;
    private List<SpannableString> content;
    private int NumOfFavorite;
    private int NumOfRecommend;
    private int NumOfCollection;

    public RecommendItem() {
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(List<SpannableString> content) {
        this.content = content;
    }

    public void setNumOfCollection(int numOfCollection) {
        NumOfCollection = numOfCollection;
    }

    public void setNumOfFavorite(int numOfFavorite) {
        NumOfFavorite = numOfFavorite;
    }

    public void setNumOfRecommend(int numOfRecommend) {
        NumOfRecommend = numOfRecommend;
    }

    public File getUserImage() {
        return userImage;
    }

    public int getNumOfFavorite() {
        return NumOfFavorite;
    }

    public int getNumOfCollection() {
        return NumOfCollection;
    }

    public List<SpannableString> getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfRecommend() {
        return NumOfRecommend;
    }

    public String getUsername() {
        return username;
    }
}
