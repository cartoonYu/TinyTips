package com.cartoon.tinytips.bean;

import java.util.List;
import java.util.Random;

public class PersonalInformation {

    private String headPortrait;  //头像

    private String nickName;  //昵称

    private String account;   //账号

    private long id;      //主键

    private List<String> interesting;   //兴趣

    private String password;  //密码

    private boolean sex;     //性别

    private String school;    //高校

    private String major;     //专业

    private String background;   //学历

    private String resume;    //个人简介

    public PersonalInformation(){
        Random rId=new Random(60);
        id=(rId.nextLong()*1000);
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

    public List<String> getInteresting() {
        return interesting;
    }

    public String getAccount() {
        return account;
    }

    public String getHeadPortrait() {
        return headPortrait;
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

    public void setAccount(String account) {
        this.account = account;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setInteresting(List<String> interesting) {
        this.interesting = interesting;
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

}
