package com.cartoon.tinytips.HomePage.Details;

import com.cartoon.tinytips.BaseActivityPresenter;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.data.Note;

/**
 * Created by cartoon on 2018/2/11.
 * 1.笔记详情（Details）的Presenter
 *
 * 功能
 * 1.为笔记详情（Details）以及数据库提供中间处理层
 *
 * 操作：
 * 1.定义函数并在View进行调用，利用成员变量view操作View层的函数，实现了View与Presenter的双向调用
 * 2.HomePageDetailsPresenter所有函数应到IHomePageDetails.Presenter定义再在此重写方法
 */

class DetailsPresenter extends BaseActivityPresenter<Details> implements IDetails.Presenter{
    private IDetails.View view;
    private IDetails.Model model;
    public DetailsPresenter(IDetails.View view){
        this.view=view;
        this.model=new DetailsModel();
    }
    @Override
    public void deleteNote(Note note){
        model.setNote(note);
        model.deleteNote(new ValueCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                view.showToast(s);
                view.handleClickBack();
            }

            @Override
            public void onFail(String code) {
                view.showToast(code);
            }
        });
    }
    @Override
    public void deleteView(){
        view=null;
        model=null;
    }
}
