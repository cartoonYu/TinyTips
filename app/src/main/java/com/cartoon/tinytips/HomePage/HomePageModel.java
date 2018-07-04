package com.cartoon.tinytips.HomePage;

import com.cartoon.tinytips.util.DbUtil.DatabaseUtil;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许锦鹏 on 2018/2/24.
 */

public class HomePageModel implements IHomePage.Model {

    private List<Note> noteList;

    private String word;      //用户输入的关键词

    public HomePageModel() {
        super();
        noteList=new ArrayList<>();
    }
    @Override
    public void getHomePageMessages(ValueCallBack<List<Note>> callBack) {
        noteList= DatabaseUtil.queryNote("Note",
                null,null,null,null,null,null,null);

        if (noteList.isEmpty()){
            callBack.onFail("笔记初始化失败，请重试");
        }else {
            callBack.onSuccess(noteList);
        }
    }
    @Override
    public void setWord(String word){
        this.word=word;
    }
    @Override
    public void searchData(ValueCallBack<List<Note>> callBack){
        //成员变量word就是用户输入的分类

    }
}
