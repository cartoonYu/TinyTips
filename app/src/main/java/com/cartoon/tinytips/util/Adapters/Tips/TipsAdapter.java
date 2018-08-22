package com.cartoon.tinytips.util.Adapters.Tips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cartoon.tinytips.R;


import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    private Context mContext;
    private List<TipsItem> mTList;
    ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void OnItemClick(View v, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tipsName;

        public ViewHolder(View view) {
            super(view);

            tipsName = (TextView) view.findViewById(R.id.Tips_Name);
        }
    }

    public TipsAdapter(List<TipsItem> TList) {
        this.mTList = TList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.tips_item, parent, false);
        return new ViewHolder(view);
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

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


}
