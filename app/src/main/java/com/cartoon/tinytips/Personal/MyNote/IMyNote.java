package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;

import java.util.List;

interface IMyNote {
    interface View{
        void initNote(List<Note> notes);      //初始化笔记列表
    }
    interface Presenter{
        void initData(Information information);   //初始化数据
    }

    interface Model{
        void getNote(Information information,ValueCallBack<List<Note>> callBack);   //获取笔记
    }

}
