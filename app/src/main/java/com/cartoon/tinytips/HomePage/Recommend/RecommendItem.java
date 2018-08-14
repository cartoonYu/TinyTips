package com.cartoon.tinytips.HomePage.Recommend;

public class RecommendItem {
    private int  userImage;
    private String username;
    private String title;
    private String content;
    private int NumOfFavoirte;
    private int NumOfRecommend;
    private int NumOfCollectoin;

    public RecommendItem(int userImage, String username, String title, String content, int NumOfFavoirte, int NumOfRecommend, int NumOfCollectoin) {
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

}
