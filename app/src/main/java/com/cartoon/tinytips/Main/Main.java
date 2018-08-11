package com.cartoon.tinytips.Main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Discover.Discover;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.Message.Message;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.FragmentConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

public class Main extends BaseActivity<MainPresenter> implements IMain.View{

    private int flag;     //决定切换之前显示的fragment

    private int fragment;               //将底部栏上的FrameLayout抽象成成员变量

    private List<Integer> unPressdrawables;      //底部栏五个默认按钮图标
    private List<Integer> pressdrawables;      //底部栏五个默认按钮已按图标



    @BindViews({R.id.mainHomepage,R.id.mainMessage,R.id.mainAddNote,R.id.mainDiscover,R.id.mainPersonal})
    List<Button> bottomBar;

    @Override
    protected MainPresenter initPresent(){
        return new MainPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.main;
    }
    @Override
    protected void initView(){
        pressdrawables=new ArrayList<>();
        pressdrawables.add(R.mipmap.bottombar_homepage_press);
        pressdrawables.add(R.mipmap.bottombar_message_press);
        pressdrawables.add(R.mipmap.bottombar_addnote);
        pressdrawables.add(R.mipmap.bottombar_discover_press);
        pressdrawables.add(R.mipmap.bottombar_personal_press);
        Intent intent=getIntent();
        flag=intent.getIntExtra("main", FragmentConstant.homePage);
        Drawable top=getResources().getDrawable(pressdrawables.get(flag));
        bottomBar.get(flag).setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
    }
    @Override
    protected void onPrepare(){
        unPressdrawables=new ArrayList<>();
        unPressdrawables.add(R.mipmap.bottombar_homepage_unpress);
        unPressdrawables.add(R.mipmap.bottombar_message_unpress);
        unPressdrawables.add(R.mipmap.bottombar_addnote);
        unPressdrawables.add(R.mipmap.bottombar_discover_unpress);
        unPressdrawables.add(R.mipmap.bottombar_personal_unpress);
        fragment=R.id.mainFragement;
        switchFragment(FragmentConstant.defaultFragment);
        switchFragment(flag);

    }
    @OnClick(R.id.mainHomepage)
    public void clickHomepage(){
        //点击首页
        setBottomBarDrawable(FragmentConstant.homePage,R.mipmap.bottombar_homepage_press);
        switchFragment(FragmentConstant.homePage);
    }
    @OnClick(R.id.mainMessage)
    public void clickMessage(){
        //点击消息
        setBottomBarDrawable(FragmentConstant.message,R.mipmap.bottombar_message_press);
        switchFragment(FragmentConstant.message);
    }
    @OnClick(R.id.mainAddNote)
    public void clickAddNote(){
        //点击新增
        switchFragment(FragmentConstant.addNote);
    }
    @OnClick(R.id.mainDiscover)
    public void clickDiscover(){
        //点击发现
        setBottomBarDrawable(FragmentConstant.discover,R.mipmap.bottombar_discover_press);
        switchFragment(FragmentConstant.discover);
    }
    @OnClick(R.id.mainPersonal)
    public void clickPersonal(){
        //点击我的
        setBottomBarDrawable(FragmentConstant.personal,R.mipmap.bottombar_personal_press);
        switchFragment(FragmentConstant.personal);
    }

    @Override
    public void setBottomBarDrawable(int flag,int drawable) {
        for(int i=0;i<unPressdrawables.size();i++){
            if(i!=2){
                Drawable temp=getResources().getDrawable(unPressdrawables.get(i));
                bottomBar.get(i).setCompoundDrawablesWithIntrinsicBounds(null,temp,null,null);
            }
        }
        Drawable top=getResources().getDrawable(drawable);
        bottomBar.get(flag).setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
    }
    @Override
    public void switchFragment(int flag){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (flag){
            case FragmentConstant.defaultFragment:{
                Homepage homepage=new Homepage();
                transaction.add(fragment,homepage);
                break;
            }
            case FragmentConstant.homePage:{
                Homepage homepage=new Homepage();
                transaction.replace(fragment,homepage);
                break;
            }
            case FragmentConstant.message:{
                Message message=new Message();
                transaction.replace(fragment,message);
                break;
            }
            case FragmentConstant.addNote:{
                break;
            }
            case FragmentConstant.discover:{
                Discover discover=new Discover();
                transaction.replace(fragment,discover);
                break;
            }
            case FragmentConstant.personal:{
                Personal personal=new Personal();
                transaction.replace(fragment,personal);
                break;
            }
        }
        transaction.commit();
    }

}
