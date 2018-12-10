package com.cartoon.tinytips.Personal.Collect;

import android.graphics.Path;
import android.icu.text.IDNA;
import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateCommentDetails;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.bean.Operate.OperateSocial;
import com.cartoon.tinytips.bean.Social;
import com.cartoon.tinytips.util.Adapters.Personal.Collect.Collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectModel implements ICollect.Model {

    private OperateSocial operateSocial;

    private OperateNote operateNote;

    private OperateInformation operateInformation;

    private OperateCommentDetails operateCommentDetails;

    private Information information;

    @Override
    public void initData(ValueCallBack<List<Collect>> callBack) {
        Social source=new Social();
        source.setType("Collect");
        source.setUserId(information.getId());
        operateSocial.query(source);
        while (operateSocial.isNotFinish()){
        }
        List<Social> list=new ArrayList<>();  //用户收藏的所有笔记
        for(Social social:operateSocial.getQueryData()){
            list.add(social);
        }
        if(list.isEmpty()){
            callBack.onFail("获取收藏信息失败，请重试");
            return;
        }
        else {
            List<Collect> collects=new ArrayList<>();
            for(int i=0,length=list.size();i<length;i++){
                Social social=list.get(i);
                Collect collect=new Collect();
                Note note=new Note();
                note.setId(social.getNoteId());
                operateNote.query(note);
                while (operateNote.isNotFinish()){
                }
                collect.setNote(operateNote.getQueryData().get(0));

                Information information=new Information();
                information.setId(collect.getNote().getUserId());
                operateInformation.query(information);
                while (operateInformation.isNotFinish()){
                }
                collect.setInformation(operateInformation.getQueryData().get(0));

                Map<String,Integer> socials=new HashMap<>();
                Map<String,Boolean> isClick=new HashMap<>();
                isClick.put("Collect",true);
                isClick.put("Like",false);
                isClick.put("Comment",false);
                isClick.put("Forward",false);

                Social temp=new Social();
                temp.setNoteId(collect.getNote().getId());

                temp.setType("Collect");
                operateSocial.query(temp);
                while (operateSocial.isNotFinish()){
                }
                socials.put(temp.getType(),operateSocial.getQueryData().size());

                temp.setType("Like");
                operateSocial.query(temp);
                while (operateSocial.isNotFinish()){
                }
                List<Social> socialList=operateSocial.getQueryData();
                for(Social s:socialList){
                    if(s.getUserId()==information.getId()){
                        isClick.put(temp.getType(),true);
                        break;
                    }
                }
                socials.put(temp.getType(),operateSocial.getQueryData().size());

                CommentDetails commentDetails=new CommentDetails();
                commentDetails.setNoteId(social.getNoteId());
                operateCommentDetails.query(commentDetails);
                while (operateCommentDetails.isNotFinish()){
                }
                for(CommentDetails details:operateCommentDetails.getQueryData()){
                    if(details.getUserId()==information.getId()){
                        isClick.put("Comment",true);
                    }
                }
                socials.put("Comment",operateCommentDetails.getQueryData().size());

                collect.setSocial(socials);
                collect.setIsClick(isClick);
                collects.add(collect);
            }
            callBack.onSuccess(collects);
        }
    }

    public CollectModel(){
        operateNote=OperateNote.getOperateNote();
        operateSocial=OperateSocial.getOperateSocial();
        operateInformation=OperateInformation.getOperateInformation();
        operateCommentDetails=OperateCommentDetails.getCommentDetails();
        information=LocalInformation.getLocalInformation().query();
    }
}
