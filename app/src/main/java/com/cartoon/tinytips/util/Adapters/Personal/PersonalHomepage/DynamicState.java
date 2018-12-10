package com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage;

import com.cartoon.tinytips.bean.Note;

import java.util.Map;

public class DynamicState {

    private Note note;
    private Map<String,Integer> numOfSocial;
    private Map<String,Boolean> isClick;

    public DynamicState(){

    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public void setIsClick(Map<String, Boolean> isClick) {
        this.isClick = isClick;
    }

    public Map<String, Boolean> getIsClick() {
        return isClick;
    }


    public void setNumOfSocial(Map<String, Integer> numOfSocial) {
        this.numOfSocial = numOfSocial;
    }

    public Map<String, Integer> getNumOfSocial() {
        return numOfSocial;
    }
}
