package com.cartoon.tinytips.Note.Addnote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;

interface IAddNote {
    interface View{
        void intentToMain();
    }

    interface Presenter{
        void addNote(Note note);
    }

    interface Model{
        void addNote(Note note, ValueCallBack<String> callBack);
    }
}
