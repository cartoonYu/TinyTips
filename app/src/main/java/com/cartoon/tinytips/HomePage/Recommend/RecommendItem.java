package com.cartoon.tinytips.HomePage.Recommend;

import android.text.SpannableString;

import com.cartoon.tinytips.bean.Note;

import java.io.File;
import java.util.List;
import java.util.Map;

public class RecommendItem {

    private File userImage;

    private Note note;

    private Map<String,Boolean> isClick;

    private Map<String,Integer> numOfSocial;

    public RecommendItem() {
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public void setIsClick(Map<String, Boolean> isClick) {
        this.isClick = isClick;
    }

    public void setNumOfSocial(Map<String, Integer> numOfSocial) {
        this.numOfSocial = numOfSocial;
    }

    public File getUserImage() {
        return userImage;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Map<String, Boolean> getIsClick() {
        return isClick;
    }

    public Map<String, Integer> getNumOfSocial() {
        return numOfSocial;
    }
}

