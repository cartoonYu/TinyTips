package com.cartoon.tinytips.Discover.NoteList;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.util.Adapters.Discover.NoteList.NoteListAdapter;
import com.cartoon.tinytips.util.Adapters.Discover.NoteList.NoteList_note;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.cartoon.tinytips.util.TinyTipsApplication.getContext;

public class NoteList extends BaseActivity<NoteListPresenter> implements INoteList.View {
    private NoteList_note[] notes = {new NoteList_note(R.drawable.nav_icon,"asad","asd","asd",123,123,123),
            new NoteList_note(R.drawable.nav_icon,"asad","asd","asd",123,123,123),
            new NoteList_note(R.drawable.nav_icon,"asad","asd","asd",123,123,123)};

    private List<NoteList_note> NL_notes = new ArrayList<>();

    private NoteListAdapter adapter;

    @BindView(R.id.notelist_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected NoteListPresenter initPresent() {
        return new NoteListPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.list;
    }

    @Override
    protected void initView() {
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
        for (NoteList_note one : notes ){
            NL_notes.add(one);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteListAdapter(NL_notes);
        recyclerView.setAdapter(adapter);
    }
}
