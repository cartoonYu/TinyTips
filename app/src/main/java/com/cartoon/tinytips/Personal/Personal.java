package com.cartoon.tinytips.Personal;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Personal.Collect.Collect;
import com.cartoon.tinytips.Personal.Detail.Detail;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
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
        presenter.initData();
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.skyBlue);
    }

    @OnClick(R.id.avatar)
    public void onClickHeadPro(){
        IntentActivity.intentWithoutData(getContext(),PersonalHomepage.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.personal_notes)
    public void onClickNote(){
        IntentActivity.intentWithoutData(getContext(),MyNote.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.detail_Personal)
    public void onClickDetail(){
        IntentActivity.intentWithoutData(getContext(),Detail.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.collect_Personal)
    public void onClickCollect(){
        IntentActivity.intentWithoutData(getContext(),Collect.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.mine_Personal)
    public void onClickHomepage(){
        IntentActivity.intentWithoutData(getContext(),PersonalHomepage.class);
        IntentActivity.finishActivity(getActivity());
    }

    @OnClick(R.id.setting_Personal)
    public void onClickSetting(){
        IntentActivity.intentWithoutData(getContext(),Setting.class);
        IntentActivity.finishActivity(getActivity());
    }

    @Override
    public void setHeadPro(Bitmap headPro){
        this.headPro.setImageBitmap(headPro);
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
}
