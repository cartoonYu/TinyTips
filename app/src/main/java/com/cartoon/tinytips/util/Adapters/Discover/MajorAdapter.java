package com.cartoon.tinytips.util.Adapters.Discover;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Discover.NoteList.NoteList;
import com.cartoon.tinytips.Message.FavoriteMessage.FavoriteMessage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.Iterator;
import java.util.List;

import butterknife.OnClick;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class MajorAdapter extends RecyclerView.Adapter<MajorAdapter.ViewHolder> implements View.OnClickListener{
    private Context mContext;

    private List<com.cartoon.tinytips.util.Adapters.Discover.Major> mMJList;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.class_image1:{
                IntentActivity.intentWithoutData(getContext(),NoteList.class);
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView majorImage1;

        public ViewHolder(View view) {
            super(view);
            cardView= (CardView) view;
            majorImage1 = (ImageView) view.findViewById(R.id.class_image1);
        }
    }

    public MajorAdapter(List<com.cartoon.tinytips.util.Adapters.Discover.Major> MJList) {
        this.mMJList = MJList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.major_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        com.cartoon.tinytips.util.Adapters.Discover.Major major = mMJList.get(position);
        Glide.with(mContext).load(major.getImage1Id()).into(holder.majorImage1);
        holder.majorImage1.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mMJList.size();
    }

}
