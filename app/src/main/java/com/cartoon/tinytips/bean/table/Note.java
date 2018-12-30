package com.cartoon.tinytips.bean.table;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Note implements Serializable{

    private long id;       //主键，笔记编号，用于关联用户

    private long userId;     //外键

    private String title;     //标题

    private List<String> wordDetails;    //文字性内容

    private List<File> photoDetails;   //图片性内容

    private Map<String,String> photoSource;   //经过Base64处理的图片,实现类为LinkedHashMap

    private String author;     //作者

    private String date;       //时间

    private List<String> tag;     //标签

    public Note(){
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getWordDetails() {
        return wordDetails;
    }

    public List<File> getPhotoDetails() {
        return photoDetails;
    }

    public Map<String, String> getPhotoSource() {
        return photoSource;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWordDetails(List<String> wordDetails) {
        this.wordDetails = wordDetails;
    }

    public void setPhotoDetails(List<File> photoDetails) {
        this.photoDetails = photoDetails;
    }

    public void setPhotoSource(Map<String, String> photoSource) {
        this.photoSource = photoSource;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

}
