package com.cartoon.tinytips.Personal.MyNote;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;

public class MyNoteModel implements IMyNote.Model {
    @Override
    public void getMyNoteInformation(ValueCallBack<Note> callBack){
        Note mynote = new Note();
    }
}
