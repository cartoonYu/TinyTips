package com.cartoon.tinytips.HomePage.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;

/**
 * Created by cartoon on 2018/2/11.
 * HomePageDetails的公共接口
 * View类接入IHomePageDetails.view，Presenter类接入IHomePageDetails.Presenter
 * 在内部接口定义方法，在接入类中重写方法实现功能
 */

interface IHomePageDetails {
    interface View{
        void handleClickBack(); //handleClick开头的方法为点击事件的处理函数
        void handleClickDelete();
        void handleClickRevamp();
        void lightOn();
        void lightOff();
        void showToast(String code);
    }
    interface Presenter{
        void deleteNote(Note note);
    }
    interface Model{
        void deleteNote(ValueCallBack<String> code);
        void setNote(Note note);
    }
}
