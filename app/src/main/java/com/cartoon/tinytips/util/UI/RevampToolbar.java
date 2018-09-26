package com.cartoon.tinytips.util.UI;

import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.TinyTipsApplication;

public class RevampToolbar {

    /**
     * 设置toolbar上的返回按钮
     * @param back
     */
    public static void setBack(TextView back){
        //设置返回图标
        back.setBackground(TinyTipsApplication.getContext().getResources().getDrawable(R.mipmap.back_black));
    }

    /**
     * 设置toolbar显示的文字
     * @param text
     * @param title
     */
    public static void setText(TextView text,String title){
        //设置toolbar正中的标题
        text.setText(title);
    }


}
