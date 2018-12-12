package com.cartoon.tinytips.Note.Comment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.HomePage.Favorite.Favorite;
import com.cartoon.tinytips.HomePage.Homepage;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.CommentDetails;
import com.cartoon.tinytips.bean.Local.LocalInformation;
import com.cartoon.tinytips.bean.Note;
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

    @BindView(R.id.edit_comment)
    TextView editComment;

    private ArrayList<CommentItem> commentArraylist;

    private List<CommentItem> commentList;

    private CommentAdapter adapter;

    @BindView(R.id.comment_recyclerview)
    RecyclerView recyclerView;

    private Note note;

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
        getNote();
        presenter.initComment(note);
    }

    @OnClick(R.id.comment_editP)
    public void sendComment(){
        //评论发送点击事件
        String comment=editComment.getText().toString();
        presenter.addComment(comment,note);
    }

    @Override
    public void addComment(CommentItem list) {
        editComment.setText("");
        commentList.add(list);
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(commentList.size(),list);
        Log.d("AAAAAA",commentList.size()+"aaaaa");
    }


    private void getNote() {
        //获取上级页面传递的笔记对象
        note=IntentActivity.getIntentNote(this,"note");
    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("评论"));
    }

    @Override
    public void recyclerList(List<CommentItem> list) {
        commentList=list;
        Log.d("AAAAAA",commentList.size()+"");
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.finishActivity(this);
    }
}
