package com.cartoon.tinytips.Main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Discover.Discover;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.Message.Message;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Major;
import com.cartoon.tinytips.util.Adapters.MajorAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class Main extends BaseActivity<MainPresenter> implements IMain.View{

    private MainPresenter presenter;

    private int flag;     //决定切换之前显示的fragment

    private int fragment;               //将底部栏上的FrameLayout抽象成成员变量

    private List<Integer> unPressdrawables;      //底部栏五个默认按钮图标
    private List<Integer> pressdrawables;      //底部栏五个默认按钮已按图标



    @BindViews({R.id.mainHomepage,R.id.mainMessage,R.id.mainAddNote,R.id.mainDiscover,R.id.mainPersonal})
    List<Button> bottomBar;

    @Override
    protected MainPresenter initPresent(){
        presenter=new MainPresenter(this);
        return presenter;
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
        flag=intent.getIntExtra("main",0);
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
        switchFragment(-1);
        switchFragment(flag);

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
        setBottomBarDrawable(4,R.mipmap.bottombar_personal_press);
        switchFragment(4);
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
