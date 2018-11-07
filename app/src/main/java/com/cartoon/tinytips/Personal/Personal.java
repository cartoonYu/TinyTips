package com.cartoon.tinytips.Personal;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Personal.Collect.Collect;
import com.cartoon.tinytips.Personal.Detail.Detail;
import com.cartoon.tinytips.Personal.MyNote.MyNote;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.Personal.Setting.Setting;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Personal extends BaseFragment<PersonalPresenter> implements IPersonal.View {
    Information information;

    @BindView(R.id.detail_Personal)
    RelativeLayout detail;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.avatar)
    CircleImageView headPro;

    @BindView(R.id.name)
    TextView nickName;

    @BindView(R.id.personal_notes)
    Button notes;

    @BindView(R.id.personal_fans)
    Button fans;

    @BindView(R.id.personal_attention)
    Button attentions;

    @Override
    protected PersonalPresenter initPresent(){
        return new PersonalPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.personal;
    }

    @Override
    protected void initView(){
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){
        if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentInformation(getContext(),"start"))){
            information=IntentActivity.getIntentInformation(getContext(),"start");
        }else if(JudgeEmpty.isNotEmpty(IntentActivity.getIntentInformation(getContext(),"personal"))){
            information=IntentActivity.getIntentInformation(getContext(),"personal");
        }
        presenter.initData();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.skyBlue);
    }

    @OnClick(R.id.avatar)
    public void onClickHeadPro(){
        IntentActivity.intentWithData(getContext(),PersonalHomepage.class,"personal",information);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.personal_notes)
    public void onClickNote(){
        IntentActivity.intentWithData(getContext(),MyNote.class,"personal",information);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.detail_Personal)
    public void onClickDetail(){
        IntentActivity.intentWithData(getContext(),Detail.class,"personal",information);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.collect_Personal)
    public void onClickCollect(){
        IntentActivity.intentWithData(getContext(),Collect.class,"personal",information);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.mine_Personal)
    public void onClickHomepage(){
        IntentActivity.intentWithData(getContext(),PersonalHomepage.class,"personal",information);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.setting_Personal)
    public void onClickSetting(){
        IntentActivity.intentWithData(getContext(),Setting.class,"personal",information);
        IntentActivity.finishActivity(getActivity());
    }

    @Override
    public void setHeadPro(File headPro){
        Glide.with(this).load(headPro).into(this.headPro);
    }

    @Override
    public void setNotes(String notes) {
        this.notes.setText(notes);
    }

    @Override
    public void setAttentions(String attentions) {
        this.attentions.setText(attentions);
    }

    @Override
    public void setFans(String fans) {
        this.fans.setText(fans);
    }

    @Override
    public void setNickName(String name){
        this.nickName.setText(name);
    }

    @Override
    public Information getInformation() {
        return information;
    }
}
