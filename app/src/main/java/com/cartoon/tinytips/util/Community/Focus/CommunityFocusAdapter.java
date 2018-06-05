package com.cartoon.tinytips.util.Community.Focus;

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

/**
 * Created by cartoon on 2018/2/8.
 * 1.社区（Community）的关注（RecyclerView）的适配器
 *
 * 功能：
 * 1.为社区（Community）的关注（RecyclerView）提供布局以及初始化的操作
 *
 * 操作
 * 1.点击事件在函数onCreateViewHolder编写具体逻辑
 */

public class CommunityFocusAdapter extends RecyclerView.Adapter<CommunityFocusAdapter.ViewHolder>{
    private Context context;
    private List<Note> noteList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView noteAuthor;             //笔记作者
        TextView noteDetails;            //笔记的标题
        TextView noteLike;              //笔记点赞数
        TextView noteComment;           //笔记评论数
        TextView more;                  //更多操作
        View view;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            noteAuthor=view.findViewById(R.id.communityFocusAuthor);
            noteDetails=view.findViewById(R.id.communityFocusDetails);
            noteLike=view.findViewById(R.id.communityFocusLike);
            noteComment=view.findViewById(R.id.communityFocusComment);
            more=view.findViewById(R.id.communityFocusMore);
            this.view=view;
        }
    }
    public CommunityFocusAdapter(List<Note> noteList){
        this.noteList=noteList;
    }
    @Override
    public CommunityFocusAdapter.ViewHolder onCreateViewHolder(ViewGroup group, int viewType){
        if(context==null){
            context=group.getContext();

        }
        View view= LayoutInflater.from(context).inflate(R.layout.community_focus_item,group,false);
        final CommunityFocusAdapter.ViewHolder holder=new CommunityFocusAdapter.ViewHolder(view);
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
