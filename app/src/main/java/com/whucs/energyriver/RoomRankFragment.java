package com.whucs.energyriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whucs.energyriver.Adapter.RoomRankAdapter;
import com.whucs.energyriver.Bean.RoomRank;
import com.whucs.energyriver.View.RoomRankView;
import java.util.List;


public class RoomRankFragment extends Fragment implements RoomRankView {
    private ListView listView;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.room_rank,null);
        initWidget(view);
        return view;
    }

    private void initWidget(View view){
        activity = getActivity();
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    public void setRoomRank(List<RoomRank> ranks) {
        RoomRankAdapter adapter = new RoomRankAdapter(activity,ranks);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getRankError() {

    }
}
