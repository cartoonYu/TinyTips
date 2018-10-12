package com.cartoon.tinytips.Personal.Detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class Detail extends BaseActivity<DetailPresenter> implements IDetail.View{

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

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("个人信息"));
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"),FragmentConstant.getConstant().getPersonal());
        IntentActivity.finishActivity(this);
    }

    @Override
    public void setHeadPro(Bitmap headPro) {
        this.avarar.setImageBitmap(headPro);
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
}
