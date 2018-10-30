package com.cartoon.tinytips.Discover.NoteList;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;

public class NoteList extends BaseActivity<NoteListPresenter> implements INoteList.View {
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

    }

    @Override
    protected void onPrepare() {

    }
}
