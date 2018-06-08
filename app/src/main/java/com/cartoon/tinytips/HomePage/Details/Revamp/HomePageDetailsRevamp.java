package com.cartoon.tinytips.HomePage.Details.Revamp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Details.HomePageDetails;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cartoon on 2018/2/14.
 * 1.笔记修改页
 * 2.layout：homepage_revamp_note
 * 3.新建方法前先到IHomePageDetailsRevamp.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在HomePageDetailsRevampPresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用HomePageDetailsRevampPresenter的成员方法进行业务逻辑的处理
 *
 *功能
 * 1.修改用户所做的笔记
 *
 * 仍需进行的操作：
 * 1.获取EditText的内容
 * 2.保存按钮的逻辑(将EditText内容储存到数据库并跳转回笔记详情页)到函数onClick编写
 */

public class HomePageDetailsRevamp extends BaseActivity<HomePageDetailsRevampPresenter>
        implements IHomePageDetailsRevamp.View, View.OnClickListener{

    @BindView(R.id.homePageRevampNoteDrawerLayout) DrawerLayout drawerLayout;
    @BindView(R.id.toolBarBack) TextView back;
    @BindView(R.id.toolBarTag) TextView title;
    @BindView(R.id.tooBarTool1) TextView save;
    @BindView(R.id.homePageRevampNoteNote) EditText details;
    @BindView(R.id.homePageRevampNoteMenu) ImageView menu;
    @BindView(R.id.homePageRevampNoteMenuTitle) Button revampTitle;
    @BindView(R.id.homePageRevampNoteMenuClassify) Button revampClassify;
    @BindView(R.id.homePageRevampNoteMenuSelectPhoto) Button revampPhoto;


    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View v;

    private Intent intent;
    private Note oldNote;

    @Override
    protected HomePageDetailsRevampPresenter initPresent(){
        return new HomePageDetailsRevampPresenter(this);
    }
    @Override
    protected int getLayout(){
        return R.layout.homepage_revamp_note;
    }
    @Override
    protected void initView(){
        save.setText("保存");
    }
    @Override
    protected void onPrepare(){
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        menu.setOnClickListener(this);
        revampTitle.setOnClickListener(this);
        revampClassify.setOnClickListener(this);
        revampPhoto.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolBarBack:{
                //点击标题栏上的返回按钮
                handleClickBack();
                break;
            }
            case R.id.tooBarTool1:{
                //点击标题栏上的保存按钮
                handleClickSave();
                break;
            }
            case R.id.homePageRevampNoteMenu:{
                handleClickMenu();
                break;
            }
            case R.id.homePageRevampNoteMenuTitle:{
                handleClickRevampTitle();
                break;
            }
            case R.id.homePageRevampNoteMenuClassify:{
                handleClickRevampClassify();
                break;
            }
            case R.id.homePageRevampNoteMenuSelectPhoto:{
                handleClickSelectPhoto();
                break;
            }
        }
    }
    @Override
    public void handleClickBack(){
        intent=new Intent(this, HomePageDetails.class);
        intent.putExtra("data",oldNote);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickSave(){
        intent=new Intent(this, HomePageDetails.class);
        intent.putExtra("data",oldNote);
        startActivity(intent);
        finish();
    }
    @Override
    public void handleClickMenu(){
        drawerLayout.openDrawer(GravityCompat.END);
    }
    @Override
    public void handleClickRevampTitle(){
        drawerLayout.closeDrawers();
        inflater= LayoutInflater.from(this);
        v=inflater.inflate(R.layout.homepage_revamp_note_title, null);
        final EditText newTitle=v.findViewById(R.id.homePageRevampNoteMenuTitleTitle);
        builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确定按钮
                String str=newTitle.getText().toString();   //str为用户输入的标题
                title.setText(str);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
    @Override
    public void handleClickRevampClassify(){
        drawerLayout.closeDrawers();
        final String[] classifyInDialog=new String[]{"1","2","3"};
        final List<String> mSelectedItems = new ArrayList();
        inflater=LayoutInflater.from(this);
        v=inflater.inflate(R.layout.homepage_revamp_note_classify, null);
        final EditText newClassify=v.findViewById(R.id.homePageRevampNoteMenuClassifyClassify);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择或输入分类");
        builder.setView(v);
        builder.setMultiChoiceItems(classifyInDialog, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    mSelectedItems.add(classifyInDialog[which]);
                }
                else{
                    if(mSelectedItems.contains(classifyInDialog[which])){
                        mSelectedItems.remove(classifyInDialog[which]);
                    }
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSelectedItems.add(newClassify.getText().toString());
                if(mSelectedItems.size()<=3){

                }
                else{
                    showToast("只能选择或填写3个分类");
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0;i<mSelectedItems.size();i++){
                    mSelectedItems.remove(i);
                }
            }
        });
        builder.show();
    }
    @Override
    public void handleClickSelectPhoto(){
        drawerLayout.closeDrawers();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission. READ_EXTERNAL_STORAGE }, 1);
        }
        else {
            Matisse.from(this).choose(MimeType.allOf())
                    .countable(true).maxSelectable(9)
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))//图片显示表格的大小getResources()
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true,"com.cartoon.tinytips.fileprovider"))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .theme(R.style.Matisse_Dracula)
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine())
                    .forResult(1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<Uri> mSelected;
            mSelected = Matisse.obtainResult(data);
            try{
                for(int i=0;i<mSelected.size();i++){
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mSelected.get(i));
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showToast(String code){
        Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        handleClickBack();
    }
}
