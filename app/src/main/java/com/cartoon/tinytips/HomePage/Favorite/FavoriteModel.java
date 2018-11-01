package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FavoriteModel implements IFavorite.Model {

    private OperateInformation operateInformation;

    private OperateNote operateNote;

    @Override
    public void initData(ValueCallBack<List<FavoriteItem>> callBack){
        Note note=new Note();
        Information information=new Information();
        List<Note> notes=operateNote.query(note);
        List<Information> list=operateInformation.query(information);
        List<FavoriteItem> result=new ArrayList<>();
        Random random=new Random();
        Set<Note> noteSet=new HashSet<>();
        for(Note temp:notes){
            noteSet.add(temp);
        }
        Iterator<Note> iterator=noteSet.iterator();
        while(iterator.hasNext()){
            Note temp=iterator.next();
            if(!temp.getAuthor().equals("Leo")){
                FavoriteItem item=new FavoriteItem();
                File file=null;
                for(Information t:list){
                    if(t.getNickName().equals(temp.getAuthor())){
                        file=t.getHeadPortrait();
                    }
                }
                if(JudgeEmpty.isNotEmpty(file)){
                    item.setUserImage(file);
                }
                item.setUserName(temp.getAuthor());
                item.setTitle(temp.getTitle());
                item.setContent(DivideNote.getDivideNote().transNoteToString(temp));
                item.setNumOfFavorite(random.nextInt(50));
                item.setNumOfRecommend(random.nextInt(50));
                item.setNumOfCollection(random.nextInt(50));
                item.setNumOfShare(random.nextInt(50));
                item.setTime(temp.getDate());
                result.add(item);
            }
        }
        if(result.isEmpty()){
            callBack.onFail("获取信息失败");
        }
        else {
            callBack.onSuccess(result);
        }

    }

    public FavoriteModel(){
        operateInformation=OperateInformation.getOperate();
        operateNote=OperateNote.getOperateNote();
    }
}
