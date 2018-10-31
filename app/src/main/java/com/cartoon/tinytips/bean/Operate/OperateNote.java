package com.cartoon.tinytips.bean.Operate;

import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Image.FileOperation;
import com.cartoon.tinytips.util.JSON.JSONArrayOperation;
import com.cartoon.tinytips.util.JSON.JSONObjectOperation;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.network.HttpConnection;
import com.cartoon.tinytips.util.network.HttpConstant;

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

public class OperateNote {

    public static volatile OperateNote operateNote;

    private FileOperation fileOperation;

    private JSONObjectOperation objectOperation;

    private JSONArrayOperation arrayOperation;

    private HttpConnection connection;

    private String url;

    private String method;

    public static OperateNote getOperateNote(){
        if(JudgeEmpty.isEmpty(operateNote)){
            synchronized (OperateNote.class){
                if(JudgeEmpty.isEmpty(operateNote)){
                    operateNote=new OperateNote();
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
    public boolean add(Note note){
        if(JudgeEmpty.isEmpty(note)){
            return false;
        }
        note=handleNote(note);
        JSONObject data=objectOperation.setNoteToJSON(note,"add");
        connection.sendJSONObject(url,method,data);
        Thread thread=new Thread(connection);
        thread.start();
        JSONObject result=null;
        try {
            thread.join();
            result=new JSONObject(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(result)){
            if(objectOperation.getResultFromJSON(result).equals("200")){
                return true;
            }
        }
        return false;
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
    public boolean delete(Note condition){
        if(JudgeEmpty.isEmpty(condition)){
            return false;
        }
        condition=handleNote(condition);
        JSONObject data=objectOperation.setNoteToJSON(condition,"delete");
        connection.sendJSONObject(url,method,data);
        Thread thread=new Thread(connection);
        thread.start();
        JSONObject result=null;
        try {
            thread.join();
            result=new JSONObject(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(result)){
            if(objectOperation.getResultFromJSON(result).equals("200")){
                return true;
            }
        }
        return false;
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
    public List<Note> query(Note condition){
        if(JudgeEmpty.isEmpty(condition)){
            return null;
        }
        condition=handleNote(condition);
        JSONObject data=objectOperation.setNoteToJSON(condition,"query");
        connection.sendJSONObject(url,"POST",data);
        Thread thread=new Thread(connection);
        thread.start();
        JSONArray array=null;
        try {
            thread.join();
            array=new JSONArray(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<JSONObject> tempResult=null;
        if(JudgeEmpty.isNotEmpty(array)){
            tempResult=arrayOperation.getObjectsFromArray(array);
        }
        List<Note> result=new ArrayList<>();
        if(JudgeEmpty.isNotEmpty(tempResult)){
            for(JSONObject object:tempResult){
                result.add(objectOperation.getNoteFromJSON(object));
            }
        }
        for(Note note:result){
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
        return result;
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
    public boolean update(Note oldNote,Note newNote){
        oldNote=handleNote(oldNote);
        newNote=handleNote(newNote);
        JSONObject condition=objectOperation.setNoteToJSON(oldNote,"update");
        JSONObject data=objectOperation.setNoteToJSON(newNote,"update");
        List<JSONObject> list=new ArrayList<>();
        list.add(condition);
        list.add(data);
        JSONArray array=arrayOperation.setObjectToArray(list);
        connection.sendJSONArray(url,method,array);
        Thread thread=new Thread(connection);
        thread.start();
        JSONObject result=null;
        try {
            thread.join();
            result=new JSONObject(connection.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(JudgeEmpty.isNotEmpty(result)){
            if (objectOperation.getResultFromJSON(result).equals("200")){
                return true;
            }
        }
        return false;
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

    public OperateNote(){
        fileOperation=FileOperation.getOperation();
        objectOperation=JSONObjectOperation.getInstance();
        arrayOperation=JSONArrayOperation.getOperation();
        connection=HttpConnection.getHttpConnection();
        url=HttpConstant.getConstant().getURL_Note();
        method="POST";
    }
}
