package com.cartoon.tinytips.util.Adapters.Personal.Setting.Management;

import android.graphics.drawable.Drawable;

public class Account {
    private Drawable headPro;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public Drawable getHeadPro() {
        return headPro;
    }

    public Account(Drawable headPro,String nickName){
        this.headPro=headPro;
        this.nickName=nickName;
    }
}
