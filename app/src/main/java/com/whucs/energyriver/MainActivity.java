package com.whucs.energyriver;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.whucs.energyriver.Adapter.MainPagerAdapter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.Layout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private ImageView inquiry,control,user,cur_tab;//底部导航
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();

    }

    private void initWidget(){
        viewPager = (ViewPager) findViewById(R.id.container);
        inquiry = (ImageView) findViewById(R.id.inquiry);
        control = (ImageView) findViewById(R.id.control);
        user = (ImageView) findViewById(R.id.user);
        inquiry.setOnClickListener(this);
        control.setOnClickListener(this);
        user.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        res = getResources();
        setCurrentTab(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.inquiry:
                setCurrentTab(0);
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.control:
                setCurrentTab(1);
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.user:
                setCurrentTab(2);
                viewPager.setCurrentItem(2,true);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setCurrentTab(int position){
        switch (position){
            case 0:
                if(cur_tab == null)
                    cur_tab = inquiry;
                else {
                    if (cur_tab != inquiry) {
                        clearAllTab();
                        cur_tab = inquiry;
                        cur_tab.setBackgroundColor(res.getColor(R.color.selected_tab_blue));
                    }
                }
                break;
            case 1:
                if (cur_tab != control) {
                    clearAllTab();
                    cur_tab = control;
                    cur_tab.setBackgroundColor(res.getColor(R.color.selected_tab_blue));
                }
                break;
            case 2:
                if (cur_tab != user) {
                    clearAllTab();
                    cur_tab = user;
                    cur_tab.setBackgroundColor(res.getColor(R.color.selected_tab_blue));
                }
                break;
            default:
                break;
        }
    }

    private void clearAllTab(){
        Drawable[] origin_menu = Layout.getOriginMenu(this);
        inquiry.setImageDrawable(origin_menu[0]);
        inquiry.setBackgroundColor(res.getColor(R.color.tab_blue));
        control.setImageDrawable(origin_menu[1]);
        control.setBackgroundColor(res.getColor(R.color.tab_blue));
        user.setImageDrawable(origin_menu[2]);
        user.setBackgroundColor(res.getColor(R.color.tab_blue));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        if(Common.getID(this) == -1) {
            Intent intent = new Intent(this,LogActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }else
            super.onResume();
    }
}
