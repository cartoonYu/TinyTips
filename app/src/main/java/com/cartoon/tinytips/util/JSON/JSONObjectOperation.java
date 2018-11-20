package com.cartoon.tinytips.util.JSON;

import android.util.Log;

import com.cartoon.tinytips.bean.Comment;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.file.FileOperation;
import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * JSONObject与Java Bean的相互转化
 *
 * notice
 * 1.本类为单例，对象通过调用静态方法getInstance获取
 */

public class JSONObjectOperation {

    private static volatile JSONObjectOperation operation;

    private FileOperation fileOperation;

    /**
     * 获取本类对象
     *
     * @return
     */
    public static JSONObjectOperation getInstance(){
        if(JudgeEmpty.isEmpty(operation)){
            synchronized (JSONObjectOperation.class){
                if(JudgeEmpty.isEmpty(operation)){
                    operation=new JSONObjectOperation();
                }
            }
        }
        return operation;
    }

    /**
     * 功能
     * 将传入的comment转换成json文件
     *
     * @param comment
     * @param method
     * @return
     */
    public JSONObject setCommentToJSON(Comment comment,String method){
        if(JudgeEmpty.isEmpty(comment)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                result.put("method",method);
                if(comment.getNoteId()!=0){
                    result.put("noteId",comment.getNoteId());
                }
                if(JudgeEmpty.isNotEmpty(comment.getTag())){
                    result.put("tag",comment.getTag().toString());
                }
                if(comment.getLike()!=0){
                    result.put("like",comment.getLike());
                }
                if(comment.getComment()!=0){
                    result.put("comment",comment.getComment());
                }
                if(comment.getCollect()!=0){
                    result.put("collect",comment.getCollect());
                }
                if(comment.getForward()!=0){
                    result.put("forward",comment.getForward());
                }
            }catch(JSONException e){
                Log.d("jsonObjectException","jsonObjectException:将comment转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 获取传入json文件中的comment值
     * @param object
     * @return
     */
    public Comment getCommentFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Comment comment=new Comment();
            try{
                if(object.has("noteId")){
                    comment.setNoteId(object.getLong("noteId"));
                }
                if(object.has("tag")){
                    String temp=object.getString("tag");
                    comment.setTag(changeStringToList(temp));
                }
                if(object.has("like")){
                    if(object.getInt("like")!=0){
                        comment.setLike(object.getInt("like"));
                    }
                }

                if(object.has("comment")){
                    if(object.getInt("comment")!=0){
                        comment.setComment(object.getInt("comment"));
                    }
                }
                if(object.has("collect")){
                    if(object.getInt("collect")!=0){
                        comment.setCollect(object.getInt("collect"));
                    }
                }
                if(object.has("forward")){
                    if(object.getInt("forward")!=0){
                        comment.setForward(object.getInt("forward"));
                    }
                }
            }catch(JSONException e){
                Log.d("jsonObjectException","jsonObjectException:将json文件转换commentDetails出现错误");
                e.printStackTrace();
            }
            return comment;
        }
    }

    /**
     * 将传入的commentDetails转换成json文件
     * @param commentDetails
     * @return
     */
    public JSONObject setCommentDetailsToJSON(CommentDetails commentDetails){
        if(JudgeEmpty.isEmpty(commentDetails)){
            return null;
        }
        else{
            JSONObject jsonObject=new JSONObject();
            try{
                jsonObject.put("commentDetails",commentDetails);
            }catch(JSONException e){
                Log.e("jsonObjectException","将commentDetails转换json文件出现错误");
                e.printStackTrace();
            }
            return jsonObject;
        }
    }

    /**
     * 获取传入json文件中的comment值
     * @param object
     * @return
     */
    public CommentDetails getcommentDetailsFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            CommentDetails commentDetails=new CommentDetails();
            try{
                if(object.get("commentDetails") instanceof CommentDetails){
                    commentDetails=(CommentDetails)object.get("commentDetails");
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换comment出现错误");
                e.printStackTrace();
            }
            return commentDetails;
        }
    }

