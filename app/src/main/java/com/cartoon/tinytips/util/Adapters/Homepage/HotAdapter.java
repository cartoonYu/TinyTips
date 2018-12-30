package com.cartoon.tinytips.util.Adapters.Homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartoon.tinytips.HomePage.Hot.IHot;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.table.Note;
import com.cartoon.tinytips.bean.view.StatSocial;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder>{

    private Context mContext;

    private List<StatSocial> mHotItems;

    private IHot.View view;

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

    public HotAdapter(IHot.View view,List<StatSocial> mHotItem) {
        this.mHotItems = mHotItem;
        this.view=view;
    }

    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.HotBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                StatSocial social=mHotItems.get(position);
                IntentActivity.intentWithData(mContext,NoteDetail.class,"social",social);
            }
        });
        return holder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(HotAdapter.ViewHolder holder, int position) {
        StatSocial social=mHotItems.get(position);
        if(JudgeEmpty.isNotEmpty(social.getWordDetails())){
            if(!social.getWordDetails().isEmpty()){
                holder.contents.setText(social.getWordDetails().get(0));
            }
        }
        holder.titles.setText(social.getTitle());
        holder.sequence.setText("0"+position);
        holder.NumOfNumOfClick.setText(Integer.toString(1000));
    }

    @Override
    public int getItemCount() {
        return mHotItems.size();
    }

}