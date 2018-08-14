package com.cartoon.tinytips.HomePage.Hot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;

public class Hot extends Fragment {
    public Hot() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_hot, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.hottxt_content);
        txt_content.setText("热门");
        return view;
    }
}
