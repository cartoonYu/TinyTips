package com.cartoon.tinytips.util.Adapters.Likes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.Personal.Likes.Likes;
import com.cartoon.tinytips.Personal.Likes.LikesItem;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class LikesAdapter  extends RecyclerView.Adapter<LikesAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;

    private List<LikesItem> mLikesList;

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.likes_item_time)
        TextView time;

        @BindView(R.id.likes_item_title)
        TextView title;

        @BindView(R.id.likes_item_tags)
        TextView tags;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public LikesAdapter(List<LikesItem> LikesList) {
        this.mLikesList = LikesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.likes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LikesItem likesItem = mLikesList.get(position);
        holder.title.setText(likesItem.getTitle());
        holder.tags.setText(likesItem.getTags());
        holder.time.setText(likesItem.getTime());
    }

    @Override
    public int getItemCount() {
        return mLikesList.size();
    }
}
