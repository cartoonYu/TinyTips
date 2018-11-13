package com.cartoon.tinytips.HomePage.Recommend;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RecommendModel implements IRecommend.Model {

    private OperateNote operateNote;

    private OperateInformation operateInformation;

    @Override
    public void initData(ValueCallBack<List<RecommendItem>> callBack){
        Note note=new Note();
        Information information=new Information();
        operateNote.query(note);
        while (operateNote.isNotFinish()){

        }
        List<Note> notes= operateNote.getQueryData();
        operateInformation.query(information);
        while(operateInformation.isNotFinish()){

        }
        List<Information> informations= operateInformation.getQueryData();
        List<RecommendItem> result=new ArrayList<>();
        Random random=new Random();
        if(JudgeEmpty.isEmpty(notes)||notes.isEmpty()){
            callBack.onFail("获取信息失败");
        }
        else {
            Set<Note> noteSet=new HashSet<>();
            for(Note temp:notes){
                noteSet.add(temp);
            }
            Iterator<Note> iterator=noteSet.iterator();
            while(iterator.hasNext()){
                Note temp=iterator.next();
                RecommendItem item=new RecommendItem();
                File file=null;
                for(Information t:informations){
                    if(t.getNickName().equals(temp.getAuthor())){
                        file=t.getHeadPortrait();
                    }
                }
                if(JudgeEmpty.isNotEmpty(file)){
                    item.setUserImage(file);
                }
                item.setUsername(temp.getAuthor());
                item.setTitle(temp.getTitle());
                item.setContent(DivideNote.getDivideNote().transNoteToString(temp));
                item.setNumOfFavorite(random.nextInt(100));
                item.setNumOfCollection(random.nextInt(100));
                item.setNumOfRecommend(random.nextInt(100));
                result.add(item);
            }
            Log.d("asd",result.size()+"");
            callBack.onSuccess(result);
        }
    }

    public RecommendModel(){
        operateNote =OperateNote.getOperateNote();
        operateInformation =OperateInformation.getOperateInformation();
    }
}
