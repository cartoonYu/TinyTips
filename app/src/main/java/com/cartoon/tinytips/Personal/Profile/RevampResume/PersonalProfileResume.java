package com.cartoon.tinytips.Personal.Profile.RevampResume;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Profile.PersonalProfile;
import com.cartoon.tinytips.R;

/**
 * Created by cartoon on 2018/2/22.
 * 1.个人信息简介修改页
 * 2.layout：personal_profile_resume
 * 3.新建方法前先到IPersonalProfileResume.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalProfileResumePresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用PersonalProfileResumePresenter的成员方法进行业务逻辑的处理
 *
 * 功能：
 * 1.修改用户的简介
 *
 * 仍需进行的操作：
 * 1.获取用户的输入信息，在函数onClick的case R.id.tooBarTool1中编写储存逻辑
 */

public class PersonalProfileResume extends BaseActivity<PersonalProfileResumePresenter>
        implements IPersonalProfileResume.View, View.OnClickListener{

    private TextView back;
    private TextView tag;
    private TextView save;

    private EditText newResume;

    private Intent intent;

    @Override
    protected PersonalProfileResumePresenter initPresent(){
        return new PersonalProfileResumePresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_profile_resume;
    }
    @Override
    protected void initView(){
        back=findViewById(R.id.toolBarBack);
        tag=findViewById(R.id.toolBarTag);
        save=findViewById(R.id.tooBarTool1);
        newResume=findViewById(R.id.personalProfileRevampResume);
    }
    @Override
    protected void onPrepare(){
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolBarBack:{
                //点击标题栏的返回按钮
                handleClickBack();
                break;
            }
            case R.id.tooBarTool1:{
                //点击标题栏的保存按钮
                handleClickSave();
                break;
            }
        }
    }
    @Override
    public void handleClickBack(){
        intent=new Intent(this, PersonalProfile.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickSave(){
        presenter.revampResume();
    }
    @Override
    public String getNickName(){
        return getIntent().getStringExtra("nickName");
    }
    @Override
    public String getResume(){
        return newResume.getText().toString();
    }
    @Override
    public void showToast(String code){
        Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed(){
        handleClickBack();
    }
}
