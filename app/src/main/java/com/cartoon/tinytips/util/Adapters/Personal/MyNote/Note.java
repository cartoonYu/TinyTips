package com.cartoon.tinytips.util.Adapters.Personal.MyNote;

import java.util.ArrayList;
import java.util.List;

public class Note {
    private String title;   //标题
    private List<String> tags;
    private String date;

    public Note(String title,String tag1,String tag2,String tag3,String date){
        this.title=title;
        tags=new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getDate() {
        return date;
    }

}
