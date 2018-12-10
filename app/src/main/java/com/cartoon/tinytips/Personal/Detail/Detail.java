package com.cartoon.tinytips.Personal.Detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class Detail extends BaseActivity<DetailPresenter> implements IDetail.View{

    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量

    private static final int REQUEST_PERMISSION_CODE = 1;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.nickName)
    TextView nickName;

    @BindView(R.id.Produce)
    TextView Resume;

    @BindView(R.id.School)
    TextView School;

    @BindView(R.id.Major)
    TextView Major;

    @BindView(R.id.Degree)
    TextView Degree;

    @BindView(R.id.personal_detail_registerData)
    TextView registerData;

    @BindViews({R.id.personal_detail_interest1,R.id.personal_detail_interest2,R.id.personal_detail_interest3,R.id.personal_detail_interest4})
    List<Button> interests;

    @BindView(R.id.avarar)
    de.hdodenhof.circleimageview.CircleImageView avarar;

    @Override
    protected DetailPresenter initPresent(){
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal_detail;
    }

    @Override
    protected void initView() {
        revampStatusBar();
        initToolbar();
    }

    @Override
    protected void onPrepare(){
        presenter.initData();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("个人信息"));
    }

    @OnClick(R.id.avatar_detailPersonal)
    public void onClickAvatar_detailPersonal(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE);
        }else{
            Matisse.from(Detail.this)
                    .choose(MimeType.allOf())//图片类型
                    .countable(false)//true:选中后显示数字;false:选中后显示对号
                    .maxSelectable(1)//可选的最大数
                    .capture(true)//选择照片时，是否显示拍照
                    .captureStrategy(new CaptureStrategy(true, "com.cartoon.tinytips.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .imageEngine(new GlideEngine())//图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE);//
        }
    }

    @Override
    public void setHeadPro(File headPro) {
        Glide.with(this).load(headPro).into(avarar);
    }

    @Override
    public void setNickName(String name) {
        this.nickName.setText(name);
    }

    @Override
    public void setResume(String resume) {
        this.Resume.setText(resume);
    }

    @Override
    public void setSchool(String school) {
        this.School.setText(school);
    }

    @Override
    public void setMajor(String major) {
        this.Major.setText(major);
    }

    @Override
    public void setDegree(String degree) {
        this.Degree.setText(degree);
    }

    @Override
    public void setRegisterData(String data) {
        this.registerData.setText(data);
    }

    @Override
    public void setInterest(List<String> interests) {
        for(int i=0,length=interests.size();i<length;i++){
            this.interests.get(i).setBackgroundColor(getResources().getColor(R.color.white));
            this.interests.get(i).setText(interests.get(i));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
            presenter.revampHeadPro(result.get(0));
        }
    }

    @OnClick(R.id.name_detailPersonal)
    public void revampResume(){
        //简介点击事件

    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }
}
