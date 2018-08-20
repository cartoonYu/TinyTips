package com.cartoon.tinytips.Note.Addnote;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Note.Addnote.Athority.Athority;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class AddNote extends BaseActivity<AddNotePresenter> implements IAddNote.View {

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.toolbarMenu)
    TextView toolbarMenu;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbar_menubutton_bg)
    RelativeLayout menubutton;

    private int flag;    //用于判断跳转到主页显示的fragment
    private int selectAthority;
    private String athority;

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

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("新建笔记"));
    }

    @Override
    protected void onPrepare(){
        flag= IntentActivity.getIntentData(this,"addNote",FragmentConstant.homePage);
    }

    private void revampStatusBar(){
        RevampToolbar.setBack(back);
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
        toolbarMenu.setText("完成");
        menubutton.setBackground(getDrawable(R.mipmap.menu_button));
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,"main",flag);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.changeAthority)
    public void onClickAthority(){
        IntentActivity.intentWithoutData(this,Athority.class);
        IntentActivity.finishActivity(this);
    }

}
