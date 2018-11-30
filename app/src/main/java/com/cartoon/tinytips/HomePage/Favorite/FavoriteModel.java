package com.cartoon.tinytips.HomePage.Favorite;

import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Comment;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateComment;
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

public class FavoriteModel implements IFavorite.Model {

    private OperateInformation operateInformation;

    private OperateNote operateNote;

    private OperateComment operateComment;

    @Override
    public void initData(ValueCallBack<List<FavoriteItem>> callBack){
        Note note=new Note();
        Information information=new Information();
        operateNote.query(note);
        while (operateNote.isNotFinish()){

        }
        List<Note> notes= operateNote.getQueryData();
        operateInformation.query(information);
        while(operateInformation.isNotFinish()){

        }
        List<Information> list= operateInformation.getQueryData();
        List<FavoriteItem> result=new ArrayList<>();
        Random random=new Random();
        Set<Note> noteSet=new HashSet<>();
        for(Note temp:notes){
            noteSet.add(temp);
        }
        Iterator<Note> iterator=noteSet.iterator();
        while(iterator.hasNext()){
            Note temp=iterator.next();
            if(!temp.getAuthor().equals(LocalInformation.getLocalInformation().query().getNickName())){
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
                item.setNote(temp);
                operateComment.query(temp.getId());
                while(operateComment.isNotFinish()){

                }
                Comment comment=operateComment.getQueryData();
                item.setNumOfFavorite(comment.getLike());
                item.setNumOfRecommend(comment.getComment());
                item.setNumOfCollection(comment.getCollect());
                item.setNumOfShare(comment.getForward());
                item.setTime(temp.getDate().substring(0,temp.getDate().length()-8));
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

    @Override
    public void addFavorites(FavoriteItem item,String operate,ValueCallBack<String> callBack) {
        Comment comment = new Comment();
        Note note = getNoteFromItem(item);
        comment.setNoteId(note.getId());
        comment.setLike(item.getNumOfFavorite());
        comment.setCollect(item.getNumOfCollection());
        if (operate.equals("like")) {
            if (sendData(comment.getNoteId(), "like", comment.getLike())) {
                callBack.onSuccess("操作成功");
            } else {
                callBack.onFail("操作失败");
            }
        }else if (operate.equals("collect")){
           if (sendData(comment.getNoteId(),"collect",comment.getCollect())){
               callBack.onSuccess("操作成功");
           }else {
               callBack.onFail("操作失败");
           }
        }

    }

    private boolean sendData(long noteId,String name,int data){
        operateComment.update(noteId,name,data);
        while (operateComment.isNotFinish()){

        }
        if(operateComment.isSuccess()){
            return true;
        }
        return false;
    }

    private Note getNoteFromItem(FavoriteItem item){
        Note note=item.getNote();
        operateNote.query(note);
        while (operateNote.isNotFinish()){

        }
        note=operateNote.getQueryData().get(0);
        return note;
    }

    public FavoriteModel(){
        operateInformation =OperateInformation.getOperateInformation();
        operateNote =OperateNote.getOperateNote();
        operateComment=OperateComment.getOperateComment();
    }
}
