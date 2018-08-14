package com.cartoon.tinytips.HomePage.Favorite;

public class FavoriteItem {
    private int  userImage;
    private String username;
    private String title;
    private String content;
    private int NumOfFavoirte;
    private int NumOfRecommend;
    private int NumOfCollectoin;
    private int NumOfShare;
    private String time;

    public FavoriteItem(int userImage, String username, String title, String content, int NumOfFavoirte, int NumOfRecommend, int NumOfCollectoin,int NumOfShare,String time){
        this.userImage = userImage;
        this.username = username;
        this.title = title;
        this.content = content;
        this.NumOfRecommend = NumOfRecommend;
        this.NumOfFavoirte = NumOfFavoirte;
        this.NumOfCollectoin = NumOfCollectoin;
        this.NumOfShare = NumOfShare;
        this.time = time;
    }

}
