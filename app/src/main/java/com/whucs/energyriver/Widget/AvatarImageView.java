package com.whucs.energyriver.Widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.whucs.energyriver.R;

public class AvatarImageView extends ImageView {
    private Bitmap bitmap;

    public AvatarImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarImageView(Context context) {
        super(context);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        this.bitmap = toRoundBitmap(bm);
        super.setImageBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r;
        if(width > height) {
            r = height;
        } else {
            r = width;
        }
        Bitmap backgroundBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        canvas.drawRoundRect(rect, r/2, r/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rect, paint);
        return backgroundBmp;
    }
}
