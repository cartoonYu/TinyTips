package com.cartoon.tinytips.Personal.Profile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Personal.Profile.RevampResume.PersonalProfileResume;
import com.cartoon.tinytips.Personal.Profile.RevampSchool.PersonalProfileSchool;
import com.cartoon.tinytips.Personal.Profile.RevampSignature.PersonalProfileSignature;
import com.cartoon.tinytips.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.IOException;
import java.util.List;

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

public class PersonalProfile extends BaseActivity<PersonalProfilePresenter> implements IProfile.View, View.OnClickListener{

    private TextView back;        //标题栏上的返回按钮，点击返回我的页面
    private TextView tag;         //标题栏上返回按钮右边的textView，用于显示当前页面名字

    private RelativeLayout headPortrait;      //头像栏，点击进入选择图片页面
    private ImageView headPortraitImage;       //显示头像，图片来源于数据库

    private TextView nickName;                 //显示昵称，昵称来源于数据库

    private TextView account;                  //显示账号，账号来源数据库

    private RelativeLayout school;            //高校栏，点击进入修改高校页面
    private TextView schoolName;               //显示高校名称，数据来源于数据库

    private RelativeLayout sex;               //性别栏，点击弹窗选择
    private TextView sexSex;                   //显示用户性别，数据来源于数据库

    private RelativeLayout resume;            //个人简介栏，点击进入修改个人简介页面
    private TextView resumeResume;             //显示个人简介，数据来源于数据库

    private RelativeLayout signature;         //个人签名栏，点击进入修改个人签名页面
    private TextView signatureSignature;       //显示个人签名，，数据来源于数据库

    private AlertDialog.Builder builder;
    private LayoutInflater inflater;

    private Intent intent;

    @Override
    protected PersonalProfilePresenter initPresent(){
        return new PersonalProfilePresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_profile;
    }
    @Override
    protected void initView(){
        //为xml的页面绑定组件，并对页面进行必要的初始化操作
        back=findViewById(R.id.toolBarBack);
        tag=findViewById(R.id.toolBarTag);
        tag.setText("个人资料");
        headPortrait=findViewById(R.id.personalProfileHeadPortrait);
        school=findViewById(R.id.personalProfileSchool);
        sex=findViewById(R.id.personalProfileSex);
        resume=findViewById(R.id.personalProfileResume);
        signature=findViewById(R.id.personalProfileSignature);
        headPortraitImage=findViewById(R.id.personalProfileHeadPortraitImage);
        nickName=findViewById(R.id.personalProfileNickNameName);
        account=findViewById(R.id.personalProfileAccountAccount);
        schoolName=findViewById(R.id.personalProfileSchoolSchool);
        sexSex=findViewById(R.id.personalProfileSexSex);
        resumeResume=findViewById(R.id.personalProfileResumeResume);
        signatureSignature=findViewById(R.id.personalProfileSignatureSignature);
    }
    @Override
    protected void onPrepare(){
        back.setOnClickListener(this);
        headPortrait.setOnClickListener(this);
        school.setOnClickListener(this);
        sex.setOnClickListener(this);
        resume.setOnClickListener(this);
        signature.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolBarBack:{
                //点击返回按钮
                handleClickBack();
                break;
            }
            case R.id.personalProfileHeadPortrait:{
                //点击头像栏
                handleClickHeadPortrait();
                break;
            }
            case R.id.personalProfileSchool:{
                //点击高校栏
                handleClickSchool();
                break;
            }
            case R.id.personalProfileSex:{
                //点击性别栏
                handleClickSex();
                break;
            }
            case R.id.personalProfileResume:{
                //点击个人简介栏
                handleClickResume();
                break;
            }
            case R.id.personalProfileSignature:{
                //点击个人签名栏
                handleClickSignature();
                break;
            }
        }
    }

    @Override
    public void handleClickBack(){
        intent=new Intent(this, Main.class);
        intent.putExtra("flag",2);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickHeadPortrait(){
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
    @Override
    public void handleClickSchool(){
        intent=new Intent(this, PersonalProfileSchool.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickSex(){
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
    @Override
    public void handleClickResume(){
        intent=new Intent(this, PersonalProfileResume.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickSignature(){
        intent=new Intent(this, PersonalProfileSignature.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<Uri> mSelected;
            mSelected = Matisse.obtainResult(data);
            try{
                for(int i=0;i<mSelected.size();i++){
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mSelected.get(i));
                    headPortraitImage.setImageBitmap(bitmap);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed(){
        handleClickBack();
    }
}
