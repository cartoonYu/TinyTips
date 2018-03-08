package com.cartoon.tinytips.HomePage.Details;


import com.cartoon.tinytips.DbUtil.DatabaseUtil;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

/**
 * Created by cartoon on 2018/2/28.
 */

class HomePageDetailsModel implements IHomePageDetails.Model{
    private Note note;
    @Override
    public void deleteNote(ValueCallBack<String> callBack){
        if ((note.getDetails().equals(null))&&note.getAuthor().equals(null)){
            callBack.onFail("笔记删除失败，请重试");
        }else{
            new android.os.Handler().post(new Runnable() {
                @Override
                public void run() {
                    //删除逻辑在这里填写
                    DatabaseUtil.deleteNote("Note","details=? and title=?",new String[]{note.getDetails(),note.getTitle()});

                }
            });
            callBack.onSuccess("删除笔记成功");
        }
    }
    @Override
    public void setNote(Note note){
        this.note=note;
    }
}
