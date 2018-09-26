package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.HomePage.Hot.HotItem;
import com.cartoon.tinytips.R;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private Context mContext;

    private List<HotItem> mHotItems;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titles;
        TextView contents;
        TextView NumOfNumOfClick;

        public ViewHolder(View view) {
            super(view);
            titles = (TextView)view.findViewById(R.id.Hitem_title);
            contents= (TextView)view.findViewById(R.id.Hitem_content);
            NumOfNumOfClick = (TextView)view.findViewById(R.id.clicknums);
        }
    }

    public HotAdapter(List<HotItem> mHotItem) {
        this.mHotItems = mHotItem;
    }

    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot, parent, false);
        return new HotAdapter.ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(HotAdapter.ViewHolder holder, int position) {
        HotItem HotItem = mHotItems.get(position);
        holder.titles.setText("" + HotItem.getTitle());
        holder.contents.setText("" + HotItem.getContent());
        holder.NumOfNumOfClick.setText("" + HotItem.getNumOfClick());

    }

    @Override
    public int getItemCount() {
        return mHotItems.size();
    }

}