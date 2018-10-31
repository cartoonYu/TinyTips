package com.cartoon.tinytips.Note.Addnote;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Note.Addnote.Athority.Athority;
import com.cartoon.tinytips.Note.Addnote.NoteTips.NoteTips;
import com.cartoon.tinytips.Personal.Detail.Detail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Tips.TipsItem;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.Image.UriAndFile;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;
import com.cartoon.tinytips.util.ShowToast;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;
import com.cartoon.tinytips.util.UI.ScrollEditText;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddNote extends BaseActivity<AddNotePresenter> implements IAddNote.View {
    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量

    private static final int REQUEST_PERMISSION_CODE = 1;

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

    @BindView(R.id.addnote_addtips)
    Button addnote_addtips;

    @BindView(R.id.contentbar)
    ScrollEditText editText;

    private Note note;


    private int flag;    //用于判断跳转到主页显示的fragment
    private int selectAthority;
    private int tips;
    private String tip;

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
        setTips();
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("新建笔记"));
    }

    @Override
    protected void onPrepare(){
        flag= IntentActivity.getIntentData(this,"addNote",FragmentConstant.getConstant().getHomePage());
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
        String result=editText.getText().toString();
        note=DivideNote.getDivideNote().transStringToNote(result);
        presenter.addNote(note);
    }

    @OnClick(R.id.addnote_toolbarBack)
    protected void onClickBack(){
        intentToMain();
    }

    @Override
    public void onBackPressed(){
        intentToMain();
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

    @OnClick(R.id.addImage)
    public void onClickAddImage(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE);
        }else{
            Matisse.from(AddNote.this)
                    .choose(MimeType.allOf())//图片类型
                    .countable(true)//true:选中后显示数字;false:选中后显示对号
                    .maxSelectable(9)//可选的最大数
                    .capture(true)//选择照片时，是否显示拍照
                    .captureStrategy(new CaptureStrategy(true, "com.cartoon.tinytips.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .imageEngine(new GlideEngine())//图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE);//
        }
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
        TipsItem[] major = {new TipsItem("选择标签"),new TipsItem("IT互联网"), new TipsItem("经济/管理"),
                new TipsItem("电子/通信"), new TipsItem("政治/法律"),
                new TipsItem("轨道/交通"), new TipsItem("艺术/设计"),
                new TipsItem("机械/自动化"), new TipsItem("汉语言/文学"),
                new TipsItem("建筑学"), new TipsItem("外语"),
                new TipsItem("物理/材料"), new TipsItem("化学/环境"),
                new TipsItem("数学"), new TipsItem("纺织/服装")};
         tips = IntentActivity.getIntentData(this,"NoteTips",tips);
         tip = major[tips].getTipsname();
         Toast.makeText(this, tips+"asd", Toast.LENGTH_SHORT).show();
         addnote_addtips.setText(tip);
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
        int flag=editText.getSelectionStart();
        Editable editable=editText.getEditableText();
        if(flag<0||flag>=editable.length()){
            for(SpannableString string:result){
                editable.append("\n");
                editable.append(string);
            }
            editText.setText(editable);
        }
        else {
            for (SpannableString string:result){
                editable.insert(flag,string);
            }
            editText.setText(editable);
        }
    }

    @Override
    public void intentToMain(){
        IntentActivity.intentWithData(this,Main.class,"main",flag);
        IntentActivity.finishActivity(this);
    }
}