    /**
     * 将传入的note转换成json文件
     * @param note
     * @return
     */
    public JSONObject setNoteToJSON(Note note,String method){
        if(JudgeEmpty.isEmpty(note)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                result.put("method",method);
                if(note.getUserId()!=0){
                    result.put("userId",note.getUserId());
                }
                if(note.getId()!=0){
                    result.put("id",note.getId());
                }
                result.put("title",note.getTitle());
                if(JudgeEmpty.isNotEmpty(note.getWordDetails())){
                    if(!note.getWordDetails().isEmpty()){
                        result.put("word",note.getWordDetails().toString());
                    }
                }
                if(JudgeEmpty.isNotEmpty(note.getPhotoSource())){
                    if(!note.getPhotoSource().isEmpty()){
                        result.put("photo",note.getPhotoSource().toString());
                    }
                }
                if(JudgeEmpty.isNotEmpty(note.getAuthor())){
                    result.put("author",note.getAuthor());
                }
                if(JudgeEmpty.isNotEmpty(note.getDate())){
                    result.put("date",note.getDate());
                }
                if(JudgeEmpty.isNotEmpty(note.getTag())){
                    result.put("tag",note.getTag().toString());
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将note转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 获取传入json文件中的note值
     * @param object
     * @return
     */
    public Note getNoteFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Note note=new Note();
            try{
                if(object.has("userId")){
                    note.setUserId(object.getLong("userId"));
                }
                if(object.has("id")){
                    note.setId(object.getLong("id"));
                }
                if(object.has("title")){
                    note.setTitle(object.getString("title"));
                }
                if(object.has("word")) {
                    String word = object.getString("word");
                    note.setWordDetails(changeStringToList(word));
                }
                if(object.has("photo")){
                    String photo=object.getString("photo");
                    note.setPhotoSource(changeStringToMap(photo));
                }
                if(object.has("author")){
                    note.setAuthor(object.getString("author"));
                }
                if(object.has("date")){
                    note.setDate(object.getString("date"));
                }
                if (object.has("tag")){
                    String tag=object.getString("tag");
                    note.setTag(changeStringToList(tag));
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换note出现错误");
                e.printStackTrace();
            }
            return note;
        }
    }

    /**
     * 功能
     * 将传入的Information对象转换成json文件
     *
     * 使用方法
     * 1.传入Information对象以及对数据库操作的类型
     * 2.通过返回值获取转换的JSON文件
     *
     * 注意
     * 1.传入的method必须是：add，delete，query，update
     *
     * @param information
     * @return
     */
    public JSONObject setInformationToJSON(Information information,String method){
        if(JudgeEmpty.isEmpty(information)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                result.put("method",method);
                if(information.getId()!=0){
                    result.put("id",information.getId());
                }
                result.put("account",information.getAccount());
                result.put("password",information.getPassword());
                result.put("date",information.getDate());
                if(JudgeEmpty.isNotEmpty(information.getHeadPortrait())){
                    String resource= fileOperation.transFileToString(information.getHeadPortrait());
                    information.setHeadPortraitResource(resource);
                    result.put("headPortrait",information.getHeadPortraitResource());
                }
                result.put("nickName",information.getNickName());
                result.put("sex",information.isSex());
                if(JudgeEmpty.isNotEmpty(information.getInterest())){
                    result.put("interest",information.getInterest().toString());
                }
                result.put("school",information.getSchool());
                result.put("major",information.getMajor());
                result.put("background",information.getBackground());
                result.put("resume",information.getResume());
            }catch(JSONException e){
                Log.e("jsonObjectException","将Information转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 功能
     * 获取传入json文件中的Information值
     *
     * 使用方法
     * 1.传入携带个人信息的JSON文件
     * 2.通过返回值获取转换的Information对象
     *
     * 注意
     *
     * @param object
     * @return
     */
    public Information getInformationFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Information information=new Information();
            try{
                if(object.has("id")){
                    information.setId(object.getLong("id"));
                }
                if(object.has("account")){
                    information.setAccount(object.getString("account"));
                }
                if(object.has("password")){
                    information.setPassword(object.getString("password"));
                }
                if(object.has("date")){
                    information.setDate(object.getString("date"));
                }
                if(object.has("headPortrait")){
                    information.setHeadPortraitResource(object.getString("headPortrait"));
                }
                if(object.has("headPortraitName")){
                    information.setHeadPortraitName(object.getString("headPortraitName"));
                }
                if(object.has("nickName")){
                    information.setNickName(object.getString("nickName"));
                }
                if(object.has("sex")){
                    information.setSex(object.getBoolean("sex"));
                }
                if(object.has("interest")){
                    String interests=object.getString("interest");
                    information.setInterest(changeStringToList(interests));
                }
                if(object.has("school")){
                    information.setSchool(object.getString("school"));
                }
                if(object.has("major")){
                    information.setMajor(object.getString("major"));
                }
                if(object.has("background")){
                    information.setBackground(object.getString("background"));
                }
                if(object.has("resume")){
                    information.setResume(object.getString("resume"));
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换Information出现错误");
                e.printStackTrace();
            }
            if(JudgeEmpty.isNotEmpty(information.getHeadPortraitResource())&&JudgeEmpty.isNotEmpty(information.getHeadPortraitName())){
                File headPortrait= fileOperation.transStringToFile(information.getHeadPortraitResource(),information.getHeadPortraitName());
                information.setHeadPortrait(headPortrait);
            }
            return information;
        }
    }

    /**
     * 功能
     * 获取传入json文件中的结果,用于解析post数据到服务器中返回的json文件
     *
     * 使用方法
     * 1.传入携带结果的JSON文件
     * 2.通过返回值获取结果
     *
     * 注意
     *
     * @param object
     * @return
     */
    public String getResultFromJSON(JSONObject object){
        String result=new String();
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            try{
                if(object.get("result") instanceof String){
                    result=(String) object.get("result");
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换personalInformation出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 功能
     * 将json文件中的List字符串转换回List
     *
     * 使用方法
     * 1.传入形如[data1, data2, data3...]的字符串
     * 2.
     * @param data
     * @return
     */
    private List<String> changeStringToList(String data){
        List<String> result=new ArrayList<>();
        data=data.substring(1);  //去除开头的"["
        data=data.substring(0,data.length()-1);   //去除结尾的"]"
        String[] strs=data.split(",");
        for(int i=0;i<strs.length;i++){
            result.add(strs[i].trim());
        }
        return result;
    }

    /**
     * 功能
     * 将json文件中的Map字符串转换回Map
     *
     * 使用方法
     * 1.传入形如[data1, data2, data3...]的字符串
     * 2.
     * @param data
     * @return
     */
    private Map<String,String> changeStringToMap(String data){
        data=data.substring(1,data.length()-1);
        String[] strs=data.split(",");
        Map<String,String> result=new LinkedHashMap<>();
        for(int i=0,length=strs.length;i<length;i++){
            int flag=strs[i].indexOf("=");
            result.put(strs[i].substring(0,flag).trim(),strs[i].substring(flag+1).trim());
        }
        return result;
    }

    private JSONObjectOperation(){
        fileOperation =FileOperation.getOperation();
    }
}
