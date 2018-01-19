package com.whucs.energyriver;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whucs.energyriver.Adapter.MainPagerAdapter;
import com.whucs.energyriver.Bean.HttpListData;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Biz.NoticeBiz;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.Layout;
import com.whucs.energyriver.Widget.MessageImageView;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private ImageView inquiry,control,user,cur_tab;//底部导航
    private Resources res;
    private Toolbar toolbar;
    private TextView title;
    private MessageImageView menu;

    private LinearLayout pull_to_refresh;
    private TextView refresh_state;
    private int marginTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        //启动极光推服务
        JPushInterface.init(this);
        //更新别名
        JPushInterface.setAlias(this,Integer.parseInt(Common.getID(this)+""),Common.getUserName(this));//将Username设为别名

    }

    private void initWidget(){
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.container);
        inquiry = (ImageView) findViewById(R.id.inquiry);
        control = (ImageView) findViewById(R.id.control);
        user = (ImageView) findViewById(R.id.user);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.title);
        menu = (MessageImageView) toolbar.findViewById(R.id.menu);
        pull_to_refresh = (LinearLayout) findViewById(R.id.pull_to_refresh);
        refresh_state = (TextView) pull_to_refresh.findViewById(R.id.refresh_state);

        inquiry.setOnClickListener(this);
        control.setOnClickListener(this);
        user.setOnClickListener(this);
        menu.setOnClickListener(this);
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
            case R.id.menu:
                switch (menu.getTag().toString()){
                    case "rank":
                        Intent intent = new Intent(this,RankActivity.class);
                        startActivity(intent);
                        break;
                    case "menu":
                        Intent intention = new Intent(this,SceneActivity.class);
                        startActivity(intention);
                        break;
                    case "mail":
                        Intent mail = new Intent(this,NotificationActivity.class);
                        startActivity(mail);
                    default:
                        break;
                }
                break;
            default:
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
                if (cur_tab == null || cur_tab != inquiry) {
                    clearAllTab();
                    cur_tab = inquiry;
                    cur_tab.setBackgroundColor(res.getColor(R.color.selected_tab_blue));
                    title.setText(res.getText(R.string.inquiry));
                    menu.setImageDrawable(res.getDrawable(R.mipmap.mail));
                    menu.setTag("mail");
                    menu.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                }
                break;
            case 1:
                if (cur_tab != control) {
                    clearAllTab();
                    cur_tab = control;
                    cur_tab.setBackgroundColor(res.getColor(R.color.selected_tab_blue));
                    toolbar.setVisibility(View.GONE);
                   /* title.setText(res.getText(R.string.control));
                    menu.setImageDrawable(res.getDrawable(R.mipmap.menu));
                    menu.setTag("menu");
                    menu.setVisibility(View.VISIBLE);*/
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
            case 2:
                if (cur_tab != user) {
                    clearAllTab();
                    cur_tab = user;
                    cur_tab.setBackgroundColor(res.getColor(R.color.selected_tab_blue));
                    title.setText(res.getText(R.string.self_info));
                    menu.setVisibility(View.GONE);
                    toolbar.setVisibility(View.VISIBLE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
            default:
                break;
        }
    }

    private void getNoticeUnReadNum(){
        NoticeBiz noticeBiz = new NoticeBiz();
        noticeBiz.getNoticeByType(this,Common.getID(this),0)//此处type=-2
                .subscribeOn(Schedulers.io())
                .map(new Func1<HttpListData<List<Notice>>, List<Notice>>() {
                    @Override
                    public List<Notice> call(HttpListData<List<Notice>> noticeHttpListData) {
                        return noticeHttpListData.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Notice>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("what",e.getMessage());

                    }

                    @Override
                    public void onNext(List<Notice> notices) {
                        menu.setNum(notices.size());
                    }
                });
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
        }else{
            if(viewPager.getCurrentItem() == 0){
                getNoticeUnReadNum();//刷新消息未读数
            }
        }
        super.onResume();
    }

    public void showPullToRefresh(int padding){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pull_to_refresh.getLayoutParams();
        marginTop = (int) res.getDimension(R.dimen.pull_refresh_initial);
        if(padding>marginTop)
            pull_to_refresh.setVisibility(View.VISIBLE);
        lp.setMargins(0,marginTop+padding,0,0);
        pull_to_refresh.setLayoutParams(lp);
    }

    public void hidePullToRefresh(){
        marginTop = (int) res.getDimension(R.dimen.pull_refresh_initial);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pull_to_refresh.getLayoutParams();
        if(lp.topMargin!=marginTop){
            lp.setMargins(0,marginTop,0,0);
            pull_to_refresh.setVisibility(View.INVISIBLE);
            pull_to_refresh.setLayoutParams(lp);
        }
    }

    public void setRefreshState(String state){
        refresh_state.setText(state);
    }
}
