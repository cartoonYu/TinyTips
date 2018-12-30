package com.cartoon.tinytips.util.Adapters.Personal.Collect;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.Collect.Collect;
import com.cartoon.tinytips.Personal.Collect.CollectModel;
import com.cartoon.tinytips.Personal.Collect.ICollect;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder>{

    private Context mContext;

    private List<StatSocial> list;

    private ICollect.View view;

    private ICollect.Model model;

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

    public CollectAdapter(ICollect.View view,List<StatSocial> list) {
        this.list = list;
        this.view=view;
        this.model=new CollectModel();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        final ViewHolder holder=
                new ViewHolder
                        (LayoutInflater.from(mContext).inflate(R.layout.personal_collect_item, parent, false));
        holder.headPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //头像点击事件
                final StatSocial social=list.get(holder.getAdapterPosition());
                model.clickUser(social, new ValueCallBack<Information>() {
                    @Override
                    public void onSuccess(Information information) {
                        IntentActivity.intentWithData(mContext,PersonalHomepage.class,"Information",information);
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
        holder.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //昵称点击事件
                final StatSocial social=list.get(holder.getAdapterPosition());
                model.clickUser(social, new ValueCallBack<Information>() {
                    @Override
                    public void onSuccess(Information information) {
                        IntentActivity.intentWithData(mContext,PersonalHomepage.class,"Information",information);
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
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
                        view.initCollect();
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
                        view.initCollect();
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        StatSocial social=list.get(position);
        holder.title.setText(social.getTitle());
        Glide.with(mContext).load(social.getHeadPortrait()).into(holder.headPro);
        holder.nickName.setText(social.getNickName());
        holder.like.setText(Integer.toString(social.getNumOfLove()));
        holder.comment.setText(Integer.toString(social.getNumOfComment()));
        holder.collect.setText(Integer.toString(social.getNumOfCollect()));
        holder.forward.setText(Integer.toString(social.getNumOfForward()));
        if(social.isLove()){
            holder.like.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getDrawable(R.drawable.favorite_press),null,null,null);
        }
        if(social.isCollect()){
            holder.collect.setCompoundDrawablesRelativeWithIntrinsicBounds(mContext.getDrawable(R.drawable.mycollection_press),null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
