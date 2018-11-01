package com.cartoon.tinytips.util.Adapters.Personal.MyNote;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.JudgeEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private Context mContext;

    private List<Note> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        @BindView(R.id.personal_mynote_title)
        TextView title;    //标题

        @BindView(R.id.personal_mynote_date)
        TextView date;     //日期

        @BindViews({R.id.personal_mynote_tag1,R.id.personal_mynote_tag2,R.id.personal_mynote_tag3})
        List<TextView> tags;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            cardView = (CardView) view;
        }
    }

    public NoteAdapter(List<com.cartoon.tinytips.bean.Note> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.personal_mynote_item, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note=list.get(position);
        holder.title.setText(note.getTitle());
        holder.date.setText(note.getDate());
        if(JudgeEmpty.isNotEmpty(note.getTag())){
            for(int i=0;i<note.getTag().size();i++){
                holder.tags.get(i).setBackground(mContext.getDrawable(R.mipmap.personal_mynote_tag));
                holder.tags.get(i).setText(note.getTag().get(i));
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
