package com.cartoon.tinytips.HomePage.AddNote;

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
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/17.
 *1.新增笔记页面
 * 2.layout：homepage_add_note
 * 3.新建方法前先到IAddNote.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在AddNotePresenter中处理
 * 5.利用父类（BaseActivity）的成员变量presenter调用AddNotePresenter的成员方法进行业务逻辑的处理
 *
 *功能：
 * 1.提供界面给用户做笔记
 *
 * 仍需进行的操作：
 * 1.标题栏上的保存按钮逻辑到函数onClick编写
 * 2.点击侧边栏，弹出图片，已经在函数onActivityResult中返回了Uri的List--mSelected，
 *   并已经在try catch模块中的try中for循环将它转换成Bitmap，显示以及存储的具体逻辑到try里面编写
 * 3.点击侧边栏,弹出分类，用户选择或填写的分类已经存储在函数onClick的局部变量mSelectedItems，具体逻辑到
 *   builder.setPositiveButton的点击函数的if(mSelectedItems.size()<=3)编写
 */

public class AddNote extends BaseActivity<AddNotePresenter>
        implements IAddNote.View, View.OnClickListener{

    @BindView(R.id.toolBarTag) TextView title;
    @BindViews({R.id.tooBarTool4,R.id.tooBarTool3,R.id.tooBarTool2}) List<TextView> classify;
    @BindView(R.id.tooBarTool1) TextView save;

    @BindView(R.id.homePageAddNoteNote) EditText details;

    @BindView(R.id.homePageAddNoteDrawerLayout) DrawerLayout drawerLayout;

    private Intent intent;

    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View v;

    private String imageDetails;
    private String[] classifyInDialog;      //供用户选择的分类集合，数据来源为数据库

    private String[] classifyString;

    @Override
    protected AddNotePresenter initPresent(){
        return new AddNotePresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.homepage_add_note;
    }

    @Override
    protected void initView(){
        save.setText("保存");
    }

    @Override
    public void onPrepare(){

    }

    @OnClick({R.id.toolBarBack,R.id.tooBarTool2,R.id.tooBarTool3,
            R.id.tooBarTool4})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toolBarBack:{
                //点击标题栏上的返回按钮
                handleClickBack();
                break;
            }
            case R.id.tooBarTool2:{
                //点击标题3
                handleClickClassify(2);
                break;
            }
            case R.id.tooBarTool3:{
                //点击标题2
                handleClickClassify(1);
                break;
            }
            case R.id.tooBarTool4:{
                //点击标题1
                handleClickClassify(0);
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
    public void handleClickClassify(final int i){
        classifyInDialog=new String[]{"1","2","3"};
        inflater=LayoutInflater.from(this);
        v=inflater.inflate(R.layout.homepage_add_note_classify, null);
        final EditText newClassify=v.findViewById(R.id.homePageAddNoteMenuAddClassifyClassify);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择或输入分类");
        builder.setView(v);
        builder.setItems(classifyInDialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classify.get(i).setText(classifyInDialog[which]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classify.get(i).setText(newClassify.getText().toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    @OnClick(R.id.tooBarTool1)
    public void handleClickSave(){
        //点击标题栏上的保存按钮
        presenter.addNote();
    }

    @OnClick(R.id.homePageAddNoteMenu)
    public void handleClickMenu(){
        //点击屏幕右侧的箭头，弹出菜单
        drawerLayout.openDrawer(GravityCompat.END);
    }

    @OnClick({R.id.toolBarTag,R.id.homePageAddNoteMenuAddTitle})
    public void handleClickAddTitle(){
        //点击标题栏的标题
        drawerLayout.closeDrawers();
        inflater=LayoutInflater.from(this);
        v=inflater.inflate(R.layout.homepage_add_note_title, null);
        final EditText newTitle=v.findViewById(R.id.homePageAddNoteMenuAddTitleTitle);
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

    @OnClick(R.id.homePageAddNoteMenuAddClassify)
    public void handleClickAddClassify(){
        //点击左滑菜单的添加分类
        classifyString=new String[3];
        drawerLayout.closeDrawers();
        classifyInDialog=new String[]{"1","2","3"};
        final List<String> mSelectedItems = new ArrayList();
        inflater=LayoutInflater.from(this);
        v=inflater.inflate(R.layout.homepage_add_note_classify, null);
        final EditText newClassify=v.findViewById(R.id.homePageAddNoteMenuAddClassifyClassify);
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
                if(!newClassify.getText().toString().isEmpty()){
                    mSelectedItems.add(newClassify.getText().toString());
                }
                if(mSelectedItems.size()<=3){
                    classifyString=mSelectedItems.toArray(new String[3]);
                    //getClassify(mSelectedItems);
                    for(int i=0;i<3;i++){
                        classify.get(i).setText("");
                    }
                    for(int i=0;i<mSelectedItems.size();i++){
                        classify.get(i).setText(mSelectedItems.get(i));
                    }
                }
                else{
                    showToast("只能选择或填写3个分类");
                }
                for(int i=0;i<mSelectedItems.size();i++){
                    mSelectedItems.remove(i);
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

    @OnClick(R.id.homePageAddNoteMenuSelectPhoto)
    public void handleClickSelectPhoto(){
        //点击左滑菜单的从相册中选择图片
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
                    addBitmapToText(bitmap);
                    if(i==0){

                        imageDetails=bitmap.toString();
                    }
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
    public String getDate() {
        return  new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(new Date());
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public String getDetails() {
        Log.d("hello", "getDetails: "+details.getText().toString());
        return details.getText().toString();
    }

    @Override
    public String[] getClassify(List<String> classify) {
        return classify.toArray(new String[classify.size()]);
    }

    @Override
    public Boolean getIsCollect() {
        return null;
    }

    @Override
    public String getNoteTitle() {
        return title.getText().toString();
    }

    @Override
    public String getImageDetails() {
        return imageDetails;
    }

    //把图片转化为SpannableString并加载到edittext中
    public void addBitmapToText(Bitmap bitmap){
        if(bitmap!=null){
            ImageSpan imageSpan=new ImageSpan(this,bitmap);
            SpannableString spannableString=new SpannableString("[me]"+"[/me]");
            spannableString.setSpan(imageSpan,0,"[me][me]".length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            int index=details.getSelectionStart();
            Editable edit_text=details.getEditableText();
            if(index<0||index>=edit_text.length()){
                edit_text.append("\n");
                edit_text.append(spannableString);
                edit_text.append("\n");
                details.setText(edit_text);
            }else{
                edit_text.insert(index,spannableString);
                details.setText(edit_text);
            }
        }else {
            showToast("加载图片失败");
        }
    }

    @Override
    public void onBackPressed(){
        handleClickBack();
    }
}
