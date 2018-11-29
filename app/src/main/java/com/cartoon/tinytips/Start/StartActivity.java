package com.cartoon.tinytips.Start;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Login.Login;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.ShowToast;

public class StartActivity extends BaseActivity<StartActivityPresenter> implements IStartActivity.View {

    private final long SPLASH_DELAY_MILLIS = 2000;
    private static final String TAG ="QuizActivity";

    @Override
    protected StartActivityPresenter initPresent() {
        return new StartActivityPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.start;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onPrepare() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else {
            presenter.getInformation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    ShowToast.shortToast("允许权限");
                    presenter.getInformation();
                }
                else {
                    ShowToast.shortToast("未允许权限");
                    IntentActivity.finishActivity(this);
                }
                break;
            }
        }
    }

    @Override
    public void intentToMain(Information information) {
        IntentActivity.intentWithoutData(this,Main.class);
        IntentActivity.finishActivity(this);
    }

    @Override
    public void intentToLogin() {
        IntentActivity.intentWithoutData(this,Login.class);
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
}

