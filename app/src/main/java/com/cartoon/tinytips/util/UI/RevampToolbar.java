package com.cartoon.tinytips.util.UI;

import android.widget.Button;
import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.TinyTipsApplication;

public class RevampToolbar {

    public static void setBack(TextView back){
        //设置返回图标
        back.setBackground(TinyTipsApplication.getContext().getResources().getDrawable(R.mipmap.back_black));
    }

    public static void setText(TextView text,String title){
        //设置toolbar正中的标题
        text.setText(title);
    }


}
