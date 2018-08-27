package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.HomePage.Favorite.FavoriteItem;
import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.ShareNote.ShareNote;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.List;

import butterknife.BindView;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> implements View.OnClickListener{
    private Context mContext;

    private List<FavoriteItem> mFavoriteItems;
    private IOnItemClickListener mClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView userImages;
        TextView userNames;
        TextView titles;
        TextView contents;
        TextView NumOfFavoirtes;
        TextView NumOfRecommends;
        TextView NumOfCollectoins;
        TextView NumOfShares;
        TextView time;
        Button favoritenums_item_favorite;
        Button commuitenums_item_favorite;
        Button Cnums_item_favorite;
        Button sharenums_item_favorite;

        private IOnItemClickListener mListener;

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
            favoritenums_item_favorite = (Button)view.findViewById(R.id.favoritenums_item_favorite);
            commuitenums_item_favorite= (Button)view.findViewById(R.id.commuitenums_item_favorite);
            Cnums_item_favorite= (Button)view.findViewById(R.id.Cnums_item_favorite);
            sharenums_item_favorite= (Button)view.findViewById(R.id.sharenums_item_favorite);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favoritenums_item_favorite:{
                Button favoritenums_item_favorite;
                favoritenums_item_favorite = (Button)view.findViewById(R.id.favoritenums_item_favorite);
                favoritenums_item_favorite.setBackgroundResource(R.drawable.favorite_press);
                break;
            }

            case R.id.commuitenums_item_favorite:{
                IntentActivity.intentWithoutData(getContext(),Comment.class);
                break;
            }

            case R.id.Cnums_item_favorite:{
                Button Cnums_item_favorite;
                Cnums_item_favorite= (Button)view.findViewById(R.id.Cnums_item_favorite);
                Cnums_item_favorite.setBackgroundResource(R.drawable.mycollection_press);
                break;
            }

            case R.id.sharenums_item_favorite:{
                IntentActivity.intentWithoutData(getContext(),ShareNote.class);
                break;
            }

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
        holder.favoritenums_item_favorite.setOnClickListener(this);
        holder.commuitenums_item_favorite.setOnClickListener(this);
        holder.Cnums_item_favorite.setOnClickListener(this);
        holder.sharenums_item_favorite.setOnClickListener(this);
        Glide.with(mContext).load(FavoriteItem.getuserImage()).into(holder.userImages);
    }

    @Override
    public int getItemCount() {
        return mFavoriteItems.size();
    }



}
