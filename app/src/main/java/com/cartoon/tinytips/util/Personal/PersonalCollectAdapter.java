package com.cartoon.tinytips.util.Personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Personal.Collect.Details.PersonalCollectDetails;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.data.Note;

import java.util.List;

/**
 * Created by cartoon on 2018/3/5.
 *  1.PersonalCollect的笔记（RecyclerView）的适配器
 *
 * 功能：
 * 1.为PersonalCollect的笔记（RecyclerView）提供布局以及初始化的操作
 *
 * 操作
 * 1.点击事件在函数onCreateViewHolder编写具体逻辑
 */

public class PersonalCollectAdapter extends RecyclerView.Adapter<PersonalCollectAdapter.ViewHolder>{
    private Context context;
    private List<Note> noteList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView noteTitle;            //笔记的标题
        ImageView noteImage;       //笔记的缩略图(随机从笔记的图片选一张）
        TextView noteWord;         //笔记的简单描述（截取笔记的开头的10字）
        TextView noteDate;         //笔记建立的日期
        View view;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            noteTitle=view.findViewById(R.id.personalCollectNoteTitle);
            noteImage=view.findViewById(R.id.personalCollectNoteImage);
            noteWord=view.findViewById(R.id.personalCollectNoteWord);
            noteDate=view.findViewById(R.id.personalCollectNoteDate);
            this.view=view;
        }
    }
    public PersonalCollectAdapter(List<Note> noteList){
        this.noteList=noteList;
    }
    @Override
    public PersonalCollectAdapter.ViewHolder onCreateViewHolder(final ViewGroup group, int viewType){
        if(context==null){
            context=group.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.personal_collect_item,group,false);
        final PersonalCollectAdapter.ViewHolder holder=new PersonalCollectAdapter.ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PersonalCollectDetails.class);
                intent.putExtra("dataFromCollect",noteList.get(holder.getAdapterPosition()));
                Activity activity=(Activity)group.getContext();
                activity.startActivity(intent);
                activity.finish();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Note note=noteList.get(position);
        Glide.with(context).load(note.getImageDetails()).into(holder.noteImage);
        holder.noteTitle.setText(note.getTitle());
        if(note.getDetails().length()<10){
            holder.noteWord.setText(note.getDetails());
        }
        else{
            holder.noteWord.setText(note.getDetails().substring(0,9)+"....");
        }
        holder.noteDate.setText(note.getDate().toString());
    }
    @Override
    public int getItemCount(){
        return noteList.size();
    }
}
