package com.cartoon.tinytips.Personal.Profile.RevampSignature;

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
 * 1.个人信息签名修改页
 * 2.layout：personal_profile_signature
 * 3.新建方法前先到IPersonalProfileSignature.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalProfileSignaturePresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用PersonalProfileSignaturePresenter的成员方法进行业务逻辑的处理
 *
 * 功能：
 * 1.修改用户的高校信息
 *
 * 仍需进行的操作：
 * 1.获取用户的输入信息，在函数onClick的case R.id.tooBarTool1中编写储存逻辑
 */

public class ProfileSignature extends BaseActivity<ProfileSignaturePresenter> implements
        IProfileSignature.View{

    @BindView(R.id.toolBarTag) TextView tag;
    @BindView(R.id.tooBarTool1) TextView save;
    @BindView(R.id.personalProfileRevampSignature) EditText newSignature;

    private Intent intent;

    @Override
    protected ProfileSignaturePresenter initPresent(){
        return new ProfileSignaturePresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_profile_signature;
    }
    @Override
    protected void initView(){
        tag.setText("修改你的签名");
        save.setText("保存");
    }
    @Override
    protected void onPrepare(){
    }

    @OnClick(R.id.toolBarBack)
    public void onClick(){
        handleClickBack();
    }

    public void handleClickBack(){
        //点击标题栏的返回按钮
        intent=new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tooBarTool1)
    public void handleClickSave(){
        //点击标题栏的保存按钮
        presenter.revampSignature();
    }

    @Override
    public String getNickName(){
        return getIntent().getStringExtra("nickName");
    }
    @Override
    public String getNewSignature() {
        return newSignature.getText().toString();
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
