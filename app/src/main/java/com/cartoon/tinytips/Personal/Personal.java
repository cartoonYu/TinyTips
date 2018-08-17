package com.cartoon.tinytips.Personal;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Personal.Collect.Collect;
import com.cartoon.tinytips.Personal.Detail.Detail;
import com.cartoon.tinytips.Personal.Homepage.Homepage;
import com.cartoon.tinytips.Personal.MyNote.MyNote;
import com.cartoon.tinytips.Personal.Setting.Setting;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Personal extends BaseFragment<PersonalPresenter> implements IPersonal.View {

    @BindView(R.id.detail_Personal)
    LinearLayout detail;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.avatar)
    CircleImageView headPro;

    @BindView(R.id.name)
    TextView nickName;


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

    }

    @Override
    public void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.skyBlue);
    }

    @OnClick(R.id.avatar)
    public void onClickHeadPro(){
        IntentActivity.intentWithoutData(getContext(),Homepage.class);
    }

    @OnClick(R.id.personal_notes)
    public void onClickNote(){
        IntentActivity.intentWithoutData(getContext(),MyNote.class);
    }

    @OnClick(R.id.detail_Personal)
    public void onClickDetail(){
        IntentActivity.intentWithoutData(getContext(),Detail.class);
    }

    @OnClick(R.id.collect_Personal)
    public void onClickCollect(){
        IntentActivity.intentWithoutData(getContext(),Collect.class);
    }

    @OnClick(R.id.mine_Personal)
    public void onClickHomepage(){
        IntentActivity.intentWithoutData(getContext(),Homepage.class);
    }

    @OnClick(R.id.setting_Personal)
    public void onClickSetting(){
        IntentActivity.intentWithoutData(getContext(),Setting.class);
    }

}
