package com.cartoon.tinytips.Start;

import android.os.Handler;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;


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
        new Handler().postDelayed(new Runnable() {
            public void run() {
                intentToMain();
            }
            }, SPLASH_DELAY_MILLIS);
    }

    @Override
    protected void onPrepare() {

    }

    @Override
    public void intentToMain() {
        IntentActivity.intentWithoutData(this,Main.class);
        IntentActivity.finishActivity(this);
    }
}
