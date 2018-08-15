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
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.HomePage.Favorite.FavoriteItem;
import com.cartoon.tinytips.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private Context mContext;

    private List<FavoriteItem> mFavoriteItems;



    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImages;
        TextView userNames;
        TextView titles;
        TextView contents;
        TextView NumOfFavoirtes;
        TextView NumOfRecommends;
        TextView NumOfCollectoins;
        TextView NumOfShares;
        TextView time;

        public ViewHolder(View view) {
            super(view);
            userImages = (ImageView) view.findViewById(R.id.Fitem_avatar);
            userNames = (TextView) view.findViewById(R.id.Fitem_username);
            titles = (TextView)view.findViewById(R.id.Fitem_title);
            contents= (TextView)view.findViewById(R.id.Fitem_content);
            NumOfRecommends = (TextView)view.findViewById(R.id.Fitem_numofrecommend);
            NumOfFavoirtes = (TextView)view.findViewById(R.id.Fitem_numoffavorite);;
            NumOfCollectoins = (TextView)view.findViewById(R.id.Fitem_numofcollection);
            NumOfShares = (TextView)view.findViewById(R.id.Fitem_numofshare);
            time = (TextView)view.findViewById(R.id.Fitem_time);
        }
    }

    public FavoriteAdapter(List<FavoriteItem> mFavoriteItem) {
        this.mFavoriteItems = mFavoriteItem;
    }

    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteAdapter.ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
        FavoriteItem FavoriteItem = mFavoriteItems.get(position);
        holder.userNames.setText(FavoriteItem.getuserName());
        holder.titles.setText(FavoriteItem.gettitle());
        holder.contents.setText(FavoriteItem.getcontent());
        holder.NumOfRecommends.setText(""+ FavoriteItem.getNumOfRecommend());
        holder.NumOfCollectoins.setText(""+ FavoriteItem.getNumOfCollectoin());
        holder.NumOfFavoirtes.setText(""+ FavoriteItem.getNumOfFavoirte());
        holder.time.setText(""+FavoriteItem.getTime());
        holder.NumOfShares.setText(""+FavoriteItem.getNumOfShare());
        Glide.with(mContext).load(FavoriteItem.getuserImage()).into(holder.userImages);

    }

    @Override
    public int getItemCount() {
        return mFavoriteItems.size();
    }
}
