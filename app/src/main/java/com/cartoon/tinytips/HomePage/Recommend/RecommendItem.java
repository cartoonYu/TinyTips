package com.cartoon.tinytips.HomePage.Recommend;

import android.text.SpannableString;

import com.cartoon.tinytips.bean.Note;

import java.io.File;
import java.util.List;

public class RecommendItem {

    private File userImage;

    private String username;

    private Note note;

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

    public int getNumOfRecommend() {
        return NumOfRecommend;
    }

    public String getUsername() {
        return username;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}

