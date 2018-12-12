package com.cartoon.tinytips.Note.Details;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateCommentDetails;
import com.cartoon.tinytips.bean.Operate.OperateSocial;
import com.cartoon.tinytips.bean.Social;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.HashMap;
import java.util.Map;

public class NoteDetailModel implements INoteDetail.Model {

    private OperateSocial operateSocial;

    private OperateCommentDetails operateCommentDetails;

    private Information info;

    @Override
    public void getSocial(Note note, ValueCallBack<Map<String, Integer>> numCallBack, ValueCallBack<Map<String, Boolean>> isClickCallBack) {

        Map<String,Integer> numOfSocial=new HashMap<>();
        Map<String,Boolean> isClick=new HashMap<>();

        isClick.put("Collect",false);
        isClick.put("Comment",false);
        isClick.put("Forward",false);
        isClick.put("Like",false);

        Social social=new Social();
        social.setNoteId(note.getId());

        social.setType("Like");
        operateSocial.query(social);
        for(Social s:operateSocial.getQueryData()){
            if(s.getUserId()==info.getId()){
                isClick.put(social.getType(),true);
            }
        }
        numOfSocial.put(social.getType(),operateSocial.getQueryData().size());

        social.setType("Collect");
        operateSocial.query(social);
        for(Social s:operateSocial.getQueryData()){
            if(s.getUserId()==info.getId()){
                isClick.put(social.getType(),true);
            }
        }
        numOfSocial.put(social.getType(),operateSocial.getQueryData().size());

        CommentDetails details=new CommentDetails();
        details.setNoteId(note.getId());
        operateCommentDetails.query(details);
        while (operateCommentDetails.isNotFinish()){
        }
        for(CommentDetails d:operateCommentDetails.getQueryData()){
            if(d.getUserId()==info.getId()){
                isClick.put("Comment",true);
            }
        }
        numOfSocial.put("Comment",operateCommentDetails.getQueryData().size());

        numOfSocial.put("Forward",0);

        if(numOfSocial.isEmpty()){
            isClickCallBack.onFail("获取社交信息失败");
            numCallBack.onFail("获取社交信息失败");
            return;
        }
        numCallBack.onSuccess(numOfSocial);
        isClickCallBack.onSuccess(isClick);
    }

    public NoteDetailModel(){
        operateSocial=OperateSocial.getOperateSocial();
        operateCommentDetails=OperateCommentDetails.getCommentDetails();
        info=LocalInformation.getLocalInformation().query();
    }
}
