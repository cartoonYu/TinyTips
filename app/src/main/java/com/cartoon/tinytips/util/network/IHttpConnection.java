package com.cartoon.tinytips.util.network;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IHttpConnection {

    void sendJSONObject(String url, String method, JSONObject object,IDataCallBack<String> callBack);
    void sendJSONArray(String url, String method, JSONArray array,IDataCallBack<String> callBack);
}
