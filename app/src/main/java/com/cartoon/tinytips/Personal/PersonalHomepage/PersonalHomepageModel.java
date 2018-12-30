package com.cartoon.tinytips.Personal.PersonalHomepage;

import android.graphics.Path;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Operate.OperateSocial;
import com.cartoon.tinytips.bean.table.Social;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.CheckStatSocial;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.ArrayList;
import java.util.List;

public class PersonalHomepageModel implements IPersonalHomepage.Model {

    private Information info;

    private CheckStatSocial checkStatSocial;

    private OperateSocial operateSocial;

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
    public void getDynamicStateList(final ValueCallBack<List<StatSocial>> callBack) {
        StatSocial social=new StatSocial();
        checkStatSocial.query(social, new IOperateBean<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> socials) {
                List<StatSocial> result=new ArrayList<>();
                for(StatSocial temp:socials){
                    if(temp.getUserId()==info.getId()) {
                        result.add(temp);
                    }
                    else {
                        List<Integer> social=new ArrayList<>();
                        social.addAll(temp.getCollectList());
                        social.addAll(temp.getCommentList());
                        social.addAll(temp.getForwardList());
                        social.addAll(temp.getLoveList());
                        for(Integer i:social){
                            if(i==info.getId()){
                                result.add(temp);
                                break;
                            }
                        }
                    }
                }
                callBack.onSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void clickLike(StatSocial social, final ValueCallBack<String> callBack) {
        Social like=new Social();
        like.setNoteId(social.getNoteId());
        like.setUserId(social.getUserId());
        like.setType("Like");
        if(social.isLove()){
            operateSocial.delete(like, new IOperateBean<String>() {
                @Override
                public void onSuccess(String s) {
                    callBack.onSuccess("取消成功");
                }

                @Override
                public void onFail(String msg) {
                    callBack.onFail("取消失败");
                }
            });
        }
        else {
            operateSocial.add(like, new IOperateBean<String>() {
                @Override
                public void onSuccess(String s) {
                    callBack.onSuccess("点赞成功");
                }

                @Override
                public void onFail(String msg) {
                    callBack.onFail("点赞失败");
                }
            });
        }
    }

    @Override
    public void clickCollect(StatSocial social,final ValueCallBack<String> callBack) {
        Social collect=new Social();
        collect.setNoteId(social.getNoteId());
        collect.setUserId(social.getUserId());
        collect.setType("Collect");
        if(social.isCollect()){
            operateSocial.delete(collect, new IOperateBean<String>() {
                @Override
                public void onSuccess(String s) {
                    callBack.onSuccess("取消成功");
                }

                @Override
                public void onFail(String msg) {
                    callBack.onFail("取消失败");
                }
            });
        }
        else {
            operateSocial.add(collect, new IOperateBean<String>() {
                @Override
                public void onSuccess(String s) {
                    callBack.onSuccess("收藏成功");
                }

                @Override
                public void onFail(String msg) {
                    callBack.onFail("收藏失败");
                }
            });
        }
    }

    public PersonalHomepageModel(){
        info=LocalInformation.getLocalInformation().query();
        operateSocial=OperateSocial.getOperateSocial();
        checkStatSocial=CheckStatSocial.getCheckStatSocial();
    }
}
