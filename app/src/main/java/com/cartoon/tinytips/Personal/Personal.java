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
        Intent intent = getActivity().getIntent();
        if ((Information)intent.getSerializableExtra("start")!=null) {
            information = (Information) intent.getSerializableExtra("start");
        }else if (IntentActivity.getIntentData(getActivity(),new String("personalDetail"),information)!=null){
            information = IntentActivity.getIntentData(getActivity(),new String("personalDetail"),information);
        }else if (IntentActivity.getIntentData(getActivity(),new String("personalHomepage"),information)!=null){
            information = IntentActivity.getIntentData(getActivity(),new String("personalHomepage"),information);
        }else if (IntentActivity.getIntentData(getActivity(),new String("personalCollect"),information)!=null){
            information = IntentActivity.getIntentData(getActivity(),new String("personalCollect"),information);
        }else if (IntentActivity.getIntentData(getActivity(),new String("personalSetting"),information)!=null){
            information = IntentActivity.getIntentData(getActivity(),new String("personalSetting"),information);
        }
        presenter.initData();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.skyBlue);
    }

    @OnClick(R.id.avatar)
    public void onClickHeadPro(){

        Intent intent = new Intent(getContext(),PersonalHomepage.class);                     //传递数据到MainActivity
        intent.putExtra("in","hello");
        intent.putExtra("personal",information);
        Log.d("text", "onClickHeadPro: "+information.getAccount());
        startActivity(intent);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.personal_notes)
    public void onClickNote(){
        Intent intent = new Intent(getContext(),MyNote.class);                     //传递数据到MainActivity
        intent.putExtra("in","hello");
        intent.putExtra("personal",information);
        startActivity(intent);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.detail_Personal)
    public void onClickDetail(){
        Intent intent = new Intent(getContext(),Detail.class);                     //传递数据到MainActivity
        intent.putExtra("in","hello");
        intent.putExtra("personal",information);
        startActivity(intent);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.collect_Personal)
    public void onClickCollect(){
        Intent intent = new Intent(getContext(),Collect.class);                     //传递数据到MainActivity
        intent.putExtra("in","hello");
        intent.putExtra("personal",information);
        Log.d("text", "collect "+information.getAccount());
        startActivity(intent);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.mine_Personal)
    public void onClickHomepage(){
        Intent intent = new Intent(getContext(),PersonalHomepage.class);                     //传递数据到MainActivity
        intent.putExtra("in","hello");
        intent.putExtra("personal",information);
        Log.d("text", "onClickDetail: "+information.getAccount());
        startActivity(intent);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.setting_Personal)
    public void onClickSetting(){
        Intent intent = new Intent(getContext(),Setting.class);                     //传递数据到MainActivity
        intent.putExtra("in","hello");
        intent.putExtra("personal",information);
        Log.d("text", "setting: "+information.getAccount());
        startActivity(intent);
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
