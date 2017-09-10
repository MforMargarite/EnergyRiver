package com.whucs.energyriver.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class ScrollGridView extends GridView {
    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        else {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }
}

