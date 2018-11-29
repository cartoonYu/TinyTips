package com.cartoon.tinytips.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Default_many {
    private Note note;

    private Comment comment;

    private ArrayList<String> details;

    private ArrayList<String> tags;

    public Note getNote(){
        note.setTitle("网络错误");
        String[] Arr = {"请检查网络","，重新刷新"};
        details = new ArrayList<String>();
        details.add("请检查网络,重新刷新");
        note.setWordDetails(details);
        note.setDate(" ");
        note.setAuthor(" ");
        tags.add(" ");
        tags.add(" ");
        tags.add(" ");
        note.setTag(tags);
        return note;
    }

}


