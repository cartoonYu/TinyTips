package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;

interface INoteDetail {

    interface View{
        void setNote(Note note);
        void setSocial(StatSocial social);
        void setTitle(String title);
        void setDetails(Note noteDetail);
        void setDate(String date);
        void setCollect(int num,boolean isClick);
        void setLove(int num,boolean isClick);
        void setForward(int num);
        void setComment(int num);
    }

    interface Presenter{
        void handleStatSocial(StatSocial social);
        void handleNote(Note note);
    }

    interface Model{
        void getNote(StatSocial social,ValueCallBack<Note> callBack);
        void getSocial(Note note,ValueCallBack<StatSocial> callBack);
    }
}
