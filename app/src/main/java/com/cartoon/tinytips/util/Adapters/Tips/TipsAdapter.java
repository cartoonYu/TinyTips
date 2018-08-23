package com.cartoon.tinytips.util.Adapters.Tips;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import com.cartoon.tinytips.R;


import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    private Context mContext;
    private List<TipsItem> mTList;
    private IOnItemClickListener mClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tipsName;
        private IOnItemClickListener mListener;
        public ViewHolder(View view,IOnItemClickListener listener){
            super(view);
            mListener = listener;
            // 为ItemView添加点击事件
            itemView.setOnClickListener(this);
            tipsName = (TextView) view.findViewById(R.id.Tips_Name);
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {this.mClickListener = listener;}

    public TipsAdapter(List<TipsItem> TList) {
        this.mTList = TList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.tips_item, parent, false);
        return new ViewHolder(view,mClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipsItem tipsItem= mTList.get(position);
        holder.tipsName.setText(tipsItem.getTipsname());

        }

    @Override
    public int getItemCount() {
        return mTList.size();
    }


}
