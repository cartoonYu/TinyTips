package com.cartoon.tinytips.NewNote;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.FragmentConstant;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;

public class AddNote extends BaseActivity<AddNotePresenter> implements IAddNote.View {

    @BindView(R.id.statusBar)
    View statusBar;

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
        statusBar.setBackgroundColor(getResources().getColor(R.color.skyBlue));

    }

    @Override
    public void onBackPressed(){
        intentToMain();
    }

    @Override
    public void intentToMain(){
        Intent intent=new Intent(this, Main.class);
        intent.putExtra("main", FragmentConstant.personal);
        startActivity(intent);
        finish();
    }
}
