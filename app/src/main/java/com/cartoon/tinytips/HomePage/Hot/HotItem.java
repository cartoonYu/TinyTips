package com.cartoon.tinytips.HomePage.Hot;

import android.text.SpannableString;

import java.util.List;

public class HotItem {

    private int sequence;

    private String title;

    private List<SpannableString> content;

    private int numOfClick;

    public HotItem(){
    }

    public int getSequence() {
        return sequence;
    }

    public int getNumOfClick() {
        return numOfClick;
    }

    public void setContent(List<SpannableString> content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public List<SpannableString> getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumOfClick(int numOfClick) {
        this.numOfClick = numOfClick;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
