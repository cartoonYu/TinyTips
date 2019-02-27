package com.cartoon.tinytips.HomePage.Recommend;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.table.Operate.OperateComment;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.bean.table.Operate.OperateSocial;
import com.cartoon.tinytips.bean.table.Social;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.CheckStatSocial;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

public class RecommendModel implements IRecommend.Model {

    private OperateNote operateNote;

    private OperateInformation operateInformation;

    private OperateSocial operateSocial;

    private OperateComment operateComment;

    private Information localInformation;

    private CheckStatSocial checkStatSocial;

    @Override
    public void initData(final ValueCallBack<List<StatSocial>> callBack){
        StatSocial statSocial=new StatSocial();
        checkStatSocial.query(statSocial, new IOperateBean<List<StatSocial>>() {
            @Override
            public void onSuccess(List<StatSocial> statSocials) {
                callBack.onSuccess(statSocials);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void clickLike(final StatSocial social, final ValueCallBack<String> callBack) {
        Social data=new Social();
        data.setType("Like");
        data.setUserId(localInformation.getId());
        data.setNoteId(social.getNoteId());
        if(social.isLove()){
            operateSocial.delete(data, new IOperateBean<String>() {
                @Override
                public void onSuccess(String s) {
                    social.setLove(false);
                    callBack.onSuccess("取消成功");
                }

                @Override
                public void onFail(String msg) {
                    callBack.onFail("取消失败");
                }
            });
        }
        else {
            operateSocial.add(data, new IOperateBean<String>() {
                @Override
                public void onSuccess(String s) {
                    social.setLove(true);
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
        Social data=new Social();
        data.setType("Collect");
        data.setUserId(localInformation.getId());
        data.setNoteId(social.getNoteId());
        if(social.isCollect()){
            operateSocial.delete(data, new IOperateBean<String>() {
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
            operateSocial.add(data, new IOperateBean<String>() {
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
    public void clickUser(StatSocial social, final ValueCallBack<Information> callBack) {
        Information information=new Information();
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

    public RecommendModel(){
        operateNote =OperateNote.getOperateNote();
        operateInformation =OperateInformation.getOperateInformation();
        operateSocial=OperateSocial.getOperateSocial();
        operateComment =OperateComment.getComment();
        localInformation=LocalInformation.getLocalInformation().query();
        checkStatSocial=CheckStatSocial.getCheckStatSocial();
    }
}
