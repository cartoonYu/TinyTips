package com.cartoon.tinytips.Personal;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Personal.Detail.PersonalDetail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import butterknife.BindView;
import butterknife.OnClick;

public class Personal extends BaseFragment<PersonalPresenter> implements IPersonal.View {

    private PersonalPresenter presenter;

    @BindView(R.id.detail_Personal)
    LinearLayout detail;

    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected PersonalPresenter initPresent(){
        presenter=new PersonalPresenter(this);
        return presenter;
    }

    @Override
    protected int getLayout(){
        return R.layout.personal;
    }

    @Override
    protected void initView(){
        revampStatusBar();
    }

    @Override
    protected void onPrepare(){

    }

    @OnClick(R.id.detail_Personal)
    public void onClickDetail(){
        Intent intent=new Intent(getActivity(), PersonalDetail.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void revampStatusBar(){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)statusBar.getLayoutParams();
        params.width=RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height= RevampStatusBar.getStatusBar(getContext());
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(getResources().getColor(R.color.skyBlue));
    }
}
