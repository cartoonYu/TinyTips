package com.cartoon.tinytips.Personal.PersonalHomepage;

import android.graphics.Path;
import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Operate.OperateCommentDetails;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.bean.Operate.OperateSocial;
import com.cartoon.tinytips.bean.Social;
import com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage.DynamicState;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalHomepageModel implements IPersonalHomepage.Model {

    private Information info;

    private Note queryCondition;

    private List<Note> noteList;

    private OperateNote operateNote;

    private OperateSocial operateSocial;

    private OperateCommentDetails operateCommentDetails;

    @Override
    public void getInformation(Information information,ValueCallBack<Information> callBack) {
        if(JudgeEmpty.isNotEmpty(information)){
            info=information;
        }
        if(JudgeEmpty.isEmpty(info)){
            callBack.onFail("获取个人信息失败");
        }
        else {
            callBack.onSuccess(info);
        }
    }

    @Override
    public void getDynamicStateList(ValueCallBack<List<DynamicState>> callBack) {
        queryCondition = new Note();
        queryCondition.setUserId(info.getId());
        operateNote.query(queryCondition);
        while (operateNote.isNotFinish()){
            ShowToast.shortToast("获取笔记信息中");
        }
        noteList= operateNote.getQueryData();
        if(JudgeEmpty.isEmpty(noteList)){
            callBack.onFail("获取笔记信息失败");
        }
        else {
            List<DynamicState> list=new ArrayList<>();
            for(Note note:noteList){
                Map<String,Boolean> isClick=new HashMap<>();
                Map<String,Integer> numOfSocial=new HashMap<>();
                Social social=new Social();
                social.setNoteId(note.getId());

                social.setType("Collect");
                operateSocial.query(social);
                while (operateSocial.isNotFinish()){

                }
                List<Social> socials=operateSocial.getQueryData();
                isClick.put(social.getType(),false);
                for(Social s:socials){
                    if(s.getUserId()==info.getId()){
                        isClick.put(social.getType(),true);
                        break;
                    }
                }
                numOfSocial.put(social.getType(),socials.size());

                social.setType("Like");
                operateSocial.query(social);
                while (operateSocial.isNotFinish()){

                }
                socials=operateSocial.getQueryData();
                isClick.put(social.getType(),false);
                for(Social s:socials){
                    if(s.getUserId()==info.getId()){
                        isClick.put(social.getType(),true);
                        break;
                    }
                }
                numOfSocial.put(social.getType(),socials.size());

                CommentDetails details=new CommentDetails();
                details.setNoteId(note.getId());
                operateCommentDetails.query(details);
                while (operateCommentDetails.isNotFinish()){

                }
                List<CommentDetails> commentDetails=operateCommentDetails.getQueryData();
                isClick.put("Comment",false);
                for(CommentDetails comment:commentDetails){
                    if(comment.getUserId()==info.getId()){
                        isClick.put("Comment",true);
                    }
                }

                numOfSocial.put("Comment",commentDetails.size());
                DynamicState state=new DynamicState();
                state.setNote(note);
                state.setNumOfSocial(numOfSocial);
                state.setIsClick(isClick);
                list.add(state);
            }
            callBack.onSuccess(list);
        }
    }

    public PersonalHomepageModel(){
        operateNote =OperateNote.getOperateNote();
        operateCommentDetails=OperateCommentDetails.getCommentDetails();
        operateSocial=OperateSocial.getOperateSocial();
        info=LocalInformation.getLocalInformation().query();
    }
}
