package com.cartoon.tinytips.HomePage.Hot;

import android.text.SpannableString;

import com.cartoon.tinytips.bean.Note;

import java.util.List;

public class HotItem {

    private int sequence;

    private Note note;

    private int numOfClick;

    public HotItem(){
    }

    public int getSequence() {
        return sequence;
    }

    public int getNumOfClick() {
        return numOfClick;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setNumOfClick(int numOfClick) {
        this.numOfClick = numOfClick;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
