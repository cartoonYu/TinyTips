package com.cartoon.tinytips.Personal.Collect;

import android.icu.text.IDNA;
import android.util.Log;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.bean.table.Operate.OperateSocial;
import com.cartoon.tinytips.bean.table.Social;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.CheckStatSocial;

import java.util.ArrayList;
import java.util.List;

public class CollectModel implements ICollect.Model {

    private CheckStatSocial checkStatSocial;

    private OperateInformation operateInformation;

    private OperateSocial operateSocial;

    private Information information;

    @Override
    public void initData(final ValueCallBack<List<StatSocial>> callBack) {
        StatSocial social=new StatSocial();
        checkStatSocial.query(social, new IOperateBean<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> list) {
                List<StatSocial> result=new ArrayList<>();
                for(StatSocial temp:list){
                    List<Integer> collects=temp.getCollectList();
                    for(Integer i:collects){
                        if(i==information.getId()){
                            result.add(temp);
                            break;
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
    public void clickUser(StatSocial social, final ValueCallBack<Information> callBack) {
        final Information information=new Information();
        information.setId(social.getUserId());
        operateInformation.query(information, new IOperateBean<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                callBack.onSuccess(information.get(0));
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
    public void clickCollect(final StatSocial social, final ValueCallBack<String> callBack) {
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

    public CollectModel(){
        checkStatSocial=CheckStatSocial.getCheckStatSocial();
        operateInformation=OperateInformation.getOperateInformation();
        operateSocial=OperateSocial.getOperateSocial();
        information=LocalInformation.getLocalInformation().query();
    }
}
