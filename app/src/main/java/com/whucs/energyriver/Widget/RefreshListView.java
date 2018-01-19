package com.whucs.energyriver.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.whucs.energyriver.R;


public class RefreshListView extends ScrollListView {
    private RefreshInterface refreshListener;
    private FrameLayout refresh_footer;
    private boolean footer_refresh;
    public RefreshListView(Context context) {
            super(context);
        }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        refresh_footer = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.refresh_footer,null);
        if(getFooterViewsCount() == 0)
            addFooterView(refresh_footer);
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(totalItemCount <= visibleItemCount){
                    removeFooterView(refresh_footer);
                }
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView =getChildAt(getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == getHeight() && !footer_refresh) {
                        footer_refresh = refreshListener.showPullToRefresh();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

        });

    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRefreshListener(RefreshInterface refreshListener){
        this.refreshListener = refreshListener;
    }

    public void resetRefreshState(){
        footer_refresh = false;
    }

    public interface RefreshInterface{
        boolean showPullToRefresh();
    }
}

