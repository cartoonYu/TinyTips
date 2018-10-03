package com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicStateAdapter extends RecyclerView.Adapter<DynamicStateAdapter.ViewHolder>{
    private Context mContext;

    private List<DynamicState> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        @BindView(R.id.personal_homepage_dynamicState_title)
        TextView title;    //标题

        @BindView(R.id.personal_homepage_dynamicState_date)
        TextView date;     //日期

        @BindView(R.id.personal_homepage_dynamicState_like)
        TextView like;     //点赞个数

        @BindView(R.id.personal_homepage_dynamicState_comment)
        TextView comment;   //评论个数

        @BindView(R.id.personal_homepage_dynamicState_collect)
        TextView collect;    //收藏个数

        @BindView(R.id.personal_homepage_dynamicState_forward)
        TextView forward;     //转发次数

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            cardView = (CardView) view;
        }
    }

    public DynamicStateAdapter(List<DynamicState> list) {
        this.list = list;
    }

    @Override
    public DynamicStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.personal_homepage_dynamicstate_item, parent, false);
        return new DynamicStateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DynamicStateAdapter.ViewHolder holder, int position) {
        DynamicState state=list.get(position);
        holder.title.setText(state.getTitle());
        holder.date.setText(state.getDate());
        holder.like.setText(new StringBuilder("").append(state.getLike()).toString());
        holder.comment.setText(new StringBuilder("").append(state.getComment()).toString());
        holder.collect.setText(new StringBuilder("").append(state.getCollect()).toString());
        holder.forward.setText(new StringBuilder("").append(state.getForward()).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
