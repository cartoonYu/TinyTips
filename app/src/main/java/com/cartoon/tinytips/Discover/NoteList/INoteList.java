package com.cartoon.tinytips.Discover.NoteList;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;

import java.io.File;

interface INoteList {
    interface View{
        void setUserName(String userName);
        void setHeadPro(File headPro);
        void setTitle(String Title);
        void setContents(String contents);
        void setNumOF(String nof);
        void setNumOC(String noc);
        void setNumOR(String nor);
    }

    interface Presenter{
        void handleData();
    }

    interface Model{
        void getUserInformation(ValueCallBack<Information> callBack);
        void getNote(ValueCallBack<Note> callBack);
    }
}
