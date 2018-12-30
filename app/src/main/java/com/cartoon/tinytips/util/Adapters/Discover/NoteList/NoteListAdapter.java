package com.cartoon.tinytips.util.Adapters.Discover.NoteList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.table.Information;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{
    private Context mContext;

    private List<NoteList_note> mNoteList;

    private IOnItemClickListener mClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private IOnItemClickListener mListener;
        ImageView userImage;
        TextView userNames;
        TextView Title;
        TextView contents;
        TextView NumOfFavoirtes;
        TextView NumOfRecommends;
        TextView NumOfCollectoins;
        RelativeLayout layout;

        public ViewHolder(View view, IOnItemClickListener listener) {
            super(view);
            mListener = listener;
            itemView.setOnClickListener(this);

            userImage = (ImageView) view.findViewById(R.id.NoteList_avatar);
            userNames = (TextView) view.findViewById(R.id.NoteList_username);
            Title = (TextView)view.findViewById(R.id.NoteList_title);
            contents= (TextView)view.findViewById(R.id.NoteList_content);
            NumOfRecommends = (TextView)view.findViewById(R.id.NoteList_numofrecommend);
            NumOfFavoirtes = (TextView)view.findViewById(R.id.NoteList_numoffavorite);;
            NumOfCollectoins = (TextView)view.findViewById(R.id.NoteList_numofcollection);
            layout = (RelativeLayout) view.findViewById(R.id.discover_note);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getAdapterPosition());

        }


    }
    public void setOnItemClickListener(IOnItemClickListener listener) {this.mClickListener = listener;}
    public NoteListAdapter(List<NoteList_note> NoteList) {
        this.mNoteList = NoteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view,mClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteList_note note = mNoteList.get(position);
        Information information = new Information();
        Glide.with(mContext).load(note.getuserImage()).into(holder.userImage);
        holder.userNames.setText(note.getuserName());
        holder.Title.setText(note.gettitle());
        holder.contents.setText(note.getcontent());
        holder.NumOfCollectoins.setText(note.getNumOfCollectoin());
        holder.NumOfFavoirtes.setText(note.getNumOfFavoirte());
        holder.NumOfRecommends.setText(note.getNumOfRecommend());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }


}
