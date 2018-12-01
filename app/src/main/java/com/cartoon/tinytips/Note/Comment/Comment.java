package com.cartoon.tinytips.Note.Comment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Default_many;
import com.cartoon.tinytips.util.Adapters.Comment.CommentAdapter;
import com.cartoon.tinytips.util.Adapters.Comment.CommentItem;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class Comment extends BaseActivity<CommentPresenter> implements IComment.View {
    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    private List<CommentItem> CommentList = new ArrayList<>();

    private CommentAdapter adapter;

    @BindView(R.id.comment_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected CommentPresenter initPresent(){
        return new CommentPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.comment;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        initToolbar();
    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("评论"));
    }

    public void recyclerList(ArrayList<CommentItem> commentItems) {
       if(commentItems.isEmpty()){
           //增加默认Item，防止空指针
           commentItems.add(new Default_many().getCommentItem());
       }

        for (CommentItem one : commentItems ){
            CommentList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(CommentList);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithoutData(this,Favorite.class);
        IntentActivity.finishActivity(this);
    }
}
