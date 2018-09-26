package com.cartoon.tinytips.HomePage.Hot;

public class HotItem {
    private String title;
    private String content;
    private int numOfClick;

    public HotItem(String title,String content,int numOfClick){
        this.title = title;
        this.content = content;
        this.numOfClick = numOfClick;
    }
    public int getNumOfClick() {
        return numOfClick;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
