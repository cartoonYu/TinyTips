package com.cartoon.tinytips.Personal.Profile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Personal.Profile.RevampResume.ProfileResume;
import com.cartoon.tinytips.Personal.Profile.RevampSchool.ProfileSchool;
import com.cartoon.tinytips.Personal.Profile.RevampSignature.ProfileSignature;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.PersonalInformation;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/6.
 * 1.个人信息页
 * 2.layout：personal_profile
 * 3.新建方法前先到IProfile.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalPresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用PersonalProfilePresenter的成员方法进行业务逻辑的处理
 *
 * 功能：
 * 1.显示用户个人信息
 * 2.为用户修改个人信息提供入口（账号以及昵称不可修改）
 *
 * 仍需进行的操作：
 * 1.用户头像，昵称，账号，高校，性别，个人简介，个人签名等具体的数据要到数据库中提取显示
 * 2.用户修改个人信息的入口要到函数onClick中编写具体逻辑
 */

public class Profile extends BaseActivity<ProfilePresenter> implements IProfile.View{

    @BindView(R.id.toolBarTag) TextView tag;         //标题栏上返回按钮右边的textView，用于显示当前页面名字

    @BindView(R.id.personalProfileNickNameName) TextView nickName;                 //显示昵称，昵称来源于数据库
    @BindView(R.id.personalProfileHeadPortraitImage) ImageView headPortraitImage;       //显示头像，图片来源于数据库

    @BindView(R.id.personalProfileAccountAccount) TextView account;                  //显示账号，账号来源数据库

    @BindView(R.id.personalProfileSchoolSchool) TextView schoolName;               //显示高校名称，数据来源于数据库

    @BindView(R.id.personalProfileSexSex) TextView sexSex;                   //显示用户性别，数据来源于数据库

    @BindView(R.id.personalProfileResumeResume) TextView resumeResume;             //显示个人简介，数据来源于数据库

    @BindView(R.id.personalProfileSignatureSignature) TextView signatureSignature;       //显示个人签名，，数据来源于数据库

    private AlertDialog.Builder builder;
    private LayoutInflater inflater;

    private Intent intent;

    @Override
    protected ProfilePresenter initPresent(){
        return new ProfilePresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_profile;
    }
    @Override
    protected void initView(){
        //为xml的页面绑定组件，并对页面进行必要的初始化操作
        tag.setText("个人资料");
    }
    @Override
    protected void onPrepare(){
        presenter.initData(getIntent().getStringExtra("nickName"));
    }

    @OnClick(R.id.toolBarBack)
    public void handleClickBack(){
        //点击返回按钮
        intent=new Intent(this, Main.class);
        intent.putExtra("flag",2);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.personalProfileHeadPortrait)
    public void handleClickHeadPortrait(){
        //点击头像栏
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission. READ_EXTERNAL_STORAGE }, 1);
        }
        else {
            Matisse.from(this).choose(MimeType.allOf())
                    .maxSelectable(1)
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true,"com.cartoon.tinytips.fileprovider"))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .theme(R.style.Matisse_Dracula)
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine())
                    .forResult(1);
        }
    }

    @OnClick(R.id.personalProfileSchool)
    public void handleClickSchool(){
        //点击高校栏
        intent=new Intent(this, ProfileSchool.class);
        intent.putExtra("nickName",nickName.getText().toString());
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.personalProfileSex)
    public void handleClickSex(){
        //点击性别栏
        builder = new AlertDialog.Builder(this);
        inflater=this.getLayoutInflater();
        builder.setItems(R.array.PersonalProfileSex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str[]=getResources().getStringArray(R.array.PersonalProfileSex);
                sexSex.setText(str[which]);
            }
        });
        builder.create();
        builder.show();
    }

    @OnClick(R.id.personalProfileResume)
    public void handleClickResume(){
        //点击个人简介栏
        intent=new Intent(this, ProfileResume.class);
        intent.putExtra("nickName",nickName.getText().toString());
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.personalProfileSignature)
    public void handleClickSignature(){
        //点击个人签名栏
        intent=new Intent(this, ProfileSignature.class);
        intent.putExtra("nickName",nickName.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void initData(PersonalInformation information){
        Glide.with(this).load(information.getHeadPortrait()).into(headPortraitImage);
        nickName.setText(information.getNickName());
        account.setText(information.getAccount());
        schoolName.setText(information.getSchool());
        sexSex.setText(information.getSex());
        resumeResume.setText(information.getResume());
        signatureSignature.setText(information.getSignature());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<Uri> mSelected=Matisse.obtainResult(data);
            Glide.with(this).load(mSelected.get(0)).into(headPortraitImage);
        }
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