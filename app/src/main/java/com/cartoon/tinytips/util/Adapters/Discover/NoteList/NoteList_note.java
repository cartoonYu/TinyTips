package com.cartoon.tinytips.util.Adapters.Discover.NoteList;

public class NoteList_note {
    private int  userImage;
    private String username;
    private String title;
    private String content;
    private int NumOfFavoirte;
    private int NumOfRecommend;
    private int NumOfCollectoin;

    public NoteList_note(int userImage, String username, String title, String content, int NumOfFavoirte, int NumOfRecommend, int NumOfCollectoin){
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

    public int getNumOfRecommend() {
        return NumOfRecommend;
    }

    public int getNumOfFavoirte() {
        return NumOfFavoirte;
    }

    public int getNumOfCollectoin() {
        return NumOfCollectoin;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNumOfCollectoin(int numOfCollectoin) {
        NumOfCollectoin = numOfCollectoin;
    }

    public void setNumOfFavoirte(int numOfFavoirte) {
        NumOfFavoirte = numOfFavoirte;
    }

    public void setNumOfRecommend(int numOfRecommend) {
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
