package com.whucs.energyriver.Widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

//显示未读消息个数的ImageView 具体实现:ImageView + 右上角用canvas画出消息个数圆圈
//主要参数: Bitmap src,int num
public class MessageImageView extends ImageView{
    String num;
    Paint paint,edgePaint,textPaint;
    public MessageImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //绘制红色实心圆
        paint = new Paint();
        paint.setARGB(255,255,0,0);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        //绘制白边
        edgePaint = new Paint();
        edgePaint.setARGB(255,255,255,255);
        edgePaint.setAntiAlias(true);
        edgePaint.setStyle(Paint.Style.STROKE);
        edgePaint.setStrokeWidth(2);
        //字体
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setARGB(255,255,255,255);
        textPaint.setTextSize(20);
    }

    public void setNum(int num){
        if(num == 0)
            this.num = null;
        else if(num>99)
            this.num = "99+";
        else
            this.num = num+"";
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(num!=null) {
            Rect rect = new Rect();
            textPaint.getTextBounds(num,0,num.length(),rect);
            int r = rect.width()/2+6;//圆的半径
            int width = this.getWidth();
            int center = width-7*r/6-2;

            setPadding(0, 5*r/6+2, 5*r/6+2, 0);
            canvas.drawCircle(center,r+2,r,paint);
            canvas.drawCircle(center,r+2,r+1,edgePaint);
            canvas.drawText(num,center-3.7f*r/5,4*r/3,textPaint);
        }
    }



}
