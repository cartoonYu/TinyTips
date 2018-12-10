package com.cartoon.tinytips.util.Adapters.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mContext;

    private List<CommentItem> mCommentItems;

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_avatar)
        ImageView userImages;

        @BindView(R.id.comment_name)
        TextView userNames;

        @BindView(R.id.comment_content)
        TextView contents;

        @BindView(R.id.comment_time)
        TextView time;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public CommentAdapter(List<CommentItem> mCommentItem) {
        this.mCommentItems = mCommentItem;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.commentitem, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        CommentItem commentItem = mCommentItems.get(position);
        holder.userNames.setText(commentItem.getUsername());
        holder.contents.setText(commentItem.getContent());
        holder.time.setText(commentItem.getTime());
        Glide.with(mContext).load(commentItem.getUserImage()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        Log.d("asd",mCommentItems.size()+"");
        return mCommentItems.size();
    }
}
