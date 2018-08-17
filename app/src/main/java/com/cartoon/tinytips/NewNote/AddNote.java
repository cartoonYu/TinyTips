package com.cartoon.tinytips.NewNote;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.FragmentConstant;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

import static com.cartoon.tinytips.Main.Main.getStateflag;

public class AddNote extends BaseActivity<AddNotePresenter> implements IAddNote.View {



    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

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
        initToolbar();
    }

    @Override
    protected void onPrepare(){

    }

    @Override
    public void revampStatusBar(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(this);
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onBackPressed(){
        intentToMain();
    }

    @Override
    public void intentToMain(){
        Intent intent=new Intent(this, Main.class);

        switch (getStateflag()){
            case 0 :{
                intent.putExtra("main", FragmentConstant.homePage);
                startActivity(intent);
                finish();
                break;
            }

            case 1 :{
                intent.putExtra("main", FragmentConstant.message);
                startActivity(intent);
                finish();
                break;
            }

            case 3 :{
                intent.putExtra("main", FragmentConstant.discover);
                startActivity(intent);
                finish();
                break;
            }

            case 4 :{
                intent.putExtra("main", FragmentConstant.personal);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

    @Override
    public void initToolbar(){
        toolbarText.setText("设置");
    }
}
