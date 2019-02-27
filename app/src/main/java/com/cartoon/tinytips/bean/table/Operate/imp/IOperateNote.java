package com.cartoon.tinytips.bean.table.Operate.imp;

import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Note;

import java.util.List;

public interface IOperateNote {

    void add(Note note,IOperateBean<String> operateBean);

    void delete(Note condition,IOperateBean<String> operateBean);

    void query(Note condition,IOperateBean<List<Note>> operateBean);

    void update(Note oldNote, Note newNote,IOperateBean<String> operateBean);

}
