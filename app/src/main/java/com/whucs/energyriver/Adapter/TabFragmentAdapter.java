package com.whucs.energyriver.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.whucs.energyriver.DeptRankFragment;
import com.whucs.energyriver.R;
import com.whucs.energyriver.RoomRankFragment;
import com.whucs.energyriver.ScoreRankFragment;


public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    public TabFragmentAdapter(Context context,FragmentManager fm)
    {
        super(fm);
        titles = context.getResources().getStringArray(R.array.tab_name);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
                return new RoomRankFragment();//房间排名
            case 1:
                return new ScoreRankFragment();//积分排名
            case 2:
                return new DeptRankFragment();//部门排名
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public String getPageTitle(int position){
        return titles[position];
    }
}
