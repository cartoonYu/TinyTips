package com.cartoon.tinytips.Note.RevampNote;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Note.Addnote.AddNote;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RevampNote extends BaseActivity<RevampNotePresenter> implements IRevampNote.View{

    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量

    private static final int REQUEST_PERMISSION_CODE = 1;

    private Note note;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.revampNote_toolbarBack)
    TextView back;

    @BindView(R.id.revampNote_toolbarText)
    TextView toolbarText;

    @BindView(R.id.revampNote_toolbarMenu)
    TextView toolbarMenu;

    @BindView(R.id.revampNote_toolbar_menubutton_bg)
    RelativeLayout menuButton;

    @BindView(R.id.revampNote_addTitle)
    EditText title;

    @BindView(R.id.contentbar)
    EditText content;

    @Override
    protected RevampNotePresenter initPresent() {
        presenter=new RevampNotePresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout() {
        return R.layout.revampnote;
    }

    @Override
    protected void initView() {
        revampStatusBar();
        revampToolbar();
    }

    @Override
    protected void onPrepare() {
        getNote();
        presenter.initData(note);
    }

    @OnClick(R.id.addImage)
    public void addImage(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE);
        }else{
            Matisse.from(RevampNote.this)
                    .choose(MimeType.allOf())//图片类型
                    .countable(true)//true:选中后显示数字;false:选中后显示对号
                    .maxSelectable(9)//可选的最大数
                    .capture(true)//选择照片时，是否显示拍照
                    .captureStrategy(new CaptureStrategy(true, "com.cartoon.tinytips.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .imageEngine(new GlideEngine())//图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE);//
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + result);
            addPhotoToEditText(result);
        }
    }

    private void addPhotoToEditText(List<Uri> photos){
        if(JudgeEmpty.isEmpty(photos)){
            return;
        }
        if(photos.isEmpty()){
            return;
        }
        List<SpannableString> result=new ArrayList<>();
        for(Uri photo:photos){
            SpannableString tempString=new SpannableString("&"+photo.toString()+"&");
            ImageSpan imageSpan=new ImageSpan(this,photo);
            tempString.setSpan(imageSpan,0,photo.toString().length()+2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            result.add(tempString);
        }
        int flag=content.getSelectionStart();
        Editable editable=content.getEditableText();
        if(flag<0||flag>=editable.length()){
            for(SpannableString string:result){
                editable.append("\n");
                editable.append(string);
            }
            content.setText(editable);
        }
        else {
            for (SpannableString string:result){
                editable.insert(flag,string);
            }
            content.setText(editable);
        }
    }

    @OnClick(R.id.revampNote_toolbar_menubutton_bg)
    public void onClickFinish(){
        //点击完成按钮
        presenter.revampNote(note);
    }


    @OnClick(R.id.revampNote_toolbarBack)
    public void onClickBack(){
        //点击返回按钮
        finishActivity();
    }

    @Override
    public String getNoteTitle() {
        return title.getText().toString();
    }

    @Override
    public String getContent() {
        return content.getText().toString();
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setContent(List<SpannableString> list) {
        for(SpannableString s:list){
            this.content.append(s);
        }
    }

    private void revampStatusBar(){
        RevampToolbar.setBack(back);
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
        toolbarMenu.setText("完成");
        menuButton.setBackground(getDrawable(R.mipmap.menu_button));
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("修改笔记"));
    }

    private void getNote() {
        note=IntentActivity.getIntentNote(this,"note");
    }

    @Override
    public void onBackPressed(){
        finishActivity();
    }

    @Override
    public void finishActivity() {
        IntentActivity.finishActivity(this);
    }
}
