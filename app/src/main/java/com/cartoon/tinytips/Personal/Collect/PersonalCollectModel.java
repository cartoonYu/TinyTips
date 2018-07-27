package com.cartoon.tinytips.Personal.Collect;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cartoon on 2018/3/5.
 * 根据isCollect==true查询数据库，并在getNoteList函数中赋值给noteList完成功能
 */

class PersonalCollectModel implements IPersonalCollect.Model{
    private List<Note> noteList;
    public PersonalCollectModel(){
        super();
        noteList=new ArrayList<>();
    }
    @Override
    public void getNoteList(ValueCallBack<List<Note>> callBack){
        if(noteList.isEmpty()){
            callBack.onFail("笔记初始化失败，请重试");
        }
        else{
            callBack.onSuccess(noteList);
        }
    }
}
