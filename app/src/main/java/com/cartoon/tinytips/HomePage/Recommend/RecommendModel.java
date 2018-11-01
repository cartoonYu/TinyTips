package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendModel implements IRecommend.Model {

    private OperateNote operateNote;

    private OperateInformation operateInformation;

    @Override
    public void initData(ValueCallBack<List<RecommendItem>> callBack){
        Note note=new Note();
        note.setAuthor("Leo");
        Information information=new Information();
        information.setNickName("Leo");
        List<Note> notes=operateNote.query(note);
        List<Information> informations=operateInformation.query(information);
        List<RecommendItem> result=new ArrayList<>();
        Random random=new Random();
        if(JudgeEmpty.isEmpty(notes)||notes.isEmpty()){
            callBack.onFail("获取信息失败");
        }
        else {
            for(Note temp:notes){
                RecommendItem item=new RecommendItem();
                item.setUserImage(informations.get(0).getHeadPortrait());
                item.setUsername(informations.get(0).getNickName());
                item.setTitle(temp.getTitle());
                item.setContent(DivideNote.getDivideNote().transNoteToString(temp));
                item.setNumOfFavorite(random.nextInt(100));
                item.setNumOfCollection(random.nextInt(100));
                item.setNumOfRecommend(random.nextInt(100));
                result.add(item);
            }
            callBack.onSuccess(result);
        }
    }

    public RecommendModel(){
        operateNote=OperateNote.getOperateNote();
        operateInformation=OperateInformation.getOperate();
    }
}
