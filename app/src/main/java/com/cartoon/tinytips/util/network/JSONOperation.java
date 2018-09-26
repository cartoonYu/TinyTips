package com.cartoon.tinytips.util.network;

import android.util.Log;

import com.cartoon.tinytips.bean.Comment;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.bean.PersonalInformation;
import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * json文件的生成与解析
 */

public class JSONOperation {

    private static volatile JSONOperation operation;

    public static JSONOperation getInstance(){
        if(JudgeEmpty.isEmpty(operation)){
            synchronized (JSONOperation.class){
                if(JudgeEmpty.isEmpty(operation)){
                    operation=new JSONOperation();
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
     * 将传入的personalInformation转换成json文件
     * @param personalInformation
     * @return
     */
    public JSONObject setPersonalInformationToJSON(PersonalInformation personalInformation){
        if(JudgeEmpty.isEmpty(personalInformation)){
            return null;
        }
        else{
            JSONObject jsonObject=new JSONObject();
            try{
                jsonObject.put("personalInformation",personalInformation);
            }catch(JSONException e){
                Log.e("jsonObjectException","将personalInformation转换json文件出现错误");
                e.printStackTrace();
            }
            return jsonObject;
        }
    }

    /**
     * 获取传入json文件中的personalInformation值
     * @param object
     * @return
     */
    public PersonalInformation getPersonalInnformationFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            PersonalInformation personalInformation=new PersonalInformation();
            try{
                if(object.get("personalInformation") instanceof PersonalInformation){
                    personalInformation=(PersonalInformation) object.get("personalInformation");
                }
            }catch(JSONException e){
                Log.e("jsonObjectException","将json文件转换personalInformation出现错误");
                e.printStackTrace();
            }
            return personalInformation;
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

    private JSONOperation(){
    }
}
