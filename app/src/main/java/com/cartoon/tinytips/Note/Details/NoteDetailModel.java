package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.bean.Operate.OperateNote;

public class NoteDetailModel implements INoteDetail.Model {

    private OperateNote operateNote;


    public NoteDetailModel(){
        operateNote=OperateNote.getOperateNote();

    }
}
