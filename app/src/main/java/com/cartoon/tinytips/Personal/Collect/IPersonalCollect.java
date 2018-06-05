package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;

import java.util.List;

/**
 * Created by cartoon on 2018/3/5.
 */

interface IPersonalCollect {
    interface View{
        void handleClickBack();
        void refreshAdapter();
        void showToast(String code);
        void initNote();
    }
    interface Presenter{
        void initData();
        List<Note> getNoteList();
    }
    interface Model{
        void getNoteList(ValueCallBack<List<Note>> callBack);
    }
}
