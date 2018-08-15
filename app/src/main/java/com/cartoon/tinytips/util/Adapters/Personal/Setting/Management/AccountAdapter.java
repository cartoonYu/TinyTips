package com.cartoon.tinytips.util.Adapters.Personal.Setting.Management;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{

    private Context mContext;

    private List<Account> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        @BindView(R.id.personal_setting_management_nickName)
        TextView nickName;

        @BindView(R.id.personal_setting_management_headPro)
        CircleImageView headPro;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            cardView = (CardView) view;
        }
    }

    public AccountAdapter(List<Account> list) {
        this.list = list;
        Log.d("asd",this.list.get(0).getNickName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.personal_setting_management_account_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Account account=list.get(position);
        holder.headPro.setImageDrawable(account.getHeadPro());
        holder.nickName.setText(account.getNickName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
