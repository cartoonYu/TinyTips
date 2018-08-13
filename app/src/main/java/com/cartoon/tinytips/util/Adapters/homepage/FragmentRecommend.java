package com.cartoon.tinytips.util.Adapters.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;

public class FragmentRecommend extends Fragment {
    public FragmentRecommend() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_recommend, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.recommendtxt_content);
        txt_content.setText("推荐");
        return view;
    }
}
