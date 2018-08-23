package com.cartoon.tinytips.util.Adapters.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mContext;

    private List<CommentItem> mCommentItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImages;
        TextView userNames;
        TextView contents;
        TextView time;

        public ViewHolder(View view) {
            super(view);
            userImages = (ImageView) view.findViewById(R.id.comment_avatar);
            userNames = (TextView) view.findViewById(R.id.comment_name);
            contents= (TextView)view.findViewById(R.id.comment_content);
            time = (TextView)view.findViewById(R.id.comment_time);
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
        return new CommentAdapter.ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        CommentItem CommentItem = mCommentItems.get(position);
        holder.userNames.setText(""+CommentItem.getuserName());
        holder.contents.setText(""+CommentItem.getcontent());
        holder.time.setText(""+CommentItem.getTime());
        Glide.with(mContext).load(CommentItem.getuserImage()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mCommentItems.size();
    }
}
