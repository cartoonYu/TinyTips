package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;

import java.util.List;

public interface IMyNote {

    interface View{
        void initNote(List<Note> notes);      //初始化笔记列表
    }
    interface Presenter{
        void initData();   //初始化数据
    }

    interface Model{
        void getNote(ValueCallBack<List<Note>> callBack);   //获取笔记
        void deleteNote(List<Note> list,Note note,ValueCallBack<String> callBack);
    }

}
