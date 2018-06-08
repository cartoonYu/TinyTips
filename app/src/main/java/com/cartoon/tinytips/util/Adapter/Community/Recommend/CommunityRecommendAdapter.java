package com.cartoon.tinytips.util.Adapter.Community.Recommend;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cartoon on 2018/2/8.
 * 1.社区（Community）的推荐（RecyclerView）的适配器
 *
 * 功能：
 * 1.为社区（Community）的推荐（RecyclerView）提供布局以及初始化的操作
 *
 * 操作
 * 1.点击事件在函数onCreateViewHolder编写具体逻辑
 */

public class CommunityRecommendAdapter extends RecyclerView.Adapter<CommunityRecommendAdapter.ViewHolder>{
    private Context context;
    private List<Note> noteList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        @BindView(R.id.communityRecommendAuthor) TextView noteAuthor;             //笔记作者
        @BindView(R.id.communityRecommendDetails) TextView noteDetails;            //笔记的标题
        @BindView(R.id.communityRecommendLike) TextView noteLike;              //笔记点赞数
        @BindView(R.id.communityRecommendComment) TextView noteComment;           //笔记评论数
        @BindView(R.id.communityRecommendMore) TextView more;                  //更多操作

        View view;
        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            cardView=(CardView)view;
            this.view=view;
        }
    }
    public CommunityRecommendAdapter(List<Note> noteList){
        this.noteList=noteList;
    }
    public CommunityRecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup group, int viewType){
        if(context==null){
            context=group.getContext();

        }
        View view= LayoutInflater.from(context).inflate(R.layout.community_recommend_item,group,false);
        final CommunityRecommendAdapter.ViewHolder holder=new CommunityRecommendAdapter.ViewHolder(view);
        holder.noteAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击作者
            }
        });
        holder.noteDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击内容
            }
        });
        holder.noteLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击点赞按钮
            }
        });
        holder.noteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击评论按钮
            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击更多按钮
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Note note=noteList.get(position);
        holder.noteAuthor.setText(note.getAuthor());
        if(note.getDetails().length()<10){
            holder.noteDetails.setText(note.getDetails());
        }
        else{
            holder.noteDetails.setText(note.getDetails().substring(0,9)+"....");
        }
        holder.noteLike.setText(Long.toString(note.getPointPraise()));
        holder.noteComment.setText(Long.toString(note.getComment()));
    }
    @Override
    public int getItemCount(){
        return noteList.size();
    }
}
