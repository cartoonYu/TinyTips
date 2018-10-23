package com.cartoon.tinytips.Start;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.ShowToast;

public class StartActivity  extends BaseActivity<StartActivityPresenter> implements IStartActivity.View {

    private final long SPLASH_DELAY_MILLIS = 3000;

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
        new Handler().postDelayed(new Runnable() {
            public void run() {
                intentToMain();
            }
        }, SPLASH_DELAY_MILLIS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    ShowToast.shortToast("允许权限");
                }
                break;
            }
        }
    }

    @Override
    public void intentToMain() {
        IntentActivity.intentWithoutData(this,Main.class);
        IntentActivity.finishActivity(this);
    }
}
