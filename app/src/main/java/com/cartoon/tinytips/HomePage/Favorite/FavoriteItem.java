package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.bean.table.Note;

import java.io.File;
import java.util.Map;

public class FavoriteItem {

    private File userImage;

    private Note note;

    private Map<String,Integer> numOfSocial;

    private Map<String,Boolean> isClick;

    public FavoriteItem(){
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public Map<String, Integer> getNumOfSocial() {
        return numOfSocial;
    }

    public void setNumOfSocial(Map<String, Integer> numOfSocial) {
        this.numOfSocial = numOfSocial;
    }

    public Map<String, Boolean> getIsClick() {
        return isClick;
    }

    public void setIsClick(Map<String, Boolean> isClick) {
        this.isClick = isClick;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

}
