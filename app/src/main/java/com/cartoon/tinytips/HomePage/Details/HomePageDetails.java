package com.cartoon.tinytips.HomePage.Details;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Details.Revamp.HomePageDetailsRevamp;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;
import com.cartoon.tinytips.util.JudgeObjectIsEmpty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/11.
 * 1.笔记详情
 * 2.layout：homepage_details
 * 3.新建方法前先到IHomePageDetails.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在HomePageDetailsPresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用HomePageDetailsPresenter的成员方法进行业务逻辑的处理
 *
 *功能
 * 1.显示用户所做笔记的情况
 *
 * 仍需进行的操作：
 * 1.标题栏菜单处理需到函数onOptionsItemSelected编写
 * 2.删除笔记功能在函数onOptionsItemSelected编写
 */

public class HomePageDetails extends BaseActivity<HomePageDetailsPresenter>
        implements IHomePageDetails.View{

    @BindView(R.id.homePageDetailsToolbar) Toolbar toolbar;
    @BindView(R.id.toolBarTag) TextView tag;       //标题栏返回按钮右侧的文字
    @BindView(R.id.homePageDetailsDetails) TextView details;   //笔记详情，图片文字混合显示

    private PopupWindow popupWindow;
    // 声明PopupWindow对应的视图
    private View popupView;

    // 声明平移动画
    private TranslateAnimation animation;

    private Intent intent;      //用于接收上一页面传过来的数据和跳转到下一页面
    private Note note;           //用于存储上一页面传过来的数据


    @Override
    protected HomePageDetailsPresenter initPresent(){
        return new HomePageDetailsPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.homepage_details;
    }
    @Override
    protected void initView(){
        setSupportActionBar(toolbar);
        if(toolbar!=null){
            toolbar.setTitle("");
        }
        note=(Note)getIntent().getSerializableExtra("dataFromMain");
        if(JudgeObjectIsEmpty.isNotEmpty(note)){
            tag.setText(note.getTitle());
            details.setText(note.getDetails());
        }
    }
    @Override
    protected void onPrepare(){
        setSupportActionBar(toolbar);
    }
    @OnClick(R.id.toolBarBack)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolBarBack:{
                //点击标题栏的返回按钮
                handleClickBack();
                break;
            }
        }
    }
    @Override
    public void handleClickBack(){
        intent=new Intent(this,Main.class);
        intent.putExtra("flag",0);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickDelete(){
        if(popupWindow==null){
            popupView=View.inflate(this,R.layout.homepage_details_delete,null);
            popupWindow=new PopupWindow(popupView,
                    WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    lightOn();
                }
            });
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            animation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,
                    Animation.RELATIVE_TO_PARENT,1);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(200);
            popupView.findViewById(R.id.homePageDetailsDeleteConfirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.deleteNote(note);
                    popupWindow.dismiss();
                    lightOn();
                }
            });
            popupView.findViewById(R.id.homePageDetailsDeleteCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    lightOn();
                }
            });
        }
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
            lightOn();
        }
        popupWindow.showAtLocation(HomePageDetails.this.findViewById(R.id.homePageDetails),
                Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        popupView.startAnimation(animation);
        lightOff();
    }
    @Override
    public void handleClickRevamp(){
        intent=new Intent(this, HomePageDetailsRevamp.class);
        intent.putExtra("dataFromDetails",note);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.homePageDetailsDelete:{
                handleClickDelete();
                //点击删除
                break;
            }
            case R.id.homePageDetailsRevamp:{
                //点击修改
                handleClickRevamp();
                break;
            }
            case R.id.homePageDetailsSetTop:{
                //点击置顶
                break;
            }
        }
        return true;
    }
    @Override
    public void lightOn(){
        //调整背景亮度
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }
    @Override
    public void lightOff(){
        //调整背景亮度
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.homepage_details,menu);
        return true;
    }
    @Override
    public void onBackPressed(){
        handleClickBack();
    }
    @Override
    public void showToast(String code){
        Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
    }
}
