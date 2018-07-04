package com.cartoon.tinytips.Personal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.Personal.Collect.Collect;
import com.cartoon.tinytips.Personal.Profile.Profile;
import com.cartoon.tinytips.Personal.Security.Security;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.TinyTipsApplication;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cartoon on 2018/2/4.
 * 1.我的页面，主活动（Main）的三个Fragment之一
 * 2.layout：personal
 * 3.新建方法前先到IPersonal.View定义方法，再回到此类重写方法
 * 4.具体业务逻辑在PersonalPresenter中处理
 * 5.利用父类（BaseFragment）的成员变量presenter调用PersonalPresenter的成员方法进行业务逻辑的处理
 *
 *功能
 * 1.显示用户的头像，昵称，个人签名
 * 2.为用户收藏页，已分享笔记页，安全页，个人信息页，注销提供入口
 *
 * 仍需进行的操作：
 * 1.编写用户收藏页，已分享笔记页，安全页，个人信息页，注销入口，在函数onClick编写具体逻辑（Intent跳转）
 * 2.用户头像，昵称，个人签名具体的数据要到数据库中提取显示
 */

public class Personal extends BaseFragment<PersonalPresenter> implements IPersonal.View{

    @BindView(R.id.toolBarBack) TextView back;                   //标题栏的返回按钮，因为此页面没有返回功能，所以定义此变量目的是把返回符号去掉
    @BindView(R.id.personalHeadPortrait) ImageView headPortrait;          //用户头像
    @BindView(R.id.personalNickName) TextView nickName;               //用户昵称
    @BindView(R.id.personalSignature) TextView signature;              //用户签名

    private Intent intent;

    @Override
    protected PersonalPresenter initPresent(){
        return new PersonalPresenter(this);
    }
    @Override
    protected int getLayout(){
       return R.layout.personal;
    }
    @Override
    protected void initView(){
        back.setBackgroundColor(getResources().getColor(R.color.ashBlack));
        presenter.initData();
    }
    @Override
    protected void onPrepare(){
    }

    @OnClick(R.id.personalProfile)
    public void handleClickProfile(){
        //点击头像所在区域，跳转到个人资料页（Profile）
        intent=new Intent(getActivity(), Profile.class);
        intent.putExtra("nickName",nickName.getText().toString());
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.personalSecurity)
    public void handleClickSecurity(){
        //点击安全，跳转到修改密码页（Security）
        intent=new Intent(getActivity(), Security.class);
        intent.putExtra("nickName",nickName.getText().toString());
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.personalMyCollect)
    public void handleClickCollect(){
        //点击我的收藏
        intent=new Intent(getActivity(), Collect.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.personalMyShare)
    public void handleClickShare(){
        showToast("此功能尚未开放");
    }

    @OnClick(R.id.personalLogout)
    public void handleClickLogout(){
        //点击注销
        showToast("此功能暂未开放");
    }

    @Override
    public void showToast(String code){
        Toast.makeText(TinyTipsApplication.getContext(),code,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initHeadPortrait(String str){
        Glide.with(getActivity()).load(str).into(headPortrait);
    }
    @Override
    public void initSignature(String str){
        signature.setText(str);
    }
    @Override
    public void initNickName(String str){
        nickName.setText(str);
    }
}
