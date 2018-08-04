package com.cartoon.tinytips.Main;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Discover.Discover;
import com.cartoon.tinytips.Homepage.Homepage;
import com.cartoon.tinytips.Message.Message;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class Main extends BaseActivity<MainActivityPresenter> implements IMain.View{

    private MainActivityPresenter presenter;

    private int fragment;               //将底部栏上的FrameLayout抽象成成员变量

   @BindViews({R.id.mainHomepage,R.id.mainMessage,R.id.mainAddNote,R.id.mainDiscover,R.id.mainPersonal})
    List<Button> bottomBar;

    @Override
    protected MainActivityPresenter initPresent(){
        presenter=new MainActivityPresenter(this);
        return presenter;
    }
    @Override
    protected int getLayout(){
        return R.layout.main;
    }
    @Override
    protected void initView(){
        Drawable top=getResources().getDrawable(R.mipmap.bottombar_homepage_press);
        bottomBar.get(0).setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
    }
    @Override
    protected void onPrepare(){
        fragment=R.id.mainFragement;
        switchFragment(-1);
        switchFragment(0);
    }
    @OnClick(R.id.mainHomepage)
    public void clickHomepage(){
        //点击首页
        setBottomBarDrawable(0,R.mipmap.bottombar_homepage_press);
        switchFragment(0);
    }
    @OnClick(R.id.mainMessage)
    public void clickMessage(){
        //点击消息
        setBottomBarDrawable(1,R.mipmap.bottombar_message_press);
        switchFragment(1);
    }
    @OnClick(R.id.mainAddNote)
    public void clickAddNote(){
        //点击新增
        switchFragment(2);
    }
    @OnClick(R.id.mainDiscover)
    public void clickDiscover(){
        //点击发现
        setBottomBarDrawable(3,R.mipmap.bottombar_discover_press);
        switchFragment(3);
    }
    @OnClick(R.id.mainPersonal)
    public void clickPersonal(){
        //点击我的
        setBottomBarDrawable(4,R.mipmap.bottombar_presonal_press);
        switchFragment(4);
    }

    @Override
    public void setBottomBarDrawable(int flag,int drawable) {
        Drawable top=getResources().getDrawable(drawable);
        bottomBar.get(flag).setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
    }
    @Override
    public void switchFragment(int flag){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (flag){
            case -1:{
                Homepage homepage=new Homepage();
                transaction.add(fragment,homepage);
                break;
            }
            case 0:{
                Homepage homepage=new Homepage();
                transaction.replace(fragment,homepage);
                break;
            }
            case 1:{
                Message message=new Message();
                transaction.replace(fragment,message);
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                Discover discover=new Discover();
                transaction.replace(fragment,discover);
                break;
            }
            case 4:{
                Personal personal=new Personal();
                transaction.replace(fragment,personal);
                break;
            }
        }
        transaction.commit();
    }
}
