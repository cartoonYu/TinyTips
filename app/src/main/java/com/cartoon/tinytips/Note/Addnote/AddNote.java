package com.cartoon.tinytips.Note.Addnote;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Note.Addnote.Athority.Athority;
import com.cartoon.tinytips.Note.Addnote.NoteTips.NoteTips;
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

    @BindView(R.id.addnote_toolbarText)
    TextView toolbarText;

    @BindView(R.id.addnote_toolbarMenu)
    TextView toolbarMenu;

    @BindView(R.id.addnote_toolbarBack)
    TextView back;

    @BindView(R.id.addnote_toolbar_menubutton_bg)
    RelativeLayout menubutton;

    @BindView(R.id.changeAthority)
    Button changeAthority;

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
        setAthority();
    }

    private void revampStatusBar(){
        RevampToolbar.setBack(back);
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
        toolbarMenu.setText("完成");
        menubutton.setBackground(getDrawable(R.mipmap.menu_button));
    }

    @OnClick(R.id.addnote_toolbar_menubutton_bg)
    protected void onClickmenubutton(){
        IntentActivity.intentWithData(this,Main.class,"main",flag);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.addnote_toolbarBack)
    protected void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,"main",flag);
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithData(this,Main.class,"main",flag);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.changeAthority)
    public void onClickAthority(){
        IntentActivity.intentWithData(this,Athority.class,"add_athority",selectAthority);
        IntentActivity.finishActivity(this);
    }

    @OnClick(R.id.addnote_addtips)
    public void onClickTips(){
        IntentActivity.intentWithoutData(this,NoteTips.class);
        IntentActivity.finishActivity(this);
    }

    /**
     * 设置权限页的显示
     */
    private void setAthority(){
        selectAthority = IntentActivity.getIntentData(this,"athority",selectAthority);
        switch (selectAthority){
            case 1 : {
                changeAthority.setText("公开");
                break;
            }

            case 2 : {
                changeAthority.setText("私密");
                break;
            }
        }
    }

    private void setTips(){

    }


}
