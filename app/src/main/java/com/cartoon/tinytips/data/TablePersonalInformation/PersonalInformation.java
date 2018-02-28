package com.cartoon.tinytips.data.TablePersonalInformation;

/**
 * Created by cartoon on 2018/2/1.
 * 个人信息类
 */

public class PersonalInformation {
    private String headPortrait;  //头像，数据类型待定
    private String nickName;  //昵称
    private String account;   //账号
    private String school;    //高校
    private String sex;       //性别
    private String resume;    //个人简介
    private String signature; //个性签名
    // 二维码，待定

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAccount() {
        return account;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getResume() {
        return resume;
    }

    public String getSchool() {
        return school;
    }

    public String getSex() {
        return sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
