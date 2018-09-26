package com.cartoon.tinytips.util.Adapters.Personal.Collect;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder>{

    private Context mContext;

    private List<Collect> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        @BindView(R.id.personal_collect_title)
        TextView title;    //标题

        @BindView(R.id.personal_collect_headPro)
        CircleImageView headPro;   //头像

        @BindView(R.id.personal_collect_like)
        TextView like;     //点赞个数

        @BindView(R.id.personal_collect_comment)
        TextView comment;   //评论个数

        @BindView(R.id.personal_collect_collect)
        TextView collect;    //收藏个数

        @BindView(R.id.personal_collect_forward)
        TextView forward;     //转发次数

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            cardView = (CardView) view;
        }
    }

    public CollectAdapter(List<Collect> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.personal_collect_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Collect collect=list.get(position);
        holder.title.setText(collect.getTitle());
        holder.headPro.setImageDrawable(collect.getHeadPro());
        holder.like.setText(new StringBuilder("").append(collect.getLike()).toString());
        holder.comment.setText(new StringBuilder("").append(collect.getComment()).toString());
        holder.collect.setText(new StringBuilder("").append(collect.getCollect()).toString());
        holder.forward.setText(new StringBuilder("").append(collect.getForward()).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
