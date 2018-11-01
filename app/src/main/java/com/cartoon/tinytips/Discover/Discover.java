package com.cartoon.tinytips.Discover;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.cartoon.tinytips.BaseFragment;
import com.cartoon.tinytips.R;

import com.cartoon.tinytips.util.Adapters.Major;
import com.cartoon.tinytips.util.Adapters.MajorAdapter;
import com.cartoon.tinytips.util.UI.RevampStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Discover extends BaseFragment<DiscoverPresenter> implements IDiscover.View{

    private Major[] major = {new Major(R.drawable.ithlw),new Major( R.drawable.jjgl),
            new Major(R.drawable.dztx), new Major(R.drawable.zzfl),
            new Major(R.drawable.gdjt),new Major(R.drawable.yssj),
            new Major(R.drawable.jxzdh),new Major(R.drawable.hyywx),
            new Major(R.drawable.jzx),new Major( R.drawable.wy),
            new Major(R.drawable.wlcl), new Major(R.drawable.hxhj),
            new Major(R.drawable.sx),new Major(R.drawable.fzfz)};

    private List<Major> MJList = new ArrayList<>();

    private MajorAdapter adapter;

    @BindView(R.id.major_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.statusBar)
    View statusBar;

    @Override
    protected DiscoverPresenter initPresent(){
        return new DiscoverPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.discover;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        recyclerList();

    }

    @Override
    protected void onPrepare(){

    }

    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }


    private void recyclerList() {
        for (Major one : major ){
            MJList.add(one);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.top = convertDpToPixel(7);
                        outRect.bottom = 0;
                        // top bottom left right 对应的值应该是dpi 而不是dp  dpi根据不同手机而不同

                        int i = parent.getChildLayoutPosition(view) % 2;//每行2个
                        switch (i) {
                            case 0://第一个
                                outRect.left = convertDpToPixel(20);
                                outRect.right = convertDpToPixel(20);
                                break;
                            case 1://第二个
                                outRect.left = convertDpToPixel(20);
                                outRect.right = convertDpToPixel(20);
                                break;
                        }
                    }
        });
        adapter = new MajorAdapter(MJList);
        recyclerView.setAdapter(adapter);
      }

      /**
       * 将dp转换成dpi
       */
    private int convertDpToPixel(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.density);
    }


}
