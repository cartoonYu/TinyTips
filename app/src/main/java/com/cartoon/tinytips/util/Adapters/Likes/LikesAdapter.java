package com.cartoon.tinytips.util.Adapters.Likes;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public abstract class LikesAdapter  extends RecyclerView.Adapter<LikesAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        private com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener mListener;

        public ViewHolder(View view,IOnItemClickListener listener) {
            super(view);
            mListener = listener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,view);

        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getAdapterPosition());
            switch (view.getId()){

            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull LikesAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
