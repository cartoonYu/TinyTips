package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;

import java.util.List;

interface INoteDetail {

    interface View{
        void setTitle(String title);
        void setDetails( Note notedetail);
        void setDate(String date);
    }

    interface Presenter{
        void handleData();
    }

    interface Model{
        void getNote(ValueCallBack<Note> callBack);
    }
}
