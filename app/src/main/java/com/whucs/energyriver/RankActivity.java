package com.whucs.energyriver;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.whucs.energyriver.Adapter.TabFragmentAdapter;
import com.whucs.energyriver.Public.Layout;
import com.whucs.energyriver.Widget.SlidingTabLayout;


public class RankActivity extends AppCompatActivity{
    private SlidingTabLayout slidingTab;
    private ViewPager viewPager;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank);
        initWidget();
    }

    private void initWidget(){
        Layout.setTranslucent(this);
        slidingTab = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.container);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RankActivity.this.finish();
            }
        });
        initViewPager(viewPager);
        initSlidingTab(slidingTab);
        slidingTab.setViewPager(viewPager);
    }

    private void initViewPager(ViewPager viewPager){
        TabFragmentAdapter tabAdapter = new TabFragmentAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
    }

    private void initSlidingTab(SlidingTabLayout slidingTab){
        Resources res = getResources();
        slidingTab.setCustomTabView(R.layout.sliding_tab,R.id.tab_text);
        slidingTab.setDividerColors(res.getColor(R.color.slide_tab_blue));
        slidingTab.setBackgroundColor(res.getColor(R.color.slide_tab_blue));
        slidingTab.setSelectedIndicatorColors(res.getColor(R.color.gradient_end));
    }

}
