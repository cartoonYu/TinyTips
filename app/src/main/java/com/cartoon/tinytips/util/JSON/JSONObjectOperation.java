package com.cartoon.tinytips.util.JSON;

import android.util.Log;

import com.cartoon.tinytips.bean.Comment;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
     * 将传入的comment转换成json文件
     * @param comment
     * @return
     */
    public JSONObject setCommentToJSON(Comment comment){
        if(JudgeEmpty.isEmpty(comment)){
            return null;
        }
        else{
            JSONObject jsonObject=new JSONObject();
            try{
                jsonObject.put("comment",comment);
            }catch(JSONException e){
                Log.e("jsonObjectException","将comment转换json文件出现错误");
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
    public Comment getCommentFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Comment comment=new Comment();
            try{
                if(object.get("comment") instanceof Comment){
                    comment=(Comment)object.get("comment");
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换commentDetails出现错误");
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
    public JSONObject setNoteToJSON(Note note){
        if(JudgeEmpty.isEmpty(note)){
            return null;
        }
        else{
            JSONObject jsonObject=new JSONObject();
            try{
                jsonObject.put("note",note);
            }catch(JSONException e){
                Log.e("jsonObjectException","将note转换json文件出现错误");
                e.printStackTrace();
            }
            return jsonObject;
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
                if(object.get("note") instanceof Note){
                    note=(Note) object.get("note");
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换note出现错误");
                e.printStackTrace();
            }
            return note;
        }
    }

    /**
     * 将传入的Information转换成json文件
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
                result.put("headPortraitPath",information.getHeadPortrait());
                result.put("nickName",information.getNickName());
                result.put("sex",information.isSex());
                result.put("interest",information.getInterest());
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
     * 获取传入json文件中的Information值
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
                if(object.has("headPortraitPath")){
                    information.setHeadPortraitPath(object.getString("headPortraitPath"));
                }
                if(object.has("nickName")){
                    information.setNickName(object.getString("nickName"));
                }
                if(object.has("sex")){
                    information.setSex(object.getBoolean("sex"));
                }
                /*if(object.has("interest")){
                    information.setInterest((List<String>) object.get("interest"));
                }*/
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
            return information;
        }
    }

    /**
     * 获取传入json文件中的结果,用于解析post数据到服务器中返回的json文件
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

    private JSONObjectOperation(){
    }
}
