package com.cartoon.tinytips.Personal.Profile.RevampSchool;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Profile.Profile;
import com.cartoon.tinytips.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/22.
 * 1.个人信息高校修改页
 * 2.layout：personal_profile_school
 * 3.新建方法前先到IPersonalProfileSchool.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalProfileSchoolPresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用PersonalProfileSchoolPresenter的成员方法进行业务逻辑的处理
 *
 * 功能：
 * 1.修改用户的高校信息
 *
 * 仍需进行的操作：
 * 1.获取用户的输入信息，在函数onClick的case R.id.tooBarTool1中编写储存逻辑
 */

public class ProfileSchool extends BaseActivity<ProfileSchoolPresenter>
        implements IProfileSchool.View{

    @BindView(R.id.toolBarTag) TextView tag;
    @BindView(R.id.tooBarTool1) TextView save;
    @BindView(R.id.personalProfileRevampSchool) EditText newSchool;

    private Intent intent;

    @Override
    protected ProfileSchoolPresenter initPresent(){
        return new ProfileSchoolPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_profile_school;
    }
    @Override
    protected void initView(){
        tag.setText("修改你的高校");
        save.setText("保存");
    }
    @Override
    protected void onPrepare(){
    }
    @OnClick(R.id.toolBarBack)
    public void onClick(View v){
        handleClickBack();

    }

    @Override
    public void handleClickBack(){
        intent=new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tooBarTool1)
    public void handleClickSave(){
        presenter.revampSchool();
    }

    @Override
    public String getNickName(){
        return getIntent().getStringExtra("nickName");
    }
    @Override
    public String getNewSchool(){
        return newSchool.getText().toString();
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
