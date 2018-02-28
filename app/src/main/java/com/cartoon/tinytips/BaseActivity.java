package com.cartoon.tinytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by cartoon on 2018/2/1.
 * 1.此类为视图（view）基类，本APP所有视图（activity）均要继承此类以及自定义接口进行开发
 */

public abstract class BaseActivity<T extends BaseActivityPresenter> extends AppCompatActivity{
    protected T presenter;       //presenter层的实例对象，用于view与presenter交互
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(getLayout());
        presenter=initPresent();
        initView();
        onPrepare();
    }
    protected abstract T initPresent();      //为presenter添加view弱引用
    protected abstract int getLayout();       //为活动添加页面
    protected abstract void initView();      //实例化组件
    protected abstract void onPrepare();     //对组件进行初始化操作
}
