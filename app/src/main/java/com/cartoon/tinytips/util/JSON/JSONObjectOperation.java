package com.cartoon.tinytips.util.JSON;

import android.util.Log;

import com.cartoon.tinytips.bean.table.Comment;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Social;
import com.cartoon.tinytips.bean.view.CommentDetails;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.file.FileOperation;
import com.cartoon.tinytips.util.JudgeEmpty;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     * 将传入的social转换成json文件
     * @param social
     * @return
     */
    public JSONObject setSocialToJSON(Social social,String method){
        if(JudgeEmpty.isEmpty(social)){
            return null;
        }
        JSONObject result=new JSONObject();
        try {
            result.put("method",method);
            if(social.getUserId()!=0){
                result.put("userId",social.getUserId());
            }
            if(JudgeEmpty.isNotEmpty(social.getType())){
                result.put("type",social.getType());
            }
            if(social.getNoteId()!=0){
                result.put("noteId",social.getNoteId());
            }
            if(JudgeEmpty.isNotEmpty(social.getDate())){
                result.put("date",social.getDate());
            }
        }catch (JSONException e){
            e.printStackTrace();
            Log.d("jsonObjectException","将Social转换成JSON文件发生错误");
        }
        return result;
    }

    public JSONObject setSocialToJSON(Social social){
        if(JudgeEmpty.isEmpty(social)){
            return null;
        }
        JSONObject result=new JSONObject();
        try {
            if(social.getUserId()!=0){
                result.put("userId",social.getUserId());
            }
            if(JudgeEmpty.isNotEmpty(social.getType())){
                result.put("type",social.getType());
            }
            if(social.getNoteId()!=0){
                result.put("noteId",social.getNoteId());
            }
            if(JudgeEmpty.isNotEmpty(social.getDate())){
                result.put("date",social.getDate());
            }
        }catch (JSONException e){
            e.printStackTrace();
            Log.d("jsonObjectException","将Social转换成JSON文件发生错误");
        }
        return result;
    }

    /**
     * 将传入的json文件转化成Social
     * @param object
     * @return
     */
    public Social getSocialFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        Social social=new Social();
        try {
            if(object.has("type")){
                social.setType(object.getString("type"));
            }
            if(object.has("userId")){
                social.setUserId(object.getLong("userId"));
            }
            if(object.has("noteId")){
                social.setNoteId(object.getLong("noteId"));
            }
            if(object.has("date")){
                social.setDate(object.getString("date"));
            }
        }catch (JSONException e){
            Log.d("jsonObjectException","将JSON文件转换成Social发生错误");
        }
        return social;
    }

    /**
     * 将传入的commentDetails转换成json文件
     * @param comment
     * @return
     */
    public JSONObject setCommentToJSON(Comment comment){
        if(JudgeEmpty.isEmpty(comment)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                if(comment.getNoteId()!=0){
                    result.put("noteId",comment.getNoteId());
                }
                if(comment.getUserId()!=0){
                    result.put("userId",comment.getUserId());
                }
                result.put("details",comment.getDetails());
            }catch(JSONException e){
                Log.e("jsonObjectException","将commentDetails转换json文件出现错误");
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
            Comment commentDetails=new Comment();
            try{
                if(object.has("noteId")){
                    if(object.getLong("noteId")!=0){
                        commentDetails.setNoteId(object.getLong("noteId"));
                    }
                }
                if(object.has("userId")){
                    if(object.getLong("userId")!=0){
                        commentDetails.setUserId(object.getLong("userId"));
                    }
                }
                if(object.has("details")){
                    commentDetails.setDetails(object.getString("details"));
                }
                if(object.has("date")){
                    commentDetails.setDate(object.getString("date"));
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
            JSONObject result=new JSONObject();
            try{
                if(note.getUserId()!=0){
                    result.put("userId",note.getUserId());
                }
                if(note.getId()!=0){
                    result.put("id",note.getId());
                }
                if(JudgeEmpty.isNotEmpty(note.getTitle())){
                    result.put("title",note.getTitle());
                }
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
    public JSONObject setInformationToJSON(Information information){
        if(JudgeEmpty.isEmpty(information)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                if(information.getId()!=0){
                    result.put("id",information.getId());
                }
                if(JudgeEmpty.isNotEmpty(information.getAccount())){
                    result.put("account",information.getAccount());
                }
                if(JudgeEmpty.isNotEmpty(information.getPassword())){
                    result.put("password",information.getPassword());
                }
                if(JudgeEmpty.isNotEmpty(information.getDate())){
                    result.put("date",information.getDate());
                }
                if(JudgeEmpty.isNotEmpty(information.getHeadPortrait())){
                    String resource= fileOperation.transFileToString(information.getHeadPortrait());
                    information.setHeadPortraitResource(resource);
                    result.put("headPortrait",information.getHeadPortraitResource());
                }
                if(JudgeEmpty.isNotEmpty(information.getNickName())){
                    result.put("nickName",information.getNickName());
                }
                if(JudgeEmpty.isNotEmpty(information.isSex())){
                    result.put("sex",information.isSex());
                }
                if(JudgeEmpty.isNotEmpty(information.getSchool())){
                    result.put("school",information.getSchool());
                }
                if(JudgeEmpty.isNotEmpty(information.getInterest())){
                    result.put("interest",information.getInterest().toString());
                }
                if(JudgeEmpty.isNotEmpty(information.getMajor())){
                    result.put("major",information.getMajor());
                }
                if(JudgeEmpty.isNotEmpty(information.getBackground())){
                    result.put("background",information.getBackground());
                }
                if(JudgeEmpty.isNotEmpty(information.getResume())){
                    result.put("resume",information.getResume());
                }
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
     * 获取传入json文件的StatSocial
     * @param object
     * @return
     */
    public StatSocial getStatSocialFromJSON(JSONObject object){
        StatSocial data=new StatSocial();
        try {
            if(object.has("userId")&&object.getLong("userId")!=0){
                data.setUserId(object.getLong("userId"));
            }
            if(object.has("headPortrait")){
                data.setHeadPortraitResource(object.getString("headPortrait"));
            }
            if(object.has("headPortraitName")){
                data.setHeadPortraitName(object.getString("headPortraitName"));
            }
            if(JudgeEmpty.isNotEmpty(data.getHeadPortraitResource())&&JudgeEmpty.isNotEmpty(data.getHeadPortraitName())){
                data.setHeadPortrait(fileOperation.transStringToFile(data.getHeadPortraitResource(),data.getHeadPortraitName()));
            }
            if(object.has("nickName")){
                data.setNickName(object.getString("nickName"));
            }
            if(object.has("noteId")&&object.getLong("noteId")!=0){
                data.setNoteId(object.getLong("noteId"));
            }
            if(object.has("title")){
                data.setTitle(object.getString("title"));
            }
            if(object.has("word")){
                data.setWordDetails(changeStringToList(object.getString("word")));
            }
            if(object.has("photo")){
                data.setPhotoSource(changeStringToMap(object.getString("photo")));
            }
            if(object.has("date")){
                data.setDate(object.getString("date"));
            }
            if(object.has("numOfLove")){
                data.setNumOfLove(object.getInt("numOfLove"));
            }
            if(object.has("loveList")){
                List<String> temp=changeStringToList(object.getString("loveList"));
                data.setLoveList(getStatSocialList(temp));
            }
            if(object.has("numOfComment")){
                data.setNumOfComment(object.getInt("numOfComment"));
            }
            if(object.has("commentList")){
                List<String> temp=changeStringToList(object.getString("commentList"));
                data.setCommentList(getStatSocialList(temp));
            }
            if(object.has("numOfCollect")){
                data.setNumOfCollect(object.getInt("numOfCollect"));
            }
            if(object.has("collectList")){
                List<String> temp=changeStringToList(object.getString("collectList"));
                data.setCollectList(getStatSocialList(temp));
            }
            if(object.has("numOfForward")){
                data.setNumOfForward(object.getInt("numOfForward"));
            }
            if(object.has("forwardList")){
                List<String> temp=changeStringToList(object.getString("forwardList"));
                data.setForwardList(getStatSocialList(temp));
            }
        }catch (JSONException e){
            Log.d("jsonObjectException","将json文件转换成statSocial发生错误");
        }
        return data;
    }

    /**
     * 将StatSocial对象转换成json文件
     * @param social
     * @return
     */
    public JSONObject setStatSocialToJSON(StatSocial social){
        JSONObject result=new JSONObject();
        try {
            if(social.getUserId()!=0){
                result.put("userId",social.getUserId());
            }
            if(JudgeEmpty.isNotEmpty(social.getHeadPortrait())){
                result.put("headPortrait",social.getHeadPortrait());
            }
            if(JudgeEmpty.isNotEmpty(social.getHeadPortraitName())){
                result.put("headPortraitName",social.getHeadPortraitName());
            }
            if(JudgeEmpty.isNotEmpty(social.getNickName())){
                result.put("nickName",social.getNickName());
            }
            if(social.getNoteId()!=0){
                result.put("noteId",social.getNoteId());
            }
            if(JudgeEmpty.isNotEmpty("title")){
                result.put("title",social.getTitle());
            }
            if(JudgeEmpty.isNotEmpty(social.getWordDetails())){
                result.put("word",social.getWordDetails());
            }
            if(JudgeEmpty.isNotEmpty(social.getPhoto())){
                result.put("photo",social.getPhoto().toString());
            }
            if(JudgeEmpty.isNotEmpty("date")){
                result.put("date",social.getDate());
            }
            if(JudgeEmpty.isNotEmpty(social.getLoveList())){
                result.put("loveList",social.getLoveList().toString());
            }
            if(JudgeEmpty.isNotEmpty(social.getCollectList())){
                result.put("collectList",social.getCollectList().toString());
            }
            if(JudgeEmpty.isNotEmpty(social.getCommentList())){
                result.put("commentList",social.getCommentList().toString());
            }
            if(JudgeEmpty.isNotEmpty(social.getForwardList())){
                result.put("forwardList",social.getForwardList().toString());
            }
            result.put("numOfLove",social.getNumOfLove());
            result.put("numOfComment",social.getNumOfComment());
            result.put("numOfCollect",social.getNumOfCollect());
            result.put("numOfForward",social.getNumOfForward());
        }catch (JSONException e){
            Log.d("jsonObjectException","将statSocial转换成json文件发生错误");
        }
        return result;
    }

    /**
     * 获取传入json文件的CommentDetails
     * @param object
     * @return
     */
    public CommentDetails getCommentDetailsFromJSON(JSONObject object){
        CommentDetails details=new CommentDetails();
        try {
            if(object.has("noteId")){
                details.setNoteId(object.getLong("noteId"));
            }
            if(object.has("userId")){
                details.setUserId(object.getLong("userId"));
            }
            if(object.has("date")){
                details.setDate(object.getString("date"));
            }
            if(object.has("headPortrait")){
                details.setHeadPortraitResource(object.getString("headPortrait"));
            }
            if(object.has("headPortraitName")){
                details.setHeadPortraitName(object.getString("headPortraitName"));
            }
            if(object.has("nickName")){
                details.setNickName(object.getString("nickName"));
            }
            if(object.has("details")){
                details.setDetails(object.getString("details"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return details;
    }

    /**
     * 将CommentDetails转换成JSON文件
     * @param details
     * @return
     */
    public JSONObject setCommentDetailsToJSON(CommentDetails details){
        JSONObject object=new JSONObject();
        try {
            if(details.getNoteId()!=0){
                object.put("noteId",details.getNoteId());
            }
            if(details.getUserId()!=0){
                object.put("userId",details.getUserId());
            }
            if(JudgeEmpty.isNotEmpty(details.getDate())){
                object.put("date",details.getDate());
            }
            if(JudgeEmpty.isNotEmpty(details.getDetails())){
                object.put("details",details.getDetails());
            }
            if(JudgeEmpty.isNotEmpty(details.getHeadPortrait())){
                object.put("headPortrait",details.getHeadPortraitResource());
            }
            if(JudgeEmpty.isNotEmpty(details.getHeadPortraitName())){
                object.put("headPortraitName",details.getHeadPortraitName());
            }
            if(JudgeEmpty.isNotEmpty(details.getNickName())){
                object.put("nickName",details.getNickName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
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
     * 遍历json文件，用于测试
     * @param object
     */
    public void displayJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return;
        }
        Iterator<String> it=object.keys();
        while (it.hasNext()){
            String key=it.next();
            Object value= null;
            try {
                value = object.get(key);

                if(value instanceof List){
                    List list=(List)value;
                    Log.d("displayJSON",key+":"+Integer.toString(list.size()));
                }
                else {
                    Log.d("displayJSON",key+":"+value.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d("displayJSON","-----------------------------------");
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

    /**
     * 获取statSocial中点赞，收藏，转发，评论列表
     * @param list
     * @return
     */
    private List<Integer> getStatSocialList(List<String> list){
        List<Integer> data=new ArrayList<>();
        if(JudgeEmpty.isEmpty(list)){
            return data;
        }
        for(String s:list){
            if(!s.equals("")){
                data.add(Integer.parseInt(s));
            }
        }
        return data;
    }

    private JSONObjectOperation(){
        fileOperation =FileOperation.getOperation();
    }
}
