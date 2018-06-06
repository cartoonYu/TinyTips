package com.cartoon.tinytips;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cartoon on 2018/2/4.
 * 1.此类为视图（view）基类，本APP所有视图（fragment）类均要继承此类以及自定义接口进行开发
 */

public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment {
    protected T presenter;       //presenter层的实例对象，用于view与presenter交互
    private View view;
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view=inflater.inflate(getLayout(),group,false);
        unbinder= ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        presenter=initPresent();
        initView();
        onPrepare();
    }
    protected abstract T initPresent();      //为presenter添加view弱引用
    protected abstract int getLayout();       //为活动添加页面
    protected abstract void initView();      //实例化组件
    protected abstract void onPrepare();     //对组件进行初始化操作
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}
