package com.cartoon.tinytips.HomePage.Hot;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotModel implements IHot.Model {

    private int flag;

    private OperateNote operateNote;

    @Override
    public void initData(ValueCallBack<List<HotItem>> callBack){
        Note note=new Note();
        note.setAuthor("Leo");
        List<Note> list=operateNote.query(note);
        List<HotItem> items=new ArrayList<>();
        if(JudgeEmpty.isEmpty(list)||list.isEmpty()){
            callBack.onFail("获取笔记信息错误");
        }
        else {
            Random random=new Random();
            for(Note temp:list){
                HotItem item=new HotItem();
                item.setTitle(temp.getTitle());
                item.setContent(DivideNote.getDivideNote().transNoteToString(temp));
                item.setSequence(flag++);
                item.setNumOfClick(random.nextInt(100));
                items.add(item);
            }
            callBack.onSuccess(items);
        }
    }

    public HotModel(){
        flag=0;
        operateNote=OperateNote.getOperateNote();
    }
}
