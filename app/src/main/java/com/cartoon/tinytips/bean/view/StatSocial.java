package com.cartoon.tinytips.bean.view;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StatSocial implements Serializable {

    private long userId;        //用户id

    private String headPortraitResource;  //头像

    private String headPortraitName; //头像文件名

    private File headPortrait;

    private String nickName;  //昵称

    private long noteId;       //笔记编号

    private String title;     //标题

    private List<String> wordDetails;    //文字性内容

    private List<File> photo;   //图片性内容

    private Map<String,String> photoSource;   //图片性内容资源

    private String date;       //时间

    private int numOfLove;    //点赞总数

    private List<Integer> loveList;   //点赞列表

    private boolean isLove;    //是否点赞

    private int numOfComment;    //评论总数

    private List<Integer> commentList;   //评论列表

    private boolean isComment;    //是否评论

    private int numOfCollect;    //收藏总数

    private List<Integer> collectList;   //收藏列表

    private boolean isCollect;   //是否收藏

    private int numOfForward;    //转发总数

    private List<Integer> forwardList;   //转发列表

    private boolean isForward;   //是否转发

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setHeadPortraitName(String headPortraitName) {
        this.headPortraitName = headPortraitName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getHeadPortraitName() {
        return headPortraitName;
    }

    public File getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(File headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setHeadPortraitResource(String headPortraitResource) {
        this.headPortraitResource = headPortraitResource;
    }

    public String getHeadPortraitResource() {
        return headPortraitResource;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }

    public long getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfCollect() {
        return numOfCollect;
    }

    public List<String> getWordDetails() {
        return wordDetails;
    }

    public List<File> getPhoto() {
        return photo;
    }

    public void setPhoto(List<File> photo) {
        this.photo = photo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWordDetails(List<String> wordDetails) {
        this.wordDetails = wordDetails;
    }

    public int getNumOfComment() {
        return numOfComment;
    }

    public int getNumOfForward() {
        return numOfForward;
    }

    public void setNumOfLove(int numOfLove) {
        this.numOfLove = numOfLove;
    }

    public int getNumOfLove() {
        return numOfLove;
    }

    public List<Integer> getCollectList() {
        return collectList;
    }

    public List<Integer> getCommentList() {
        return commentList;
    }

    public List<Integer> getForwardList() {
        return forwardList;
    }

    public List<Integer> getLoveList() {
        return loveList;
    }

    public void setCollectList(List<Integer> collectList) {
        this.collectList = collectList;
    }

    public void setCommentList(List<Integer> commentList) {
        this.commentList = commentList;
    }

    public void setForwardList(List<Integer> forwardList) {
        this.forwardList = forwardList;
    }

    public void setLoveList(List<Integer> loveList) {
        this.loveList = loveList;
    }

    public void setNumOfCollect(int numOfCollect) {
        this.numOfCollect = numOfCollect;
    }

    public void setNumOfComment(int numOfComment) {
        this.numOfComment = numOfComment;
    }

    public void setNumOfForward(int numOfForward) {
        this.numOfForward = numOfForward;
    }

    public boolean isComment() {
        return isComment;
    }

    public boolean isLove() {
        return isLove;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public void setLove(boolean love) {
        isLove = love;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }

    public Map<String, String> getPhotoSource() {
        return photoSource;
    }

    public void setPhotoSource(Map<String, String> photoSource) {
        this.photoSource = photoSource;
    }
}
