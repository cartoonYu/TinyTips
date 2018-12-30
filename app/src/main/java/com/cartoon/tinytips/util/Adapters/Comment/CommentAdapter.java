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
import com.cartoon.tinytips.HomePage.Recommend.IRecommend;
import com.cartoon.tinytips.Note.Comment.IComment;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.view.CommentDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context mContext;

    private List<CommentDetails> mCommentItems;

    private IComment.View view;

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

    public CommentAdapter(IComment.View view,List<CommentDetails> mCommentItem) {
        this.view=view;
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
        CommentDetails details=mCommentItems.get(position);
        holder.userNames.setText(details.getNickName());
        holder.contents.setText(details.getDetails());
        holder.time.setText(details.getDate());
        Glide.with(mContext).load(details.getHeadPortrait()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mCommentItems.size();
    }
}
