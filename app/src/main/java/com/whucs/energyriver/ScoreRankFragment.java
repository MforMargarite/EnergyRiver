package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whucs.energyriver.Widget.StateSwitchFragment;


public class ScoreRankFragment extends StateSwitchFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        View content = inflater.inflate(R.layout.score_rank,null);
        iniAdapter(content);
        return view;
    }
}


