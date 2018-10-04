package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;

interface IMyNote {
    interface View{
        void initNote();      //初始化笔记列表
    }
    interface Presenter{
        void initData();   //初始化数据
    }

    interface Model{
        void getMyNoteInformation(ValueCallBack<Note> callBack);   //获取笔记
    }

}
