package com.cartoon.tinytips;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.ScrollEditText;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BaseActivityPresenter> extends AppCompatActivity {

    protected T presenter;       //presenter层的实例对象，用于view与presenter交互

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        if(Build.VERSION.SDK_INT>=21){
            Window window=getWindow();
            View view=window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            );
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        RevampStatusBar.FlymeSetStatusBarLightMode(getWindow(),true);
        RevampStatusBar.MIUISetStatusBarLightMode(getWindow(),true);
        setContentView(getLayout());
        ButterKnife.bind(this);
        presenter=initPresent();
        initView();
        onPrepare();
    }

    protected abstract T initPresent();      //为presenter添加view弱引用
    protected abstract int getLayout();       //为活动添加页面
    protected abstract void initView();      //UI组件初始化
    protected abstract void onPrepare();     //对组件事件进行处理

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.deleteView();
        presenter=null;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof ScrollEditText) && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
