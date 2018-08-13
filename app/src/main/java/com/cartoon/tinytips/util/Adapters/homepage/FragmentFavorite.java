package com.cartoon.tinytips.util.Adapters.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;

public class FragmentFavorite extends Fragment {
    public FragmentFavorite() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_favorite, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.favoritetxt_content);
        txt_content.setText("关注");
        return view;
    }
}
