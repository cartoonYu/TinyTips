package com.cartoon.tinytips.Personal;

import android.content.Intent;
import android.widget.LinearLayout;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Personal.PersonalDetail.PersonalDetail;
import com.cartoon.tinytips.R;

import butterknife.BindView;
import butterknife.OnClick;

public class Personal extends BaseFragment<PersonalPresenter> implements IPersonal.View {
    private PersonalPresenter presenter;

    @BindView(R.id.detail_Personal)
    LinearLayout detail;

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
}
