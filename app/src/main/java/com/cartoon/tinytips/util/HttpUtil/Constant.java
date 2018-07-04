package com.cartoon.tinytips.util.HttpUtil;

public class Constant {
    //寻找java后端对应接口的位置
    public static String URL = "http://192.168.1.109:8080/TinyTips/"; // IP地址改为服务器的IP

    public static String homePageURL="HomePage/";
    public static String communityURL="Community/";
    public static String personalURL="Personal/";

    public static String URL_HomePage = new StringBuilder(URL).
            append(homePageURL).append("HomePage").toString();

    public static String URL_HomePageDetails = new StringBuilder(URL).
            append(homePageURL).append("Details/").
            append("Details").toString();

    public static String URL_HomePageDetailsRevamp = new StringBuilder(URL).
            append(homePageURL).append("Details/").
            append("Revamp/").append("DetailsRevamp").toString();

    public static String URL_HomePageAddNote = new StringBuilder(URL).
            append(homePageURL).append("AddNote/").append("AddNote").toString();

    public static String URL_CommunityFocus = new StringBuilder(URL).
            append(communityURL).append("Focus").toString();

    public static String URL_CommunityHot = new StringBuilder(URL).
            append(communityURL).append("Hot/").append("Hot").toString();

    public static String URL_CommunityRecommend = new StringBuilder(URL).
            append(communityURL).append("Recommend").toString();

    public static String URL_Personal=new StringBuilder(URL).
            append(personalURL).append("Personal").toString();

    public static String URL_Collect=new StringBuilder(URL).
            append(personalURL).append("Collect/").append("Collect").toString();

    public static String URL_CollectDetails=new StringBuilder(URL).
            append(personalURL).append("Collect/").append("Details/").
            append("CollectDetails").toString();

    public static String URL_Profile=new StringBuilder(URL).
            append(personalURL).append("Profile/").append("Profile").toString();

    public static String URL_RevampResume=new StringBuilder(URL).
            append(personalURL).append("Profile/").append("RevampResume/").
            append("ProfileResume").toString();

    public static String URL_RevampSchool=new StringBuilder(URL).
            append(personalURL).append("Profile/").append("RevampSchool/").
            append("ProfileSchool").toString();

    public static String URL_RevampSignature=new StringBuilder(URL).
            append(personalURL).append("Profile/").append("RevampSignature/").
            append("ProfileSignature").toString();

    public static String URL_Security=new StringBuilder(URL).
            append(personalURL).append("Security/").append("Security").toString();
}
