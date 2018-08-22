package com.cartoon.tinytips.Note.Addnote.NoteTips;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Note.Addnote.AddNote;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Tips.TipsAdapter;
import com.cartoon.tinytips.util.Adapters.Tips.TipsItem;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class NoteTips extends BaseActivity<NoteTipsPresenter> implements INoteTips.View {

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.tips_recyclerview)
    RecyclerView recyclerView;

    private TipsAdapter adapter;
    private List<TipsItem> TList = new ArrayList<>();


    @Override
    protected NoteTipsPresenter initPresent() {
        return new NoteTipsPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.tips;
    }

    @Override
    protected void initView() {
        revampStatusBar();
        revampToolbar();
    }

    @Override
    protected void onPrepare() {
        initTips();
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("标签"));
}

    private void revampStatusBar(){
        RevampToolbar.setBack(back);
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithoutData(this,AddNote.class);
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.intentWithoutData(this,AddNote.class);
    }

    private void initTips(){
        TipsItem[] major = {new TipsItem("IT互联网"), new TipsItem("经济/管理"),
                new TipsItem("电子/通信"), new TipsItem("政治/法律"),
                new TipsItem("轨道/交通"), new TipsItem("艺术/设计"),
                new TipsItem("机械/自动化"), new TipsItem("汉语言/文学"),
                new TipsItem("建筑学"), new TipsItem("外语"),
                new TipsItem("物理/材料"), new TipsItem("化学/环境"),
                new TipsItem("数学"), new TipsItem("纺织/服装")};

        for (TipsItem one : major ){
            TList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TipsAdapter(TList);
        recyclerView.setAdapter(adapter);
    }


}
