package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.HomePage.Recommend.RecommendItem;
import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> implements View.OnClickListener{
    private Context mContext;

    private List<RecommendItem> mRecommendItems;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Recommend_itemup:{
                IntentActivity.intentWithoutData(getContext(),NoteDetail.class);
                break;
            }

            case R.id.Ritem_avatar:{
                IntentActivity.intentWithoutData(getContext(),PersonalHomepage.class);
                break;
            }

            case R.id.Rnums_item_recommedn:{
                //评论
                IntentActivity.intentWithoutData(getContext(),Comment.class);
                break;
            }

            case R.id.Fnums_item_recommedn:{
                //赞
                Button Rnums_item_recommedn;
                Rnums_item_recommedn= (Button)view.findViewById(R.id.Fnums_item_recommedn);
                Rnums_item_recommedn.setBackgroundResource(R.drawable.favorite_press);
                break;
            }

            case R.id.Conums_item_recommedn:{
                //收藏
                Button Ronums_item_recommedn;
                Ronums_item_recommedn= (Button)view.findViewById(R.id.Conums_item_recommedn);
                Ronums_item_recommedn.setBackgroundResource(R.drawable.mycollection_press);
                break;
            }

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImages;
        TextView userNames;
        TextView titles;
        TextView contents;
        TextView NumOfFavoirtes;
        TextView NumOfRecommends;
        TextView NumOfCollectoins;
        RelativeLayout Recommend_itemup;
        Button Rnums_item_recommedn;
        Button Conums_item_recommedn;
        Button Fnums_item_recommedn;

        public ViewHolder(View view) {
            super(view);
            userImages = (ImageView) view.findViewById(R.id.Ritem_avatar);
            userNames = (TextView) view.findViewById(R.id.Ritem_username);
            titles = (TextView)view.findViewById(R.id.Ritem_title);
            contents= (TextView)view.findViewById(R.id.Ritem_content);
            NumOfRecommends = (TextView)view.findViewById(R.id.Ritem_numofrecommend);
            NumOfFavoirtes = (TextView)view.findViewById(R.id.Ritem_numoffavorite);;
            NumOfCollectoins = (TextView)view.findViewById(R.id.Ritem_numofcollection);
            Recommend_itemup = (RelativeLayout)view.findViewById(R.id.Recommend_itemup);
            Rnums_item_recommedn = (Button)view.findViewById(R.id.Rnums_item_recommedn);
            Conums_item_recommedn = (Button)view.findViewById(R.id.Conums_item_recommedn);
            Fnums_item_recommedn = (Button)view.findViewById(R.id.Fnums_item_recommedn);
        }
    }

    public HomeRecommendAdapter(List<RecommendItem> mRecommendItem) {
        this.mRecommendItems = mRecommendItem;
    }

    @Override
    public HomeRecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommend, parent, false);
        return new HomeRecommendAdapter.ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(HomeRecommendAdapter.ViewHolder holder, int position) {
        RecommendItem RecommendItem = mRecommendItems.get(position);
        holder.userNames.setText(RecommendItem.getUsername());
        holder.titles.setText(RecommendItem.getTitle());
        if(JudgeEmpty.isNotEmpty(RecommendItem.getContent())&&!RecommendItem.getContent().isEmpty()){
            for(SpannableString string:RecommendItem.getContent()){
                holder.contents.append(string);
            }
        }
        holder.NumOfRecommends.setText(""+ RecommendItem.getNumOfRecommend());
        holder.NumOfCollectoins.setText(""+ RecommendItem.getNumOfCollection());
        holder.NumOfFavoirtes.setText(""+ RecommendItem.getNumOfFavorite());
        holder.Recommend_itemup.setOnClickListener(this);
        holder.userImages.setOnClickListener(this);
        holder.NumOfRecommends.setOnClickListener(this);
        holder.NumOfFavoirtes.setOnClickListener(this);
        holder.NumOfCollectoins.setOnClickListener(this);
        Glide.with(mContext).load(RecommendItem.getUserImage()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mRecommendItems.size();
    }
}
