package com.cartoon.tinytips.NewNote;

import android.content.Intent;
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

public class AddNote extends BaseActivity<AddNotePresenter> implements IAddNote.View {

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    private int flag;    //用于判断跳转到主页显示的fragment

    @Override
    protected AddNotePresenter initPresent(){
        return new AddNotePresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.addnote;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        revampToolbar();
    }

    @Override
    public void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("新建笔记"));
    }

    @Override
    protected void onPrepare(){
        flag= IntentActivity.getIntentData(this,"addNote",FragmentConstant.homePage);
    }

    @Override
    public void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,"main",flag);
    }

}
