package com.cartoon.tinytips.data.TableNote;

import com.cartoon.tinytips.data.TableComment.Comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by cartoon on 2018/2/1.
 * 笔记类
 */

public class Note implements Serializable{
    private String title;      //标题
    private String date;         //日期
    private String author;     //作者
    /* private String wordDetails;     //文字性笔记内容，长度不确定*/
    private String imageDetails;    //图片性笔记内容，长度不确定

    private String details;
    private String[] classify;       //分类，最多3个
    private boolean isCollect;      //是否收藏



    private boolean isShare;        //是否允许分享
    private long pageView;         //浏览量
    private long comment;           //评论量
    private long pointPraise;      //点赞量
    private long pointDislike;     //点灭量
    private List<Comment> list;    //评论

    public Note(String titile, String date, String author, String imageDetails,String details, String[] classify, boolean isCollect) {
        this.title=titile;
        this.date=date;
        this.author=author;
        this.imageDetails=imageDetails;
        this.details=details;
        this.classify=new String[3];
        this.classify=classify;
        this.isCollect=isCollect;
    }

    public void setPointDislike(long pointDislike) {
        this.pointDislike = pointDislike;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPointPraise(long pointPraise) {
        this.pointPraise = pointPraise;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public boolean isShare() {
        return isShare;
    }

    public List<Comment> getList() {
        return list;
    }

    public long getPageView() {
        return pageView;
    }

    public long getPointDislike() {
        return pointDislike;
    }

    public long getPointPraise() {
        return pointPraise;
    }

    public String getImageDetails() {
        return imageDetails;
    }

    public String getTitle() {
        return title;
    }

   /* public String getWordDetails() {
        return wordDetails;
    }*/

    public String[] getClassify() {
        return classify;
    }

    public void setClassify(String[] classify) {
        this.classify = classify;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setImageDetails(String imageDetails) {
        this.imageDetails = imageDetails;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }

    public void setPageView(long pageView) {
        this.pageView = pageView;
    }

    public void setShare(boolean share) {
        isShare = share;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public void setWordDetails(String wordDetails) {
        this.wordDetails = wordDetails;
    }*/

    public void setComment(long comment) {
        this.comment = comment;
    }

    public long getComment() {
        return comment;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
