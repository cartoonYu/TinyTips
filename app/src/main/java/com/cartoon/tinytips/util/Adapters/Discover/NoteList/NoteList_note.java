package com.cartoon.tinytips.util.Adapters.Discover.NoteList;

public class NoteList_note {
    private int  userImage;
    private String username;
    private String title;
    private String content;
    private String NumOfFavoirte;
    private String NumOfRecommend;
    private String NumOfCollectoin;


    public NoteList_note(){

    }
    public NoteList_note(int userImage, String username, String title, String content, String NumOfFavoirte, String NumOfRecommend, String NumOfCollectoin){
        this.userImage = userImage;
        this.username = username;
        this.title = title;
        this.content = content;
        this.NumOfRecommend = NumOfRecommend;
        this.NumOfFavoirte = NumOfFavoirte;
        this.NumOfCollectoin = NumOfCollectoin;
    }

    public String getuserName() {
        return username;
    }

    public int getuserImage() {
        return userImage;
    }

    public String gettitle() {
        return title;
    }

    public String getcontent() {
        return content;
    }

    public String getNumOfRecommend() {
        return NumOfRecommend;
    }

    public String getNumOfFavoirte() {
        return NumOfFavoirte;
    }

    public String getNumOfCollectoin() {
        return NumOfCollectoin;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNumOfCollectoin(String numOfCollectoin) {
        NumOfCollectoin = numOfCollectoin;
    }

    public void setNumOfFavoirte(String numOfFavoirte) {
        NumOfFavoirte = numOfFavoirte;
    }

    public void setNumOfRecommend(String numOfRecommend) {
        NumOfRecommend = numOfRecommend;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }
}
