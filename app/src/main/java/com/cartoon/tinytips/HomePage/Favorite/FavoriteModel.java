package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Operate.OperateCommentDetails;
import com.cartoon.tinytips.bean.Operate.OperateNote;
import com.cartoon.tinytips.bean.Operate.OperateInformation;
import com.cartoon.tinytips.bean.Operate.OperateSocial;
import com.cartoon.tinytips.bean.Social;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FavoriteModel implements IFavorite.Model {

    private OperateNote operateNote;

    private OperateInformation operateInformation;

    private OperateSocial operateSocial;

    private OperateCommentDetails operateCommentDetails;

    private Information localInformation;

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
        List<Information> informations= operateInformation.getQueryData();
        List<FavoriteItem> result=new ArrayList<>();
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
                FavoriteItem item=new FavoriteItem();
                File file=null;
                for(Information t:informations){
                    if(t.getNickName().equals(temp.getAuthor())){
                        file=t.getHeadPortrait();
                    }
                }
                if(JudgeEmpty.isNotEmpty(file)){
                    item.setUserImage(file);
                }
                item.setNote(temp);

                Map<String,Boolean> isClick=new HashMap<>();
                Map<String,Integer> numOfSocial=new HashMap<>();

                Social social=new Social();
                social.setNoteId(temp.getId());

                social.setType("Collect");
                operateSocial.query(social);
                while (operateSocial.isNotFinish()){

                }
                List<Social> socials=operateSocial.getQueryData();
                isClick.put(social.getType(),false);
                for(Social s:socials){
                    if(s.getUserId()==localInformation.getId()){
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
                    if(s.getUserId()==localInformation.getId()){
                        isClick.put(social.getType(),true);
                        break;
                    }
                }
                numOfSocial.put(social.getType(),socials.size());

                CommentDetails details=new CommentDetails();
                details.setNoteId(temp.getId());
                operateCommentDetails.query(details);
                while (operateCommentDetails.isNotFinish()){

                }
                numOfSocial.put("Comment",operateCommentDetails.getQueryData().size());

                item.setIsClick(isClick);
                item.setNumOfSocial(numOfSocial);
                result.add(item);
            }
            callBack.onSuccess(result);
        }

    }

    @Override
    public void onClickItem(FavoriteItem item,String type,ValueCallBack<String> callBack) {
        Social social=new Social();
        social.setNoteId(item.getNote().getId());
        social.setType(type);
        social.setUserId(localInformation.getId());
        Map<String,Boolean> map=item.getIsClick();
        Boolean isClick=map.get(type);
        if(isClick){
            operateSocial.delete(social);
            while (operateSocial.isNotFinish()){
            }
            if(operateSocial.isSuccess()){
                map.put(type,false);
                item.getNumOfSocial().put(type,item.getNumOfSocial().get(type)-1);
                callBack.onSuccess(type+":取消成功");
            }
            else {
                callBack.onFail(type+":取消失败");
            }
        }
        else {
            operateSocial.add(social);
            while (operateSocial.isNotFinish()){
            }
            if(operateSocial.isSuccess()){
                item.getNumOfSocial().put(type,item.getNumOfSocial().get(type)+1);
                map.put(type,true);
                callBack.onSuccess(type+":成功");
            }
            else {
                callBack.onFail(type+":失败");
            }
        }
        item.setIsClick(map);
    }

    /**
     * 功能
     * 处理点击用户头像或呢称的数据
     *
     * 使用方法
     * 1.传入带有用户id或呢称的个人信息对象
     * @param information
     * @param callBack
     */
    @Override
    public void clickUser(Information information, ValueCallBack<Information> callBack) {
        operateInformation.query(information);
        while (operateInformation.isNotFinish()){
        }
        Information result=operateInformation.getQueryData().get(0);
        if(JudgeEmpty.isEmpty(result)){
            callBack.onFail("网络错误");
        }
        else {
            callBack.onSuccess(result);
        }
    }

    public FavoriteModel(){
        operateNote =OperateNote.getOperateNote();
        operateInformation =OperateInformation.getOperateInformation();
        operateSocial=OperateSocial.getOperateSocial();
        operateCommentDetails=OperateCommentDetails.getCommentDetails();
        localInformation=LocalInformation.getLocalInformation().query();
    }
}
