package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.file.FileOperation;
import com.cartoon.tinytips.util.network.HttpConstant;
import com.cartoon.tinytips.util.network.HttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author cartoon
 * @version 1.3
 *
 * description
 * 通过网络传输对数据库笔记表进行增删查改操作
 *
 * how to use
 * 1.通过静态方法getOperateNote获取本类对象
 * 2.根据相应操作执行相应的方法
 *   add(插入笔记)
 *   delete(删除笔记)
 *   query(查询笔记)
 *   update(更新笔记)
 * 3.因网络传输以及数据库操作需要时间，
 *   通过方法isNotFinish()判断操作是否完成（true为未完成）
 * 4.获取结果
 *   插入，删除，查询，更新操作通过方法isSuccess()获取操作结果
 *   查询操作通过方法getQueryData()获取结果集合
 *
 * notice
 * 1.本类无法new对象，只能通过静态方法getOperateNote获取
 */

public class OperateNote {

    private static volatile OperateNote operateNote;

    private FileOperation fileOperation;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private boolean isNotFinish;

    private boolean isSuccess;

    private List<Note> queryData;

    private HttpConnection connection;

    private String url;

    private String method;

    /**
     * 功能
     * 返回本类对象，确保在程序运行的过程只有一个对象
     *
     * 使用方法
     * 1.类名直接调用获取
     *
     * @return
     */
    public static OperateNote getOperateNote(){
        if(JudgeEmpty.isEmpty(operateNote)){
            synchronized (OperateNote.class){
                if(JudgeEmpty.isEmpty(operateNote)){
                    operateNote =new OperateNote();
                }
            }
        }
        return operateNote;
    }

    /**
     * 功能
     * 插入笔记信息
     *
     * 使用方法
     * 1.传入笔记对象
     * 2.通过返回值判断插入是否成功
     *
     * 注意
     *
     * @param note
     * @return
     */
    public void add(Note note){
        note=handleNote(note);
        JSONObject data=objectOperation.setNoteToJSON(note,"add");
        String result=sendData(data,null);
        setSuccess(result);
    }

    /**
     * 功能
     * 删除笔记
     *
     * 使用方法
     * 1.传入笔记对象
     * 2.通过返回值判断删除是否成功
     *
     * 注意
     * 1.传入笔记对象必须携带至少一个条件
     *
     * @param condition
     * @return
     */
    public void delete(Note condition){
        condition=handleNote(condition);
        JSONObject data=objectOperation.setNoteToJSON(condition,"delete");
        String result=sendData(data,null);
        setSuccess(result);
    }

    /**
     * 功能
     * 查询笔记信息
     *
     * 使用方法
     * 1.传入笔记对象
     * 2.通过返回值索取数据
     *
     * 注意
     * 1.传入笔记对象必须携带至少一个条件
     * 2.返回值使用前必须先进行非空判断
     *
     * @param condition
     * @return
     */
    public void query(Note condition){
        condition=handleNote(condition);
        JSONObject data=objectOperation.setNoteToJSON(condition,"query");
        String result=sendData(data,null);
        setQueryData(result);
    }

    /**
     * 功能
     * 更新笔记信息
     *
     * 使用方法
     * 1.传入原有笔记信息以及修改后的笔记信息对象
     * 2.通过返回值判断更新是否成功
     *
     * 注意
     * 1.传入的原有笔记信息对象必须携带至少一个条件
     *
     * @param oldNote
     * @param newNote
     * @return
     */
    public void update(Note oldNote,Note newNote){
        oldNote=handleNote(oldNote);
        newNote=handleNote(newNote);
        JSONObject condition=objectOperation.setNoteToJSON(oldNote,"update");
        JSONObject data=objectOperation.setNoteToJSON(newNote,"update");
        List<JSONObject> list=new ArrayList<>();
        list.add(condition);
        list.add(data);
        JSONArray array=arrayOperation.setObjectToArray(list);
        String result=sendData(null,array);
        setSuccess(result);
    }

    /**
     * 功能
     * 将笔记对象内属性进行转换，以适应传输的需要
     *
     * 使用方法
     *
     * 注意
     *
     * @param source
     * @return
     */
    private Note handleNote(Note source){
        if(JudgeEmpty.isNotEmpty(source.getPhotoDetails())){
            Map<String,String> photo=new LinkedHashMap<>();
            for(File file:source.getPhotoDetails()){
                if(JudgeEmpty.isNotEmpty(file.getName())){
                    photo.put(file.getName(),fileOperation.transFileToString(file));
                }
            }
            source.setPhotoSource(photo);
        }
        return source;
    }

    /**
     * 功能
     * 调用网络连接工具类发送数据
     *
     * 使用方法
     *
     * 注意
     * 1.两个形参中必须有一个为null
     *
     * @param object
     * @param array
     * @return
     */
    private String sendData(JSONObject object, JSONArray array){
        if(JudgeEmpty.isNotEmpty(object)){
            connection.sendJSONObject(url,method,object);
        }else if(JudgeEmpty.isNotEmpty(array)){
            connection.sendJSONArray(url,method,array);
        }
        new Thread(connection).start();
        while (connection.isRun()){
        }
        setNotFinish(false);
        return connection.getResult();
    }

    /**
     * 功能
     * 判断网络传输是否完成
     *
     * @return
     */
    public boolean isNotFinish() {
        return isNotFinish;
    }

    /**
     * 获取插入，删除，更新数据是否成功
     * @return
     */
    public boolean isSuccess() {
        setNotFinish(true);
        return isSuccess;
    }

    /**
     * 获取查询的结果
     *
     * @return
     */
    public List<Note> getQueryData() {
        setNotFinish(true);
        return queryData;
    }

    private void setNotFinish(boolean notFinish) {
        isNotFinish = notFinish;
    }

    private void setSuccess(String result) {
        JSONObject object=null;
        try {
            object=new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(object)){
            if(objectOperation.getResultFromJSON(object).equals("200")){
                isSuccess=true;
            }
            else {
                isSuccess=false;
            }
        }
    }

    /**
     * 功能
     * 将服务器返回的查询数据转换成集合形式输出
     *
     * @param result
     */
    private void setQueryData(String result) {
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
        if(JudgeEmpty.isNotEmpty(temp)){
            for(JSONObject object:temp){
                queryData.add(objectOperation.getNoteFromJSON(object));
            }
        }
        for(Note note:queryData){
            if(JudgeEmpty.isNotEmpty(note.getPhotoSource())){
                if(!note.getPhotoSource().isEmpty()){
                    List<File> files=new ArrayList<>();
                    Set<String> set=note.getPhotoSource().keySet();
                    Iterator<String> iterator=set.iterator();
                    while(iterator.hasNext()){
                        String fileName=iterator.next();
                        String file=note.getPhotoSource().get(fileName);
                        files.add(fileOperation.transStringToFile(file,fileName));
                    }
                    note.setPhotoDetails(files);
                }
            }
        }
    }

    private OperateNote(){
        fileOperation=FileOperation.getOperation();
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        queryData=new ArrayList<>();
        connection=HttpConnection.getConnection();
        url=HttpConstant.getConstant().getURL_Note();
        method="POST";
    }

}
