package com.whucs.energyriver.Widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
import com.whucs.energyriver.R;

//场景功能 -> 圆形/圆角矩形场景控件
public class SceneView extends TextView{
    Paint paint,textPaint;
    Resources res;
    int radius,baseline;
    String info = "";
    Rect bounds;

    public SceneView(Context context){
        super(context);
        res = context.getResources();
        setGravity(Gravity.CENTER);
        initPaint();

    }

    public SceneView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        res = context.getResources();
        setGravity(Gravity.CENTER);
        initPaint();
    }

    private void initPaint(){
        if(paint == null)
            paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

        if(textPaint == null)
            textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(36);
        textPaint.setTextAlign(Paint.Align.LEFT);

        if(bounds == null)
            bounds = new Rect();

    }


    public void setSceneInfo(String info,boolean openStatus){
        if(info!=null)
            this.info = info;
        if(openStatus){
            paint.setStrokeWidth(2);
            paint.setAntiAlias(true);
            paint.setColor(res.getColor(R.color.login_btn));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setColor(Color.WHITE);
           // textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        }else
            initPaint();
        textPaint.getTextBounds(this.info, 0, this.info.length(), bounds);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radius = getWidth()/2-1;

       // canvas.drawCircle(radius+1,radius+1,radius,paint);
        canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()/2),20f,20f,paint);

        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        baseline = (getMeasuredHeight()/2 - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(info,getWidth()/2-bounds.width()/2,baseline,textPaint);

    }

    //宽=高
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(widthMeasureSpec));
    }


}
