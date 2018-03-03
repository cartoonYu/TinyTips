package com.cartoon.tinytips.HomePage.AddNote;

import android.content.Context;

import com.cartoon.tinytips.DbUtil.MyDatabaseHelper;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.TableNote.Note;

import java.util.List;

/**
 * Created by cartoon on 2018/2/17.
 */

interface IAddNote {
    interface View{
        void handleClickBack();
        void handleClickSave();
        void handleClickMenu();
        void handleClickAddTitle();
        void handleClickAddClassify();
        void handleClickClassify(int i);
        void handleClickSelectPhoto();
        String getDate();
        String getAuthor();
        String getDetails();
        String[] getClassify(List<String> classify);
        Boolean getIsCollect();
        String getNoteTitle();
        String getImageDetails();
        void showToast(String code);
    }
    interface Presenter{
        void saveNote(String title, String date,String author,String imageDetails,String details,String[] classify,boolean isCollect);
        void addNote();
    }
    interface Model{
        void addNote(ValueCallBack<String> callBack);
        void setNote(String title,String date, String author,String imageDetails, String details, String[] classify, boolean isCollect);
    }
}
