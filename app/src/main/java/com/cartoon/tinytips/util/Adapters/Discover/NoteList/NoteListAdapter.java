package com.cartoon.tinytips.util.Adapters.Discover.NoteList;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Information;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Discover.Major;
import com.cartoon.tinytips.util.IntentActivity;

import java.util.List;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;

    private List<NoteList_note> mNoteList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userNames;
        TextView Title;
        TextView contents;
        TextView NumOfFavoirtes;
        TextView NumOfRecommends;
        TextView NumOfCollectoins;

        public ViewHolder(View view) {
            super(view);
            userImage = (ImageView) view.findViewById(R.id.NoteList_avatar);
            userNames = (TextView) view.findViewById(R.id.NoteList_username);
            Title = (TextView)view.findViewById(R.id.NoteList_title);
            contents= (TextView)view.findViewById(R.id.NoteList_content);
            NumOfRecommends = (TextView)view.findViewById(R.id.NoteList_numofrecommend);
            NumOfFavoirtes = (TextView)view.findViewById(R.id.NoteList_numoffavorite);;
            NumOfCollectoins = (TextView)view.findViewById(R.id.NoteList_numofcollection);
        }
    }

    public NoteListAdapter(List<NoteList_note> NoteList) {
        this.mNoteList = NoteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteList_note note = mNoteList.get(position);
        Information information = new Information();
        Glide.with(mContext).load(note.getuserImage()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.discover_note :{
                IntentActivity.intentWithoutData(getContext(),NoteDetail.class);
            }
        }
    }
}
