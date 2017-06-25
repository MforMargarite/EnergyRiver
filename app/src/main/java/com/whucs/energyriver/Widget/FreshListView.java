package com.whucs.energyriver.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;


public class FreshListView extends ListView{
    private float beginY,currentY;
    private boolean isFreshing;
    private Refresh refresh;

    public FreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isFreshing = true;
    }

    public void setRefresh(Refresh refresh){
        this.refresh = refresh;
    }

    public void setIsFreshing(boolean isFreshing){
        this.isFreshing = isFreshing;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!isFreshing)
                    beginY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = ev.getY();
                if(!isFreshing) {
                    int slide = (int) (currentY - beginY > 296 ? 296 : currentY - beginY);
                    if (slide>0 && (getCount() == 0 || getChildAt(0).getTop() == 0)) {
                        //已经滑动到顶部 拦截滑动事件
                        refresh.showWaiting(slide);
                            if (slide == 296)
                                isFreshing = true;
                            return true;
                        }
                    }break;
                case MotionEvent.ACTION_UP:
                    if(isFreshing) {
                        refresh.onLoadResult();
                        return true;
                    }break;
            }
            return super.onTouchEvent(ev);
        }

    public interface Refresh{
        void showWaiting(int padding);
        void hideWaiting();
        void onLoadResult();
    }
}
