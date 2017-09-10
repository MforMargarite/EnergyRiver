package com.whucs.energyriver.Widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
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
        setTextColor(Color.WHITE);
        setTextSize(24);
        setGravity(Gravity.CENTER);
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
        radius = getWidth()/2-1;
        canvas.drawCircle(radius+1,radius+1,radius,paint);
    }

    //宽=高
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(widthMeasureSpec));
    }

}
