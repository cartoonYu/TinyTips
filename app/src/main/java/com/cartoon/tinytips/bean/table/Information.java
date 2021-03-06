package com.cartoon.tinytips.bean.table;

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 个人信息bean类
 *
 * notice
 * 1.属性sex，true为男，false为女
 * 2.属性interest转换成String以符号$进行分隔
 */

public class Information implements Serializable {

    private long id;      //主键，个人信息的唯一标识，自增长

    private String account;   //主键，账号

    private String password;  //密码

    private String date;     //注册时间

    private File headPortrait;  //头像

    private String headPortraitName;  //服务器中头像文件名

    private String headPortraitResource;   //头像经过Base64处理后的字符串

    private String nickName;  //昵称

    private boolean sex;     //性别

    private List<String> interest;   //兴趣

    private String school;    //高校

    private String major;     //专业

    private String background;   //学历

    private String resume;    //个人简介

    private String NumOfNote;   //个人笔记数量

    private String NumOfAttentions;  //关注人数量

    private String NumOfFans;    //粉丝数量


    public Information(){
        setSex(true);
    }

    public Information(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public boolean isSex() {
        return sex;
    }

    public List<String> getInterest() {
        return interest;
    }

    public String getAccount() {
        return account;
    }

    public void setHeadPortrait(File headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getHeadPortraitName() {
        return headPortraitName;
    }

    public String getHeadPortraitResource() {
        return headPortraitResource;
    }

    public String getPassword() {
        return password;
    }

    public String getMajor() {
        return major;
    }

    public String getBackground() {
        return background;
    }

    public String getSchool() {
        return school;
    }

    public String getResume() {
        return resume;
    }

    public String getNumOfAttentions() {
        return NumOfAttentions;
    }

    public String getNumOfNote() {
        return NumOfNote;
    }

    public String getNumOfFans() {
        return NumOfFans;
    }

    public void setNumOfNote(String numOfNote) {
        NumOfNote = numOfNote;
    }

    public void setNumOfFans(String numOfFans) {
        NumOfFans = numOfFans;
    }

    public void setNumOfAttentions(String numOfAttentions) {
        NumOfAttentions = numOfAttentions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public File getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortraitName(String headPortraitName) {
        this.headPortraitName = headPortraitName;
    }

    public void setHeadPortraitResource(String headPortraitResource) {
        this.headPortraitResource = headPortraitResource;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }
}
