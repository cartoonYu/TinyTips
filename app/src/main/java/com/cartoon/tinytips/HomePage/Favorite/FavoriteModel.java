package com.cartoon.tinytips.HomePage.Favorite;

import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Operate.OperateComment;
import com.cartoon.tinytips.bean.table.Operate.OperateNote;
import com.cartoon.tinytips.bean.table.Operate.OperateInformation;
import com.cartoon.tinytips.bean.table.Operate.OperateSocial;
import com.cartoon.tinytips.bean.table.Operate.imp.IOperateInformation;

import java.util.List;

public class FavoriteModel implements IFavorite.Model {

    private IOperateInformation operateInformation;

    @Override
    public void initData(ValueCallBack<List<FavoriteItem>> callBack){
        callBack.onFail("系统错误");
    }

    @Override
    public void onClickItem(FavoriteItem item,String type,ValueCallBack<String> callBack) {

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
    public void clickUser(Information information,final ValueCallBack<Information> callBack) {
        operateInformation.query(information, new IOperateBean<List<Information>>() {
            @Override
            public void onSuccess(List<Information> information) {
                callBack.onSuccess(information.get(0));
            }

            @Override
            public void onFail(String msg) {
                switch (msg){
                    case "300":{
                        callBack.onFail("系统错误，请重试");
                        break;
                    }
                    case "400":{
                        callBack.onFail("系统错误，请重试");
                        break;
                    }
                    case "500":{
                        callBack.onFail("查询错误，请重试");
                        break;
                    }
                }
            }
        });
    }

    public FavoriteModel(){
        operateInformation =OperateInformation.getOperateInformation();
    }
}
