package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Comment;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateComment;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.util.JudgeEmpty;

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

    private OperateComment operateComment;

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
        if(notes.isEmpty()){
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
                item.setNote(temp);
                operateComment.query(temp.getId());
                while(operateComment.isNotFinish()){

                }
                Comment comment=operateComment.getQueryData();
                item.setNumOfFavorite(comment.getLike());
                item.setNumOfCollection(comment.getComment());
                item.setNumOfRecommend(comment.getCollect());
                result.add(item);
            }
            callBack.onSuccess(result);
        }
    }

    @Override
    public void addFavorites(RecommendItem item, ValueCallBack<String> callBack) {
        Comment comment=new Comment();
        Note note=getNoteFromItem(item);
        comment.setNoteId(note.getId());
        comment.setLike(item.getNumOfFavorite());
        if(sendData(comment.getNoteId(),"like",comment.getLike())){
            callBack.onSuccess("操作成功");
        }
        else {
            callBack.onFail("操作失败");
        }
    }

    /**
     * 功能
     * 将页面中的子项转换成Comment对象进行数据库操作
     * @param item
     * @return
     */
    private Note getNoteFromItem(RecommendItem item){
        Note note=item.getNote();
        operateNote.query(note);
        while (operateNote.isNotFinish()){

        }
        note=operateNote.getQueryData().get(0);
        return note;
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

    public RecommendModel(){
        operateNote =OperateNote.getOperateNote();
        operateInformation =OperateInformation.getOperateInformation();
        operateComment=OperateComment.getOperateComment();
    }
}
