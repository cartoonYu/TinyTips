package com.cartoon.tinytips.util.Adapters.Personal.Collect;

import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;

import java.util.Map;

public class Collect {

    private Note note;

    private Information information;

    private Map<String,Integer> social;

    private Map<String,Boolean> isClick;

    public void setNote(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public void setSocial(Map<String, Integer> social) {
        this.social = social;
    }

    public Map<String, Integer> getSocial() {
        return social;
    }

    public Map<String, Boolean> getIsClick() {
        return isClick;
    }

    public void setIsClick(Map<String, Boolean> isClick) {
        this.isClick = isClick;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Information getInformation() {
        return information;
    }
}
