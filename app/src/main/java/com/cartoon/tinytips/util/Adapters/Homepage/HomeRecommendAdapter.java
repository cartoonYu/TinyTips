package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.HomePage.Recommend.IRecommend;
import com.cartoon.tinytips.HomePage.Recommend.Recommend;
import com.cartoon.tinytips.HomePage.Recommend.RecommendItem;
import com.cartoon.tinytips.HomePage.Recommend.RecommendModel;
import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.Note.DivideNote;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class HomeRecommendAdapter
        extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {

    private Context mContext;

    private List<RecommendItem> mRecommendItems;

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

    public HomeRecommendAdapter(List<RecommendItem> mRecommendItem) {
        this.model=new RecommendModel();
        this.mRecommendItems = mRecommendItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommend, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendItem item=mRecommendItems.get(holder.getAdapterPosition());
                IntentActivity.intentWithData(mContext,NoteDetail.class,"note",item.getNote());
            }
        });
        holder.collect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                holder.collect.setBackgroundResource(R.drawable.mycollection_press);
                final int num = new Integer(holder.collectNum.getText().toString());
                RecommendItem item = mRecommendItems.get(holder.getAdapterPosition());
                item.setNumOfCollection(num+1);
                model.addFavorites(item, new String("collect"), new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.collectNum.setText(Integer.toString(num+1));
                        ShowToast.shortToast(s);
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
                holder.like.setBackgroundResource(R.drawable.favorite_press);
                final int num=new Integer(holder.likeNum.getText().toString());
                RecommendItem item=mRecommendItems.get(holder.getAdapterPosition());
                item.setNumOfFavorite(num+1);
                model.addFavorites(item,new String("like"), new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.likeNum.setText(Integer.toString(num+1));
                        ShowToast.shortToast(s);
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });

        return holder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(HomeRecommendAdapter.ViewHolder holder, int position) {
        RecommendItem recommendItem = mRecommendItems.get(position);
        Note note=recommendItem.getNote();
        holder.userNames.setText(note.getAuthor());
        holder.titles.setText(note.getTitle());
        if(JudgeEmpty.isNotEmpty(note.getPhotoDetails())){
            if(!note.getPhotoDetails().isEmpty()){
                Glide.with(mContext).load(note.getPhotoDetails().get(0)).into(holder.noteImage);
            }
        }
        holder.contents.setText(note.getWordDetails().get(0));
        holder.collectNum.setText(""+recommendItem.getNumOfCollection());
        holder.commentNum.setText(""+recommendItem.getNumOfRecommend());
        holder.likeNum.setText(""+recommendItem.getNumOfFavorite());
        Glide.with(mContext).load(recommendItem.getUserImage()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mRecommendItems.size();
    }
}
