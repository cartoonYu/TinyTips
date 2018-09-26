package com.cartoon.tinytips.Main;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Discover.Discover;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.Message.Message;
import com.cartoon.tinytips.Note.Addnote.AddNote;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

public class Main extends BaseActivity<MainPresenter> implements IMain.View{

    private FragmentConstant constant;

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
        constant=FragmentConstant.getConstant();
        pressdrawables.add(R.mipmap.bottombar_homepage_press);
        pressdrawables.add(R.mipmap.bottombar_message_press);
        pressdrawables.add(R.mipmap.bottombar_addnote);
        pressdrawables.add(R.mipmap.bottombar_discover_press);
        pressdrawables.add(R.mipmap.bottombar_personal_press);
        flag= IntentActivity.getIntentData(this,new String("main"), constant.getHomePage());
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
        switchFragment(constant.getDefaultFragment());
        switchFragment(flag);

    }

    @OnClick(R.id.mainHomepage)
    public void clickHomepage(){
        //点击首页
        setBottomBarDrawable(constant.getHomePage(),R.mipmap.bottombar_homepage_press);
        switchFragment(constant.getHomePage());
    }

    @OnClick(R.id.mainMessage)
    public void clickMessage(){
        //点击消息
        setBottomBarDrawable(constant.getMessage(),R.mipmap.bottombar_message_press);
        switchFragment(constant.getMessage());
    }

    @OnClick(R.id.mainAddNote)
    public void clickAddNote(){
        //点击新增
        switchFragment(constant.getAddNote());

    }

    @OnClick(R.id.mainDiscover)
    public void clickDiscover(){
        //点击发现
        setBottomBarDrawable(constant.getDiscover(),R.mipmap.bottombar_discover_press);
        switchFragment(constant.getDiscover());
    }

    @OnClick(R.id.mainPersonal)
    public void clickPersonal(){
        //点击我的
        setBottomBarDrawable(constant.getPersonal(),R.mipmap.bottombar_personal_press);
        switchFragment(constant.getPersonal());
    }


    private void setBottomBarDrawable(int flag,int drawable) {
        for(int i=0;i<unPressdrawables.size();i++){
            if(i!=2){
                Drawable temp=getResources().getDrawable(unPressdrawables.get(i));
                bottomBar.get(i).setCompoundDrawablesWithIntrinsicBounds(null,temp,null,null);
            }
        }
        Drawable top=getResources().getDrawable(drawable);
        bottomBar.get(flag).setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
    }

    private void switchFragment(int flag){
        if(flag!=constant.getDefaultFragment()&&flag!=constant.getAddNote()){
            this.flag=flag;
        }
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
                IntentActivity.intentWithData(this,AddNote.class,"addNote",this.flag);
                IntentActivity.finishActivity(this);
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
