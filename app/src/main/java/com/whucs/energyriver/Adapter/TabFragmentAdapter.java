package com.whucs.energyriver.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.whucs.energyriver.LoopRankFragment;
import com.whucs.energyriver.R;
import com.whucs.energyriver.RoomRankFragment;
import com.whucs.energyriver.BranchRankFragment;
import com.whucs.energyriver.TypeRankFragment;


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
                return new RoomRankFragment();//区域排名
            case 1:
                return new BranchRankFragment();//机构排名
            case 2:
                return new LoopRankFragment();//设备排名
            case 3:
                return new TypeRankFragment();//用电类型排名
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
