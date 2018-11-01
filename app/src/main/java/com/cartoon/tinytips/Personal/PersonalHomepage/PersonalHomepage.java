package com.cartoon.tinytips.Personal.PersonalHomepage;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage.DynamicState;
import com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage.DynamicStateAdapter;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalHomepage extends BaseActivity<PersonalHomepagePresenter> implements IPersonalHomepage.View{

    Information information;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.personal_homepage_HeadPro)
    de.hdodenhof.circleimageview.CircleImageView headPro;     //头像

    @BindView(R.id.personal_homepage_focus)
    TextView focus;     //关注按钮

    @BindView(R.id.personal_homepage_nickName)
    TextView nickName;     //昵称

    @BindView(R.id.personal_homepage_sex)
    ImageView sex;       //性别图片

    @BindView(R.id.personal_homepage_school)
    TextView school;     //学校

    @BindView(R.id.personal_homepage_major)
    TextView major;      //专业

    @BindViews({R.id.personal_homepage_interest1,R.id.personal_homepage_interest2,
            R.id.personal_homepage_interest3,R.id.personal_homepage_interest4})
    List<TextView> interests;    //四个兴趣


    private List<DynamicState> dynamicStateList;
    private List<Note> noteList;
    private DynamicStateAdapter dynamicStateAdapter;

    @BindView(R.id.personal_homepage_dynamicState)
    RecyclerView dynamicState;

    @Override
    protected PersonalHomepagePresenter initPresent(){
        return new PersonalHomepagePresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_homepage;
    }

    @Override
    protected void initView(){
        if ((Information)getIntent().getSerializableExtra("personal")!=null){
            information = (Information)getIntent().getSerializableExtra("personal");
            Log.d("text", "personalHomepage: "+information.getAccount());
        }
        presenter.initData();
        noteList=presenter.getNoteList();
        Log.d("noteList", "getNoteList:111111 "+noteList.size());
        revampStatusBar();
        initDynamicState();
    }

    @Override
    protected void onPrepare(){

    }

    @OnClick(R.id.personal_homepage_back)
    public void clickBack(){

        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal(),new String("personalHomepage"),information);

        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal(),new String("personalHomepage"),information);
        IntentActivity.finishActivity(this);
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.skyBlue);
    }

    @Override
    public void initDynamicState(){
        dynamicStateList=new ArrayList<>();


        DynamicState state=new DynamicState("asd","2018-8-11",2,3,4,5);
        for(int i=0;i<20;i++){
            dynamicStateList.add(state);
        }
        LinearLayoutManager manager=new LinearLayoutManager(this);
        dynamicState.setLayoutManager(manager);
        dynamicStateAdapter=new DynamicStateAdapter(noteList);
        dynamicState.setAdapter(dynamicStateAdapter);
        dynamicState.setFocusableInTouchMode(false);
        dynamicState.requestFocus();
    }

    @Override
    public void setHeadPro(File headPro1) {
        Glide.with(this).load(headPro1).into(headPro);
    }

    @Override
    public void setNickName(String name) {
        this.nickName.setText(name);
    }

    @Override
    public void setSchool(String school) {
        this.school.setText(school);
    }

    @Override
    public void setMajor(String major) {
        this.major.setText(major);
    }

    @Override
    public void setInterset(List<String> interest) {
        for (int i=0;i<interest.size();i++){
            this.interests.get(i).setText(interest.get(i));
        }
    }

    @Override
    public Information getInformation() {
        return information;
    }
}
