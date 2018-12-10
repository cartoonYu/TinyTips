package com.cartoon.tinytips.util.Adapters.Personal.MyNote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.Personal.Detail.Detail;
import com.cartoon.tinytips.Personal.MyNote.IMyNote;
import com.cartoon.tinytips.Personal.MyNote.MyNoteModel;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.ValueCallBack;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.JudgeEmpty;
import com.cartoon.tinytips.util.ShowToast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context mContext;

    private List<Note> list;

    private IOnItemClickListener mClickListener;

    private IMyNote.Model model;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;

        @BindView(R.id.personal_mynote_title)
        TextView title;    //标题

        @BindView(R.id.personal_mynote_date)
        TextView date;     //日期

        @BindViews({R.id.personal_mynote_tag1,R.id.personal_mynote_tag2,R.id.personal_mynote_tag3})
        List<TextView> tags;

        ImageView delete;

        private IOnItemClickListener mListener;

        public ViewHolder(View view,IOnItemClickListener listener) {
            super(view);
            mListener = listener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,view);
            cardView = (CardView) view;
            delete = (ImageView)view.findViewById(R.id.personal_mynote_delete);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getAdapterPosition());
            switch (view.getId()){
                case R.id.personal_mynote_date: {
                }
            }
        }
    }


    public NoteAdapter(List<Note> list) {
        this.list = list;
        this.model=new MyNoteModel();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.personal_mynote_item, parent, false);
        final ViewHolder holder=new ViewHolder(view,mClickListener);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                IntentActivity.intentWithData(mContext,NoteDetail.class,"note",list.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setTitle("温馨提示");
                builder.setMessage("确定要删除该笔记吗？");
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int position=holder.getAdapterPosition();
                                model.deleteNote(list,list.get(position), new ValueCallBack<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        notifyDataSetChanged();
                                        ShowToast.shortToast(s);
                                    }

                                    @Override
                                    public void onFail(String msg) {
                                        ShowToast.shortToast(msg);
                                    }
                                });
                            }
                        });
                builder.setNegativeButton("取消",//这个string是设置右边按钮的文字
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        return holder;
    }
    public void setOnItemClickListener(IOnItemClickListener listener) {this.mClickListener = listener;}

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
