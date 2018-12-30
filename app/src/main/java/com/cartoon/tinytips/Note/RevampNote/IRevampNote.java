package com.cartoon.tinytips.Note.RevampNote;

import android.text.SpannableString;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;

import java.util.List;

interface IRevampNote {

    interface View{
        void setTitle(String title);
        void setContent(List<SpannableString> list);
        String getNoteTitle();
        String getContent();
        void finishActivity();
    }

    interface Presenter{
        void initData(Note note);
        void revampNote(Note oldNote);
    }

    interface Model{
        void revampNote(Note oldNote,Note note, ValueCallBack<String> callBack);
    }
}
