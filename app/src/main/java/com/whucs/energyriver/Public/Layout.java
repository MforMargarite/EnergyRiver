package com.whucs.energyriver.Public;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.whucs.energyriver.R;

public class Layout {
   // private static final int[] selected_menu_id = new int[]{R.mipmap.inquiry_selected,R.mipmap.control_selected,R.mipmap.me_selected};
    private static final int[] origin_menu_id = new int[]{R.mipmap.inquiry,R.mipmap.control,R.mipmap.me};
    private static Drawable[] origin_menu,selected_menu;
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }
    public static Drawable[] getOriginMenu(Context context){
        if(origin_menu == null) {
            origin_menu = new Drawable[origin_menu_id.length];
            Resources res = context.getResources();
            for (int i = 0; i < origin_menu_id.length; i++) {
                origin_menu[i] = res.getDrawable(origin_menu_id[i]);
            }
        }
        return origin_menu;
    }

/*
    public static Drawable[] getSelectedMenu(Context context){
        if(selected_menu == null) {
            selected_menu = new Drawable[3];
            Resources res = context.getResources();
            for (int i = 0; i < selected_menu_id.length; i++) {
                selected_menu[i] = res.getDrawable(selected_menu_id[i]);
            }
        }
        return origin_menu;
    }
*/
}
