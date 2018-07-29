package com.cartoon.tinytips.Personal.Collect.Details;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Collect.Collect;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/3/6.
 */

public class CollectDetails extends BaseActivity<CollectDetailsPresenter>
        implements ICollectDetails.View{

    @BindView(R.id.toolBarTag) TextView title;
    @BindViews({R.id.tooBarTool1,R.id.tooBarTool2,R.id.tooBarTool3}) List<TextView> classify;
    @BindView(R.id.personalCollectDetails) TextView details;

    private Note note;

    private Intent intent;

    @Override
    protected CollectDetailsPresenter initPresent(){
        return new CollectDetailsPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_collect_details;
    }
    @Override
    protected void initView(){
        initData();
    }
    @Override
    protected void onPrepare(){
    }

    @OnClick(R.id.toolBarBack)
    public void handleClickBack(){
        intent=new Intent(this, Collect.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        handleClickBack();
    }

    @Override
    public void initData(){
        note=(Note)getIntent().getSerializableExtra("dataFromCollect");
        title.setText(note.getTitle());
        for(int i=0;i<note.getClassify().length;i++){
            classify.get(i).setText(note.getClassify()[i]);
        }
        details.setText(note.getDetails());
    }
}