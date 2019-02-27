package com.cartoon.tinytips.bean.view.check;


import com.cartoon.tinytips.bean.IOperateBean;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Local.LocalInformation;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.bean.view.check.imp.ICheckStatSocial;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.file.FileOperation;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.IDataCallBack;
import com.cartoon.tinytips.util.network.IHttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 社交统计视图查看
 * @author cartoon
 * @version 1.0
 */

public class CheckStatSocial implements ICheckStatSocial {

    private static volatile ICheckStatSocial checkStatSocial;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private IHttpConnection connection;

    private String url;

    private String method;

    public static ICheckStatSocial getCheckStatSocial(){
        if(JudgeEmpty.isEmpty(checkStatSocial)){
            synchronized (CheckStatSocial.class){
                if(JudgeEmpty.isEmpty(checkStatSocial)){
                    checkStatSocial=new CheckStatSocial();
                }
            }
        }
        return checkStatSocial;
    }

    @Override
    public void query(final StatSocial social, final IOperateBean<List<StatSocial>> operateBean){
        url=HttpConstant.getConstant().getURL_StatSocial("query");
        JSONObject object=objectOperation.setStatSocialToJSON(social);
        connection.sendJSONObject(url, method, object, new IDataCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                if(result.equals("[]")){
                    operateBean.onFail("500");
                }
                else {
                    JSONArray array=null;
                    try {
                        array=new JSONArray(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    List<JSONObject> temp=null;
                    if(JudgeEmpty.isNotEmpty(array)){
                        temp=arrayOperation.getObjectsFromArray(array);
                    }
                    List<StatSocial> list=new ArrayList<>();
                    if(JudgeEmpty.isNotEmpty(temp)){
                        for(JSONObject object:temp){
                            objectOperation.displayJSON(object);
                            StatSocial statSocial=objectOperation.getStatSocialFromJSON(object);
                            statSocial=getSocial(statSocial);
                            statSocial=getPhoto(statSocial);
                            statSocial=revampNote(statSocial);
                            list.add(statSocial);
                        }
                    }
                    operateBean.onSuccess(list);
                }
            }

            @Override
            public void onFail(String msg) {
                operateBean.onFail(msg);
            }
        });
    }

    /**
     * 去除文字内容开头与结尾的双引号
     * @param statSocial
     * @return
     */
    private StatSocial revampNote(StatSocial statSocial){
        List<String> list=statSocial.getWordDetails();
        List<String> result=new ArrayList<>();
        if(JudgeEmpty.isEmpty(list)){
            return statSocial;
        }
        for(String temp:list){
            temp=temp.substring(1,temp.length());
            temp=temp.substring(0,temp.length()-1);
            result.add(temp);
        }
        statSocial.setWordDetails(result);
        return statSocial;
    }

    /**
     * 获取用户的社交行为
     * @param statSocial
     * @return
     */
    private StatSocial getSocial(StatSocial statSocial){
        List<Integer> loveList=statSocial.getLoveList();
        List<Integer> collectList=statSocial.getCollectList();
        List<Integer> forwardList=statSocial.getForwardList();
        List<Integer> commentList=statSocial.getCommentList();
        statSocial.setLove(isClick(loveList));
        statSocial.setCollect(isClick(collectList));
        statSocial.setForward(isClick(forwardList));
        statSocial.setComment(isClick(commentList));
        return statSocial;
    }

    /**
     * 判断用户是否进行过收藏，点赞，转发，评论等操作
     * @param list
     * @return
     */
    private boolean isClick(List<Integer> list){
        if(JudgeEmpty.isEmpty(list)){
            return false;
        }
        Information information=LocalInformation.getLocalInformation().query();
        for(Integer temp:list){
            if(temp==information.getId()){
                return true;
            }
        }
        return false;
    }

    /**
     * 将图片转换成适合前端使用的数据类型
     * @param statSocial
     * @return
     */
    private StatSocial getPhoto(StatSocial statSocial){
        if(JudgeEmpty.isEmpty(statSocial.getPhotoSource())){
            return statSocial;
        }
        FileOperation fileOperation=FileOperation.getOperation();
        List<File> photos=new ArrayList<>();
        Map<String,String> source=statSocial.getPhotoSource();
        Set<String> set=source.keySet();
        for (String temp:set){
            photos.add(fileOperation.transStringToFile(source.get(temp),temp));
        }
        return statSocial;
    }

    private CheckStatSocial(){
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=new HttpConnection();
        method="POST";
    }
}
