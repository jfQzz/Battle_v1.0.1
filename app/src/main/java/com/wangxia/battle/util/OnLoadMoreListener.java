package com.wangxia.battle.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.RecyclerView.OnScrollListener;

/**
 * Created by 昝奥博 on 2017/9/25 0025
 * Email:18772833900@163.com
 * Explain：
 */
class OnLoadMoreListener extends OnScrollListener {
    private int lastVisibleItemPosition;
    private ILoadMoreListener iLoadMore;

    public OnLoadMoreListener(ILoadMoreListener iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == recyclerView.getAdapter().getItemCount()) {
            // 加载更多
            iLoadMore.loadMoreData();

        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

    }

    public interface ILoadMoreListener{
        void loadMoreData();
    }
}
