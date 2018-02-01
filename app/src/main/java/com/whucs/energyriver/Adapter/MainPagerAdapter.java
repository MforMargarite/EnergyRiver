package com.whucs.energyriver.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.whucs.energyriver.ControlFragment;
import com.whucs.energyriver.InquiryFragment;
import com.whucs.energyriver.MainActivity;
import com.whucs.energyriver.Presenter.ControlPresenter;
import com.whucs.energyriver.UserFragment;


public class MainPagerAdapter extends FragmentStatePagerAdapter {
    MainActivity activity;
    public MainPagerAdapter(MainActivity activity,FragmentManager fm) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InquiryFragment();
            case 1:
                ControlFragment controlFragment = new ControlFragment();
                activity.setOnRoomChangeListener(controlFragment);
                return controlFragment;
            default:
                return new UserFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
