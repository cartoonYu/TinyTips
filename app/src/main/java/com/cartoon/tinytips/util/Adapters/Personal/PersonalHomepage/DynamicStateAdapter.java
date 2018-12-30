package com.cartoon.tinytips.util.Adapters.Personal.PersonalHomepage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.table.Note;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicStateAdapter extends RecyclerView.Adapter<DynamicStateAdapter.ViewHolder>{

    private Context mContext;

    private List<DynamicState> list;

    private List<Note> noteList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        @BindView(R.id.personal_homepage_dynamicState_title)
        TextView title;    //标题

        @BindView(R.id.personal_homepage_dynamicState_date)
        TextView date;     //日期

        @BindView(R.id.personal_homepage_dynamicState_like)
        TextView like;     //点赞个数

        @BindView(R.id.personal_homepage_dynamicState_comment)
        TextView comment;   //评论个数

        @BindView(R.id.personal_homepage_dynamicState_collect)
        TextView collect;    //收藏个数

        @BindView(R.id.personal_homepage_dynamicState_forward)
        TextView forward;     //转发次数

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            cardView = (CardView) view;
        }
    }

    public DynamicStateAdapter(List<DynamicState> list) {           //传入日记列表
        this.list = list;
    }

    @Override
    public DynamicStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.personal_homepage_dynamicstate_item, parent, false);
        return new DynamicStateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DynamicStateAdapter.ViewHolder holder, int position) {

        DynamicState state=list.get(position);
        Note note = state.getNote();
        Map<String,Integer> numOfSocial=state.getNumOfSocial();
        Map<String,Boolean> isClick=state.getIsClick();

        holder.title.setText(note.getTitle());
        holder.date.setText(note.getDate());
        holder.like.setText(Integer.toString(numOfSocial.get("Like")));
        holder.comment.setText(Integer.toString(numOfSocial.get("Comment")));
        holder.collect.setText(Integer.toString(numOfSocial.get("Collect")));

        if(isClick.get("Like")){
            holder.like.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.drawable.favorite_press),null,null,null);
        }
        if(isClick.get("Collect")){
            holder.collect.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.drawable.mycollection_press),null,null,null);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
