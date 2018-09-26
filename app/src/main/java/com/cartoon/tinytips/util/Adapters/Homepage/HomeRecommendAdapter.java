package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.HomePage.Recommend.RecommendItem;
import com.cartoon.tinytips.R;

import java.util.List;

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {
    private Context mContext;

    private List<RecommendItem> mRecommendItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImages;
        TextView userNames;
        TextView titles;
        TextView contents;
        TextView NumOfFavoirtes;
        TextView NumOfRecommends;
        TextView NumOfCollectoins;

        public ViewHolder(View view) {
            super(view);
            userImages = (ImageView) view.findViewById(R.id.Ritem_avatar);
            userNames = (TextView) view.findViewById(R.id.Ritem_username);
            titles = (TextView)view.findViewById(R.id.Ritem_title);
            contents= (TextView)view.findViewById(R.id.Ritem_content);
            NumOfRecommends = (TextView)view.findViewById(R.id.Ritem_numofrecommend);
            NumOfFavoirtes = (TextView)view.findViewById(R.id.Ritem_numoffavorite);;
            NumOfCollectoins = (TextView)view.findViewById(R.id.Ritem_numofcollection);
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
        holder.userNames.setText(RecommendItem.getuserName());
        holder.titles.setText(RecommendItem.gettitle());
        holder.contents.setText(RecommendItem.getcontent());
        holder.NumOfRecommends.setText(""+ RecommendItem.getNumOfRecommend());
        holder.NumOfCollectoins.setText(""+ RecommendItem.getNumOfCollectoin());
        holder.NumOfFavoirtes.setText(""+ RecommendItem.getNumOfFavoirte());
        Glide.with(mContext).load(RecommendItem.getuserImage()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mRecommendItems.size();
    }
}
