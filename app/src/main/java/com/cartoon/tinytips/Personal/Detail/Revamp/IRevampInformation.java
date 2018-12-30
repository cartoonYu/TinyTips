package com.cartoon.tinytips.Personal.Detail.Revamp;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;

interface IRevampInformation {

    interface View{
        void setToolBarText(String text);
        String getInput();   //获取用户输入信息
        void intentToDetails();   //跳转回详情页
    }

    interface Presenter{
        void initUI(String type);
        void revampInformation(String type);
    }

    interface Model{
        void revampInformation(Information information, ValueCallBack<String> callBack);
    }
}
