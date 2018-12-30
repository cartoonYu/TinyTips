package com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.PersonalHomepage.IPersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicStateAdapter extends RecyclerView.Adapter<DynamicStateAdapter.ViewHolder>{

    private Context mContext;

    private IPersonalHomepage.View view;

    private List<StatSocial> list;

    private IPersonalHomepage.Model model;

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

    public DynamicStateAdapter(IPersonalHomepage.View view,List<StatSocial> list) {           //传入日记列表
        this.view=view;
        this.list = list;

    }

    @Override
    public DynamicStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        final ViewHolder holder=
                new ViewHolder
                        (LayoutInflater.from(mContext).inflate(R.layout.personal_homepage_dynamicstate_item, parent, false));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StatSocial social=list.get(holder.getAdapterPosition());
                IntentActivity.intentWithData(mContext,NoteDetail.class,"social",social);
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论点击事件
                final StatSocial social=list.get(holder.getAdapterPosition());
                IntentActivity.intentWithData(mContext,Comment.class,"social",social);
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞
                final StatSocial social=list.get(holder.getAdapterPosition());
                model.clickLike(social, new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ShowToast.shortToast(s);
                        view.initDynamicState();
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
        holder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏
                final StatSocial social=list.get(holder.getAdapterPosition());
                model.clickCollect(social, new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ShowToast.shortToast(s);
                        view.initDynamicState();
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(DynamicStateAdapter.ViewHolder holder, int position) {
        StatSocial social=list.get(position);
        holder.title.setText(social.getTitle());
        holder.date.setText(social.getDate());
        holder.like.setText(Integer.toString(social.getNumOfLove()));
        holder.comment.setText(Integer.toString(social.getNumOfComment()));
        holder.collect.setText(Integer.toString(social.getNumOfCollect()));
        holder.forward.setText(Integer.toString(social.getNumOfForward()));
        if(social.isLove()){
            holder.like.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.drawable.favorite_press),null,null,null);
        }
        if(social.isCollect()){
            holder.collect.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.drawable.mycollection_press),null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
