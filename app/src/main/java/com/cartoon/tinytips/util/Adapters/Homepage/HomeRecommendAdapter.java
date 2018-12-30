package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.HomePage.Recommend.IRecommend;
import com.cartoon.tinytips.HomePage.Recommend.RecommendModel;
import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.ActivityAndContext;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecommendAdapter
        extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {

    private Context mContext;

    private List<StatSocial> mSocials;

    private IRecommend.View view;

    private IRecommend.Model model;


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Ritem_avatar)
        ImageView userImages;

        @BindView(R.id.Ritem_username)
        TextView userNames;

        @BindView(R.id.Ritem_title)
        TextView titles;

        @BindView(R.id.recommend_item_image)
        ImageView noteImage;

        @BindView(R.id.Ritem_content)
        TextView contents;

        @BindView(R.id.Recommend_itemup)
        RelativeLayout Recommend_itemup;

        @BindView(R.id.homePageRecommendCollect)
        Button collect;

        @BindView(R.id.homePageRecommendComment)
        Button comment;

        @BindView(R.id.homePageRecommendLike)
        Button like;

        @BindView(R.id.homePageRecommendCollectNum)
        TextView collectNum;

        @BindView(R.id.homePageRecommendCommentNum)
        TextView commentNum;

        @BindView(R.id.homePageRecommendLikeNum)
        TextView likeNum;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public HomeRecommendAdapter(IRecommend.View view,List<StatSocial> mSocials) {
        this.view=view;
        this.model=new RecommendModel();
        this.mSocials=mSocials;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        final ViewHolder holder=
                new ViewHolder
                        (LayoutInflater.from(mContext).inflate(R.layout.item_recommend, parent, false));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //卡片点击事件
                StatSocial social=mSocials.get(holder.getAdapterPosition());
                IntentActivity.intentWithData(mContext,NoteDetail.class,"social",social);
            }
        });
        holder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏按钮点击事件
                final StatSocial social=mSocials.get(holder.getAdapterPosition());
                model.clickCollect(social,new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ShowToast.shortToast(s);
                        view.initData();
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞按钮点击事件
                final StatSocial social=mSocials.get(holder.getAdapterPosition());
                model.clickLike(social, new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ShowToast.shortToast(s);
                        view.initData();
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
       holder.userImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //头像点击事件
                final StatSocial social=mSocials.get(holder.getAdapterPosition());
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
       holder.userNames.setOnClickListener(new View.OnClickListener() {
            //昵称点击事件
            @Override
            public void onClick(View v) {
                final StatSocial social=mSocials.get(holder.getAdapterPosition());
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
       holder.comment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //评论点击事件
               final StatSocial social=mSocials.get(holder.getAdapterPosition());
               IntentActivity.intentWithData(mContext,Comment.class,"social",social);
           }
       });
       return holder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(HomeRecommendAdapter.ViewHolder holder, int position) {
        StatSocial temp=mSocials.get(position);
        holder.userNames.setText(temp.getNickName());
        holder.titles.setText(temp.getTitle());
        if(JudgeEmpty.isNotEmpty(temp.getPhoto())){
            if(!temp.getPhoto().isEmpty()){
                Glide.with(mContext).load(temp.getPhoto().get(0)).into(holder.noteImage);
            }
        }
        if(temp.isLove()){
            holder.like.setBackgroundResource(R.drawable.favorite_press);
        }
        if(temp.isCollect()){
            holder.collect.setBackgroundResource(R.drawable.mycollection_press);
        }
        if(JudgeEmpty.isNotEmpty(temp.getWordDetails())){
            if(!temp.getWordDetails().isEmpty()){
                holder.contents.setText(temp.getWordDetails().get(0));
            }
        }
        holder.collectNum.setText(Integer.toString(temp.getNumOfCollect()));
        holder.likeNum.setText(Integer.toString(temp.getNumOfLove()));
        holder.commentNum.setText(Integer.toString(temp.getNumOfComment()));
        Glide.with(mContext).load(temp.getHeadPortrait()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mSocials.size();
    }
}
