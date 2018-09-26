package com.cartoon.tinytips.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Note implements Serializable{

    private long id;       //主键，笔记编号，用于关联用户

    private long userId;     //外键

    private String title;     //标题

    private String wordDetails;    //文字性内容

    private String photoDetails;   //图片性内容

    private String author;     //作者

    private Date date;       //时间

    private List<String> tag;     //标签

    public Note(){
        //时间的赋值
        Random rId=new Random(50);
        id=(rId.nextLong()*1000);
        Random rUserId=new Random(60);
        userId=(rUserId.nextLong()*1000);
        this.date=new Date();
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

    public String getWordDetails() {
        return wordDetails;
    }

    public String getPhotoDetails() {
        return photoDetails;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWordDetails(String wordDetails) {
        this.wordDetails = wordDetails;
    }

    public void setPhotoDetails(String photoDetails) {
        this.photoDetails = photoDetails;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

}
