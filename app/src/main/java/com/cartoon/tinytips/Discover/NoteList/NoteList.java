package com.cartoon.tinytips.Discover.NoteList;

import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.Main.Main;
import com.cartoon.tinytips.Note.Details.NoteDetail;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Discover.NoteList.NoteListAdapter;
import com.cartoon.tinytips.util.Adapters.Discover.NoteList.NoteList_note;
import com.cartoon.tinytips.util.Adapters.Tips.IOnItemClickListener;
import com.cartoon.tinytips.util.FragmentConstant;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class NoteList extends BaseActivity<NoteListPresenter> implements INoteList.View {

    private List<NoteList_note> NL_notes = new ArrayList<>();

    private NoteList_note note;

    private NoteListAdapter adapter;

    private String CT1 ="一、优点\n" +
            "保留了经典Java应用的架构，但开发、运行成本更低\n" +
            "\n" +
            "二、Java EE应用的分层模型\n" +
            "1、Domain Object（领域对象）层（也称为 持久层）：由一系列的POJO组成，这些对象往往包含着各自所需实现的业务逻辑方法，可以理解为操作数据的人\n";

    private String CT2 = "问题原因是Github 禁用了TLS v1.0 and v1.1，必须更新Windows的git凭证管理器。\n"+
            "通过此网址https://github.com/Microsoft/Git-Credential-Manager-for-Windows/releases/\n";

    private String CT3 ="举例：\n" +
            "1、商品推荐：要解决的问题：（1）大量的数据如何存储？（2）大量数据如何计算\n" +
            "\n" +
            "2、天气预报：要解决的问题：（1）大量的天气数据如何存储？";

    private String CT4 ="java基础和Linux基础\n" +
            "\n" +
            "Hadoop的学习\n" +
            "\n" +
            "第一阶段：HDFS、MapReduce、HBase（NoSQL数据库）\n";

    private NoteList_note[] notes = {new NoteList_note(R.drawable.apple,"cartoon","JavaEE应用",CT1,"1223","123","1233"),
            new NoteList_note(R.drawable.nav_icon,"Leo","HttpRequestException encountered解决方法",CT2,"223","1233","1231"),
            new NoteList_note(R.drawable.tou,"胖婷","什么是大数据",CT3,"123","123","123"),
            new NoteList_note(R.drawable.tou,"胖婷","学习大数据的路线",CT4,"123","123","123")};

    private int image;
    @BindView(R.id.notelist_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.toolbarBack)
    TextView back;

    @Override
    protected NoteListPresenter initPresent() {
        return new NoteListPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.list;
    }

    @Override
    public void setUserName(String userName) {
        this.note.setUsername(userName);
    }

    @Override
    public void setHeadPro(File headPro) {
        note.setUserImage(R.drawable.apple);
    }

    @Override
    public void setTitle(String Title) {
        note.setTitle(Title);
    }

    @Override
    public void setContents(String contents) {
        note.setContent(contents);
    }

    @Override
    public void setNumOF(String nof) {
        note.setNumOfFavoirte(nof);
    }

    @Override
    public void setNumOC(String noc) {
        note.setNumOfCollectoin(noc);
    }

    @Override
    public void setNumOR(String nor) {
        note.setNumOfRecommend(nor);
    }

    @Override
    protected void initView() {
        RevampToolbar.setBack(back);
        revampToolbar();
        revampStatusBar();
        recyclerList();
    }

    @Override
    protected void onPrepare() {

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    private void recyclerList(){
        note = new NoteList_note();
        for(NoteList_note one : notes){
            NL_notes.add(one);
        }


        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteListAdapter(NL_notes);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new IOnItemClickListener() {

            @Override
            public void onItemClick(View view, int postion) {
                IntentActivity.intentWithData(getContext(),NoteDetail.class,"NoteList_note",postion );
            }
        });
    }

    private void revampToolbar(){
        RevampToolbar.setText(toolbarText,new String("笔记列表"));
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack(){
        IntentActivity.intentWithData(this,Main.class,new String("main"), FragmentConstant.getConstant().getDiscover());
        IntentActivity.finishActivity(this);
    }


}
