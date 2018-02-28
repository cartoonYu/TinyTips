package com.cartoon.tinytips.Main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Community.Community;
import com.cartoon.tinytips.HomePage.HomePage;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;

/**
 * Created by cartoon on 2018/2/4.
 * 1.主界面，底部导航栏切换首页，社区以及我的三个页面
 * 2.layout:main
 * 3.函数onTabSelected控制页面(HomePage,Community,Personal)的跳转
 * 4.函数switchFragment则是页面跳转的具体实现
 *
 * 功能:
 * 1.为首页（HomePage),社区(Community)，我的(Personal)提供切换支持
 *
 * 操作：
 * 1.在其他活动返回到此活动的时候，初始化页面时底部导航栏还是默认第一个，还不能人为控制
 */

public class Main extends BaseActivity<MainPresenter> implements IMain.View,BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bar;    //底部导航栏实例
    private int fragment;               //将底部栏上的FrameLayout抽象成成员变量

    private Intent intent;             //接收其他页面跳转回来携带的变量
    private int intTemp;               //存储其他页面跳转回来携带的变量的值

    @Override
    protected int getLayout(){
        return R.layout.main;
    }
    @Override
    protected MainPresenter initPresent(){
        return new MainPresenter(this);
    }
    @Override
    protected void initView(){
        bar=findViewById(R.id.mainBottomBar);
        bar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.drawable.homepage,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.community,"社区"))
                .addItem(new BottomNavigationItem(R.drawable.personal,"我的")).initialise();
    }
    @Override
    protected void onPrepare(){
        fragment=R.id.mainFrame;
        intent=getIntent();
        intTemp=intent.getIntExtra("flag",-1);
        switchFragment(intTemp);
        bar.setTabSelectedListener(this);
    }
    @Override
    public void switchFragment(int flag){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        HomePage homePage=new HomePage();
        Community community=new Community();
        Personal personal=new Personal();
        switch (flag){
            case -1:{
                transaction.add(fragment,homePage);
                transaction.commit();
                break;
            }
            case 0:{
                transaction.replace(fragment,homePage);
                transaction.commit();
                break;
            }
            case 1:{
                transaction.replace(fragment,community);
                transaction.commit();
                break;
            }
            case 2:{
                transaction.replace(fragment,personal);
                transaction.commit();
                break;
            }
        }
    }
    @Override
    public void onTabSelected(int position){
        //底部菜单栏从未选中到选中的处理事件
        switchFragment(position);
    }
    @Override
    public void onTabUnselected(int position){
        //底部菜单栏从选中到未选中的处理事件

    }
    @Override
    public void onTabReselected(int position){
        //底部菜单栏重复选中的处理事件
    }
}
