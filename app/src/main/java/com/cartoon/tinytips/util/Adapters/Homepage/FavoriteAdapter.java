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
import com.cartoon.tinytips.HomePage.Favorite.FavoriteItem;
import com.cartoon.tinytips.HomePage.Favorite.FavoriteModel;
import com.cartoon.tinytips.HomePage.Favorite.IFavorite;
import com.cartoon.tinytips.Note.Comment.Comment;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Note.ShareNote.ShareNote;
import com.cartoon.tinytips.Personal.PersonalHomepage.PersonalHomepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private Context mContext;

    private List<FavoriteItem> mFavoriteItems;

    private IFavorite.Model model;
    static class ViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.Fitem_avatar)
        ImageView userImages;

        @BindView(R.id.Fitem_username)
        TextView userNames;

        @BindView(R.id.Fitem_title)
        TextView titles;

        @BindView(R.id.Fitem_content)
        TextView contents;

        @BindView(R.id.homePageFavoriteImage)
        ImageView image;

        @BindView(R.id.Fitem_numoffavorite)
        TextView NumOfFavorites;

        @BindView(R.id.Fitem_numofrecommend)
        TextView NumOfRecommends;

        @BindView(R.id.Fitem_numofcollection)
        TextView NumOfCollections;

        @BindView(R.id.Fitem_numofshare)
        TextView NumOfShares;

        @BindView(R.id.Fitem_time)
        TextView time;

        @BindView(R.id.favoritenums_item_favorite)
        Button favoritenums_item_favorite;

        @BindView(R.id.commuitenums_item_favorite)
        Button commuitenums_item_favorite;

        @BindView(R.id.Cnums_item_favorite)
        Button Cnums_item_favorite;

        @BindView(R.id.sharenums_item_favorite)
        Button sharenums_item_favorite;

        @BindView(R.id.body_item_favorite)
        RelativeLayout body_item_favorite;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }


    }

    public FavoriteAdapter(List<FavoriteItem> mFavoriteItem) {
        this.mFavoriteItems = mFavoriteItem;
        model = new FavoriteModel();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_favorite, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.body_item_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                IntentActivity.intentWithData(mContext,NoteDetail.class,"note",mFavoriteItems.get(position).getNote());
            }
        });
        return holder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(final FavoriteAdapter.ViewHolder holder, final int position) {
        final FavoriteItem FavoriteItem = mFavoriteItems.get(position);
        Note note=FavoriteItem.getNote();
        holder.userNames.setText(note.getAuthor());
        holder.titles.setText(note.getTitle());
        if(!note.getWordDetails().isEmpty()){
            holder.contents.setText(note.getWordDetails().get(0));
        }
        if(JudgeEmpty.isNotEmpty(note.getPhotoDetails())){
            if(!note.getPhotoDetails().isEmpty()){
                Glide.with(mContext).load(note.getPhotoDetails().get(0)).into(holder.image);
            }
        }

        holder.NumOfRecommends.setText(Integer.toString(FavoriteItem.getNumOfRecommend()));
        holder.NumOfCollections.setText(Integer.toString(FavoriteItem.getNumOfCollection()));
        holder.NumOfFavorites.setText(Integer.toString(FavoriteItem.getNumOfFavorite()));
        holder.time.setText(FavoriteItem.getTime());
        holder.NumOfShares.setText(Integer.toString(FavoriteItem.getNumOfShare()));
        Glide.with(mContext).load(FavoriteItem.getUserImage()).into(holder.userImages);
        holder.Cnums_item_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.Cnums_item_favorite.setBackgroundResource(R.drawable.mycollection_press);
                final int num = new Integer(holder.NumOfCollections.getText().toString());
                FavoriteItem item = mFavoriteItems.get(holder.getAdapterPosition());
                item.setNumOfCollection(num+1);
                model.addFavorites(item, new String("collect"), new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.NumOfCollections.setText(Integer.toString(num+1));
                        ShowToast.shortToast(s);
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
        holder.favoritenums_item_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.favoritenums_item_favorite.setBackgroundResource(R.drawable.favorite_press);
                final int num = new Integer(holder.NumOfFavorites.getText().toString());
                FavoriteItem item = mFavoriteItems.get(holder.getAdapterPosition());
                item.setNumOfFavorite(num+1);
                model.addFavorites(item,new String("like"),new ValueCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.NumOfFavorites.setText(Integer.toString(num+1));
                        ShowToast.shortToast(s);
                    }

                    @Override
                    public void onFail(String msg) {
                        ShowToast.shortToast(msg);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavoriteItems.size();
    }
}
