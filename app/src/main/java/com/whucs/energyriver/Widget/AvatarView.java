package com.whucs.energyriver.Widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import java.io.InputStream;



public class AvatarView extends ImageView{
    public Matrix matrix;
    ScaleGestureDetector multiplyTouchesListener;
    GestureDetector singleTouchListener;
    int pic_width,pic_height;
    public int parent_width,parent_height;
    float initTransX, initTransY,initScaleX,initScaleY;//初始化时保证图片包含圆形裁剪区
    float values[];
    RectF srcRect,dstRect;
    RectF circleRect;
    public static Bitmap bitmap;

    public AvatarView(Context context){
        super(context);
        parent_height = 1677;
        parent_width = 1080;
    }

    public AvatarView(Context context, Uri uri) {
        super(context);
        values = new float[9];
        setLongClickable(true);
        parent_height = 1677;
        parent_width = 1080;
        circleRect = new RectF((parent_width - (int)(520)) / 2, (parent_height - (int)(520)) / 2, (parent_width + (int)(520)) / 2, (parent_height + (int)(520)) / 2);
        multiplyTouchesListener = new ScaleGestureDetector(context, new MultiplyTouchesListener());
        singleTouchListener = new GestureDetector(context, new SingleTouchListener());
        try{
            InputStream is = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inMutable = true;
            BitmapFactory.decodeStream(is, null, options);
            pic_width = options.outWidth;
            pic_height = options.outHeight;
            is.close();
            if(pic_width>parent_width || pic_height>parent_height) {
                float y_scale = (float) pic_height / parent_height;
                float x_scale = (float) pic_width / parent_width;
                float scale = y_scale > x_scale ? y_scale : x_scale;
                if (scale <= 2)
                    options.inSampleSize = 2;
                else if (scale <= 4)
                    options.inSampleSize = 4;
                else
                    options.inSampleSize = 8;
            }
            options.inJustDecodeBounds = false;
            options.inMutable = true;
            is = context.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(is, null,options);
            pic_width = bitmap.getWidth();
            pic_height = bitmap.getHeight();
            is.close();
          } catch (Exception e){
            e.printStackTrace();
        }
        matrix = new Matrix();
        initScaleX = pic_width>=520?1.0f:(float)(520.0)/pic_width;
        initScaleY = pic_height>=520?1.0f:(float)(520.0)/pic_height;
        matrix.postScale(initScaleX, initScaleY);
        initTransX = (parent_width-pic_width*initScaleX)/2>(parent_width-520)/2?(parent_width-520)/2:(parent_width-pic_width*initScaleX)/2;
        initTransY = (parent_height-pic_height*initScaleY)/2>(parent_height-520)/2?(parent_height-520)/2:(parent_height-pic_height*initScaleY)/2;
        matrix.postTranslate(initTransX, initTransY);

        srcRect = new RectF(0,0,pic_width,pic_height);
        dstRect = new RectF();
        invalidate();
        Log.i("what", "长宽:" + parent_width + " " + parent_height);
    }

    @Override
    public void onDraw(Canvas canvas) {
            canvas.save();
            canvas.drawBitmap(bitmap, matrix, null);
            canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getPointerCount()>1)
            multiplyTouchesListener.onTouchEvent(event);
        else
            singleTouchListener.onTouchEvent(event);
        return true;
    }

    class SingleTouchListener extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnDoubleTapListener {
        int double_tap_count = 0;
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
            float offsetX = -distanceX;
            float offsetY = -distanceY;
            matrix.getValues(values);
            matrix.mapRect(dstRect, srcRect);
            if (dstRect.contains(circleRect)) {
                if(dstRect.left+offsetX>=(parent_width-520)/ 2.0)//左边线出界
                    offsetX = (float)((parent_width-520)/ 2.0-dstRect.left);
                else if(dstRect.right+offsetX<=(parent_width+520)/ 2.0)
                    offsetX = (float)((parent_width+520)/ 2.0 - dstRect.right);
                if(dstRect.top+offsetY>=(parent_height-520)/ 2.0)//上边线出界
                    offsetY = (float)((parent_height-520)/ 2.0 -dstRect.top)-2;
                else if(dstRect.bottom+offsetY<=(parent_height+520)/ 2.0)
                    offsetY = (float)((parent_height+520)/ 2.0 - dstRect.bottom);
                matrix.postTranslate(offsetX, offsetY);
                invalidate();
            }
            return true;
            }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
             return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (double_tap_count == 0) {
                matrix.postScale(2f, 2f, e.getX(), e.getY());
                matrix.mapRect(dstRect,srcRect);
                if(!dstRect.contains(circleRect)) {
                   //设为初始状态
                    matrix.reset();
                    matrix.setScale(initScaleX,initScaleY);
                    matrix.postTranslate(initTransX,initScaleY);
                }
                invalidate();
                double_tap_count = 1;
            } else {
                matrix.postScale(0.5f, 0.5f, e.getX(), e.getY());
                matrix.mapRect(dstRect, srcRect);
                if(!dstRect.contains(circleRect)) {
                    //设为初始状态
                    matrix.reset();
                    matrix.setScale(initScaleX,initScaleY);
                    matrix.postTranslate(initTransX,initTransY);
                }
                double_tap_count = 0;
            }
            invalidate();
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }

    class MultiplyTouchesListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float max_scale = 4f;
            float min_scale = (520)/pic_width>(520)/pic_height?(520)/(float)pic_width:(520)/(float)pic_height;
            float scale_factor = detector.getScaleFactor();
            matrix.getValues(values);
            if(scale_factor*values[Matrix.MSCALE_X]>max_scale )
                scale_factor = max_scale/values[Matrix.MSCALE_X];
            if(scale_factor*values[Matrix.MSCALE_X]<min_scale)
                scale_factor = min_scale/values[Matrix.MSCALE_X];
            matrix.postScale(scale_factor, scale_factor, detector.getFocusX(), detector.getFocusY());
            matrix.mapRect(dstRect,srcRect);
            if(!dstRect.contains(circleRect))
               matrix.postScale(1/scale_factor, 1/scale_factor, detector.getFocusX(), detector.getFocusY());
            invalidate();
            return true;
        }
    }
}

