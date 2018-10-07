package com.cartoon.tinytips.util.JSON;

import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * JSONObject与JSONArray的互相转化
 *
 * notice
 * 1.本类为单例，对象通过调用静态方法getOperation获取
 */

public class JSONArrayOperation {

    private static volatile JSONArrayOperation operation;

    public static JSONArrayOperation getOperation(){
        if(JudgeEmpty.isEmpty(operation)){
            synchronized (JSONArrayOperation.class){
                if(JudgeEmpty.isEmpty(operation)){
                    operation=new JSONArrayOperation();
                }
            }
        }
        return operation;
    }

    /**
     * 功能
     * 将JSONArray转化成JSONObject
     *
     * 使用方法
     * 1.传入JSONArray对象
     * 2.通过返回值获取JSONObject的集合
     *
     * @param source
     * @return
     */
    public List<JSONObject> getObjectsFromArray(JSONArray source) {
        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < source.length(); i++) {
            try {
                result.add(source.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 功能
     * 将JSONObject转化成JSONArray
     * <p>
     * 使用方法
     * 1.传入JSONObject集合
     * 2.通过返回值获取JSONArray对象
     *
     * @param source
     * @return
     */
    public JSONArray setObjectToArray(List<JSONObject> source) {
        JSONArray result = new JSONArray();
        for (JSONObject object : source) {
            result.put(object);
        }
        return result;
    }

    private JSONArrayOperation(){
    }
}
