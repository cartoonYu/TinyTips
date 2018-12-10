package com.cartoon.tinytips.util.Adapters.Personal.Collect;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.Personal;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.List;
import java.util.Map;

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

        @BindView(R.id.personal_collect_nickName)
        TextView nickName;

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
        final ViewHolder holder=new ViewHolder(view);
        holder.headPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //头像点击事件
                Information information=list.get(holder.getAdapterPosition()).getInformation();
                IntentActivity.intentWithData(mContext,PersonalHomepage.class,"Information",information);
            }
        });
        holder.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //昵称点击事件
                Information information=list.get(holder.getAdapterPosition()).getInformation();
                IntentActivity.intentWithData(mContext,PersonalHomepage.class,"Information",information);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note=list.get(holder.getAdapterPosition()).getNote();
                IntentActivity.intentWithData(mContext,NoteDetail.class,"note",note);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Collect collect=list.get(position);
        Map<String,Boolean> isClick=collect.getIsClick();
        Map<String,Integer> socials=collect.getSocial();

        holder.title.setText(collect.getNote().getTitle());
        Glide.with(mContext).load(collect.getInformation().getHeadPortrait()).into(holder.headPro);
        holder.nickName.setText(collect.getNote().getAuthor());
        holder.like.setText(Integer.toString(socials.get("Like")));
        holder.comment.setText(Integer.toString(socials.get("Comment")));
        holder.collect.setText(Integer.toString(socials.get("Collect")));
        holder.forward.setText(Integer.toString(1));
        if(isClick.get("Like")){
            holder.like.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getDrawable(R.drawable.favorite_press),null,null,null);
        }
        if(isClick.get("Collect")){
            holder.collect.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getDrawable(R.drawable.mycollection_press),null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
