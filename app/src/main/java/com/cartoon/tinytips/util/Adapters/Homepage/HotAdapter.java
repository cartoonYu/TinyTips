package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.HomePage.Hot.HotItem;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;

    private List<HotItem> mHotItems;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.HotBody:{
                IntentActivity.intentWithoutData(getContext(),NoteDetail.class);
                break;
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Hitem_sequence)
        TextView sequence;

        @BindView(R.id.Hitem_title)
        TextView titles;

        @BindView(R.id.Hitem_content)
        TextView contents;

        @BindView(R.id.clicknums)
        TextView NumOfNumOfClick;

        @BindView(R.id.HotBody)
        RelativeLayout HotBody;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
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
        holder.sequence.setText(Integer.toString(0).concat(Integer.toString(HotItem.getSequence())));
        holder.titles.setText(HotItem.getTitle());
        if(JudgeEmpty.isNotEmpty(HotItem.getContent())&&!HotItem.getContent().isEmpty()){
            for(SpannableString string:HotItem.getContent()){
                holder.contents.append(string);
            }
        }
        holder.NumOfNumOfClick.setText(Integer.toString(HotItem.getNumOfClick()));
        holder.HotBody.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mHotItems.size();
    }

}