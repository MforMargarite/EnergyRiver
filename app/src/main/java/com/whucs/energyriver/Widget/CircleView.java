package com.whucs.energyriver.Widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.TextView;

//场景功能 -> 圆形场景模式
public class CircleView extends TextView{
    Paint paint;
    Resources res;
    int radius;
    public CircleView(Context context){
        super(context);
        res = context.getResources();
        initPaint();
    }

    public CircleView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        res = context.getResources();
        initPaint();
    }

    private void initPaint(){
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setPaintColor(String colorString){
        paint.setColor(Color.parseColor(colorString));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radius = getWidth()/2;
        canvas.drawCircle(radius,radius,radius,paint);
    }

    //宽=高
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(widthMeasureSpec));
    }

}
