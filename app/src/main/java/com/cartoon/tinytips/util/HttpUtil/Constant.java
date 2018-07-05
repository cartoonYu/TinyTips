package com.cartoon.tinytips.util.HttpUtil;

public class Constant {
    //寻找java后端对应接口的位置
    private static String URL = "http://192.168.1.109:8080/TinyTips/"; // IP地址改为服务器的IP

    private static String homePageURL="homePage/";
    private static String communityURL="community/";
    private static String personalURL="personal/";

    public static String URL_HomePage = new StringBuilder(URL).
            append(homePageURL).append("HomePage").toString();

    public static String URL_HomePageDetails = new StringBuilder(URL).
            append(homePageURL).append("Details").toString();

    public static String URL_HomePageDetailsRevamp = new StringBuilder(URL).
            append(homePageURL).append("DetailsRevamp").toString();

    public static String URL_HomePageAddNote = new StringBuilder(URL).
            append(homePageURL).append("AddNote").toString();

    public static String URL_CommunityFocus = new StringBuilder(URL).
            append(communityURL).append("Focus").toString();

    public static String URL_CommunityHot = new StringBuilder(URL).
            append(communityURL).append("Hot").toString();

    public static String URL_CommunityRecommend = new StringBuilder(URL).
            append(communityURL).append("Recommend").toString();

    public static String URL_Personal=new StringBuilder(URL).
            append(personalURL).append("Personal").toString();

    public static String URL_Collect=new StringBuilder(URL).
            append(personalURL).append("Collect").toString();

    public static String URL_CollectDetails=new StringBuilder(URL).
            append(personalURL).append("CollectDetails").toString();

    public static String URL_Profile=new StringBuilder(URL).
            append(personalURL).append("Profile").toString();

    public static String URL_RevampResume=new StringBuilder(URL).
            append(personalURL).append("ProfileResume").toString();

    public static String URL_RevampSchool=new StringBuilder(URL).
            append(personalURL).append("ProfileSchool").toString();

    public static String URL_RevampSignature=new StringBuilder(URL).
            append(personalURL).append("ProfileSignature").toString();

    public static String URL_Security=new StringBuilder(URL).
            append(personalURL).append("Security").toString();
}
