package com.cartoon.tinytips.Personal.Security;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.PersonalInformation;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/6.
 * 1.安全页
 * 2.layout：personal_security
 * 3.新建方法前先到ISecurity.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalPresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用PPersonalSecurityPresenter的成员方法进行业务逻辑的处理
 *
 * 功能
 * 1.修改密码
 *
 * 仍需进行的操作：
 * 1.获取三个输入框的值，并进行对比，将新密码存储到数据库中
 * 2.1中的操作写到保存按钮的点击事件中（函数onClick）
 */

public class Security extends BaseActivity<SecurityPresenter>
        implements ISecurity.View{

    @BindView(R.id.toolBarTag) TextView tag;        //标题栏上返回按钮右边的textView，用于显示当前页面名字
    @BindView(R.id.tooBarTool1) TextView save;      //标题栏上的保存按钮，点击对所输入的原密码，新密码，确认密码（与新密码进行匹配）对比，相同保存在数据库上，并返回我的页面
    @BindView(R.id.personalSecurityOldPassword) EditText oldPassword;    //输入原密码
    @BindView(R.id.personalSecurityNewPassword) EditText newPassword;    //输入新密码
    @BindView(R.id.personalSecurityConfirmPassword) EditText confirmPassword;    //用于与新密码进行匹配，防止出现错误

    @Override
    protected SecurityPresenter initPresent(){
        return new SecurityPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_security;
    }
    @Override
    protected void initView(){
        tag.setText("安全");
        save.setText("保存");
    }
    @Override
    protected void onPrepare(){
    }

    @OnClick(R.id.toolBarBack)
    public void onClick(View view){
        handleClickBack();
    }

    @Override
    public void handleClickBack(){
        Intent intent=new Intent(this,Main.class);
        intent.putExtra("flag",2);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tooBarTool1)
    public void handleClickSave(){
        presenter.revampPassword();
    }

    @Override
    public PersonalInformation getOldPassword(){
        PersonalInformation information=new PersonalInformation();
        information.setNickName(getIntent().getStringExtra("nickName"));
        information.setPassword(oldPassword.getText().toString());
        return information;
    }
    @Override
    public String getConfirmPassword(){
        return confirmPassword.getText().toString();
    }
    @Override
    public String getNewPassword(){
        return newPassword.getText().toString();
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
