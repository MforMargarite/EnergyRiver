package com.whucs.energyriver.Widget;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.app.Activity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.whucs.energyriver.R;
import com.whucs.energyriver.UserInfoActivity;
import java.io.ByteArrayOutputStream;


public class MyCircleCrop extends Activity implements View.OnClickListener{
    AvatarView avatarView;
    ShadeView shadeView;
    FrameLayout frameLayout;
    Button headpic_negative_btn;
    Button headpic_positive_btn;
    LinearLayout option;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.circle_crop_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        option = (LinearLayout)findViewById(R.id.options);
        headpic_positive_btn = (Button) findViewById(R.id.positive_headpic);
        headpic_negative_btn = (Button) findViewById(R.id.negative_headpic);
        headpic_positive_btn.setOnClickListener(this);
        headpic_negative_btn.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        uri = (Uri) bundle.get("data");
        avatarView = new AvatarView(this,uri);
        shadeView = new ShadeView(this);
        frameLayout.addView(avatarView);
        frameLayout.addView(shadeView);
        measureView(shadeView);
    }


    private void measureView(View child) {
        ViewGroup.LayoutParams params =child.getLayoutParams();
        if(params==null)
            params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, params.width);
        int lpHeight = params.height;
        int childHeightSpec ;
        if(lpHeight>0)
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        else
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        child.measure(childWidthSpec, childHeightSpec);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MyCircleCrop.this, UserInfoActivity.class);
        switch (v.getId()) {
            case R.id.positive_headpic:
                Bitmap bitmap;
                AvatarView.bitmap.recycle();
                avatarView.setDrawingCacheEnabled(true);
                bitmap = Bitmap.createBitmap(avatarView.getDrawingCache(), avatarView.parent_width/2 - 260, avatarView.parent_height/2 - 260, 520, 520);
                bitmap = toRoundBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 80, baos);
                byte[] pic_byte = baos.toByteArray();
                intent.putExtra("crop_pic", pic_byte);
                avatarView.setDrawingCacheEnabled(false);
                setResult(RESULT_OK, intent);
                bitmap.recycle();
                finish();
                break;
            case R.id.negative_headpic:
                finish();
                break;
            default:
                break;
        }
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        if(event.getY()<frameLayout.getHeight()) {
            return avatarView.onTouchEvent(event);
        }
        else {
            return this.onTouchEvent(event);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int bound = (option.getWidth()-2*headpic_positive_btn.getWidth())+headpic_positive_btn.getWidth();
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            return true;
        if(event.getAction()!=MotionEvent.ACTION_UP) {
            if(event.getX()<=headpic_negative_btn.getWidth())
                headpic_negative_btn.setPressed(true);
            else if(event.getX()>=bound)
                headpic_positive_btn.setPressed(true);
        }else {
            if(event.getX()<=headpic_negative_btn.getWidth()){
                headpic_negative_btn.setPressed(false);
               headpic_negative_btn.performClick();
            }
            else if(event.getX()>=bound) {
                headpic_positive_btn.setPressed(false);
                headpic_positive_btn.performClick();
            }
        }
        return true;
    }
}
