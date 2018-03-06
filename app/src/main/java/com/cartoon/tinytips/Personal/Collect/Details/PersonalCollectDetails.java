package com.cartoon.tinytips.Personal.Collect.Details;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Personal.Collect.PersonalCollect;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.TableNote.Note;

/**
 * Created by cartoon on 2018/3/6.
 */

public class PersonalCollectDetails extends BaseActivity<PersonalCollectDetailsPresenter>
        implements IPersonalCollectDetails.View,View.OnClickListener{

    private TextView back;
    private TextView title;
    private TextView classify[];
    private TextView details;

    private Note note;

    private Intent intent;

    @Override
    protected PersonalCollectDetailsPresenter initPresent(){
        return new PersonalCollectDetailsPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.personal_collect_details;
    }
    @Override
    protected void initView(){
        back=findViewById(R.id.toolBarBack);
        title=findViewById(R.id.toolBarTag);
        classify=new TextView[3];
        classify[0]=findViewById(R.id.tooBarTool1);
        classify[1]=findViewById(R.id.tooBarTool2);
        classify[2]=findViewById(R.id.tooBarTool3);
        details=findViewById(R.id.personalCollectDetails);
        initData();
    }
    @Override
    protected void onPrepare(){
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolBarBack:{
                handleClickBack();
                break;
            }
        }
    }
    @Override
    public void handleClickBack(){
        intent=new Intent(this, PersonalCollect.class);
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
            classify[i].setText(note.getClassify()[i]);
        }
        details.setText(note.getDetails());
    }
}
