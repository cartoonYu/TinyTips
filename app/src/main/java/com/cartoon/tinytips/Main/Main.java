package com.cartoon.tinytips.Main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Discover.Discover;
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.Login.Login;
import com.cartoon.tinytips.Message.Message;
import com.cartoon.tinytips.Note.Addnote.AddNote;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class Main extends BaseActivity<MainPresenter> implements IMain.View{

    private FragmentConstant constant;

    private int flag;     //决定切换之前显示的fragment

    private int fragment;               //将底部栏上的FrameLayout抽象成成员变量

    Information information;


    @BindViews({R.id.mainHomepage,R.id.mainMessage,R.id.mainAddNote,R.id.mainDiscover,R.id.mainPersonal})
    List<RelativeLayout> bottomBar;

    @BindView(R.id.mainHomepageP)
    ImageView mainHomepageP;

    @BindView(R.id.mainDiscoverP)
    ImageView mainDiscoverP;

    @BindView(R.id.mainMessageP)
    ImageView mainMessageP;

    @BindView(R.id.mainPersonalP)
    ImageView mainPersonalP;

    @BindView(R.id.mainHomepageT)
    TextView mainHomepageT;

    @BindView(R.id.mainMessageT)
    TextView mainMessageT;

    @BindView(R.id.mainDiscoverT)
    TextView mainDiscoverT;

    @BindView(R.id.mainPersonalT)
    TextView mainPersonalT;


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
        constant=FragmentConstant.getConstant();
        mainHomepageP.setImageResource(R.mipmap.bottombar_homepage_press);
        mainHomepageT.setTextColor(Color.parseColor("#7ae1f7"));
        flag= IntentActivity.getIntentData(this,new String("main"), constant.getHomePage());
        ToChangeBar(flag);
    }

    @Override
    protected void onPrepare(){
        fragment=R.id.mainFragement;
        switchFragment(constant.getDefaultFragment());
        switchFragment(flag);
        information = new Information();
        if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentInformation(this,"start"))){
            information=IntentActivity.getIntentInformation(this,"start");
        }else if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentInformation(this,"personal"))){
            information=IntentActivity.getIntentInformation(this,"personal");
        }
    }

    @OnClick(R.id.mainHomepage)
    public void clickHomepage(){
        //点击首页
        ToChangeBar(0);
        switchFragment(constant.getHomePage());
    }

    @OnClick(R.id.mainMessage)
    public void clickMessage(){
        //点击消息
        ToChangeBar(1);
        switchFragment(constant.getMessage());
    }

    @OnClick(R.id.mainAddNoteB)
    public void clickAddNote(){
        //点击新增
        switchFragment(constant.getAddNote());

    }

    @OnClick(R.id.mainDiscover)
    public void clickDiscover(){
        //点击发现
        ToChangeBar(3);
        switchFragment(constant.getDiscover());
    }

    @OnClick(R.id.mainPersonal)
    public void clickPersonal(){
        //点击我的
        ToChangeBar(4);
        switchFragment(constant.getPersonal());
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

    private void ToChangeBar(int flag){
        switch (flag){
            case 0 :{
                mainHomepageP.setImageResource(R.mipmap.bottombar_homepage_press);
                mainDiscoverP.setImageResource(R.mipmap.bottombar_discover_unpress);
                mainMessageP.setImageResource(R.mipmap.bottombar_message_unpress);
                mainPersonalP.setImageResource(R.mipmap.bottombar_personal_unpress);
                mainHomepageT.setTextColor(Color.parseColor("#7ae1f7"));
                mainMessageT.setTextColor(Color.BLACK);
                mainDiscoverT.setTextColor(Color.BLACK);
                mainPersonalT.setTextColor(Color.BLACK);
                break;
            }

            case 1:{
                mainHomepageP.setImageResource(R.mipmap.bottombar_homepage_unpress);
                mainDiscoverP.setImageResource(R.mipmap.bottombar_discover_unpress);
                mainMessageP.setImageResource(R.mipmap.bottombar_message_press);
                mainPersonalP.setImageResource(R.mipmap.bottombar_personal_unpress);
                mainHomepageT.setTextColor(Color.BLACK);
                mainMessageT.setTextColor(Color.parseColor("#7ae1f7"));
                mainDiscoverT.setTextColor(Color.BLACK);
                mainPersonalT.setTextColor(Color.BLACK);
                break;
            }

            case 3:{
                mainHomepageP.setImageResource(R.mipmap.bottombar_homepage_unpress);
                mainDiscoverP.setImageResource(R.mipmap.bottombar_discover_press);
                mainMessageP.setImageResource(R.mipmap.bottombar_message_unpress);
                mainPersonalP.setImageResource(R.mipmap.bottombar_personal_unpress);
                mainHomepageT.setTextColor(Color.BLACK);
                mainMessageT.setTextColor(Color.BLACK);
                mainDiscoverT.setTextColor(Color.parseColor("#7ae1f7"));
                mainPersonalT.setTextColor(Color.BLACK);
                break;
            }

            case 4:{
                mainHomepageP.setImageResource(R.mipmap.bottombar_homepage_unpress);
                mainDiscoverP.setImageResource(R.mipmap.bottombar_discover_unpress);
                mainMessageP.setImageResource(R.mipmap.bottombar_message_unpress);
                mainPersonalP.setImageResource(R.mipmap.bottombar_personal_press);
                mainHomepageT.setTextColor(Color.BLACK);
                mainMessageT.setTextColor(Color.BLACK);
                mainDiscoverT.setTextColor(Color.BLACK);
                mainPersonalT.setTextColor(Color.parseColor("#7ae1f7"));
                break;
            }
        }
    }
}
