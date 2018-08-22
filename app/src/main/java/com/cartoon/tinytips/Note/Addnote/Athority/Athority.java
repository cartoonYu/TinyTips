package com.cartoon.tinytips.Note.Addnote.Athority;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Note.Addnote.AddNote;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class Athority extends BaseActivity<AthorityPresenter> implements IAthority.View {

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.select_public)
    ImageView select_public;

    @BindView(R.id.select_private)
    ImageView select_private;

    private int athority;

    @Override
    protected AthorityPresenter initPresent(){
        return new AthorityPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.selectauthority;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        revampToolbar();
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("选择可见范围"));
    }

    @Override
    protected void onPrepare(){
        athority = IntentActivity.getIntentData(this,"add_athority",athority);
        switch (athority){
            case 1 : {
                select_public.setImageResource(R.drawable.select);
                select_private.setImageResource(R.mipmap.white);
                break;
            }

            case 2 : {
                select_public.setImageResource(R.mipmap.white);
                select_private.setImageResource(R.drawable.select);
                break;
            }
        }
    }

    private void revampStatusBar(){
        RevampToolbar.setBack(back);
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithoutData(this,AddNote.class);
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithoutData(this,AddNote.class);
    }

    @OnClick(R.id.authority_public)
    public void OnClickPublic(){
        select_public.setImageResource(R.drawable.select);
        select_private.setImageResource(R.mipmap.white);
        IntentActivity.intentWithData(this,AddNote.class,"athority",1);
        IntentActivity.finishActivity(this);
    }


    @OnClick(R.id.authority_private)
    public void OnClickPrivate(){
        select_public.setImageResource(R.mipmap.white);
        select_private.setImageResource(R.drawable.select);
        IntentActivity.intentWithData(this,AddNote.class,"athority",2);
        IntentActivity.finishActivity(this);
    }
}
