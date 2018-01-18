package com.whucs.energyriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.whucs.energyriver.Adapter.RankAdapter;
import com.whucs.energyriver.Bean.Rank;
import com.whucs.energyriver.Presenter.RankPresenter;
import com.whucs.energyriver.View.RankView;
import com.whucs.energyriver.Widget.StateSwitchFragment;

import java.util.List;


public class BranchRankFragment extends StateSwitchFragment implements RankView{
    private ListView listView;
    private Activity activity;
    private LinearLayout empty_layout,title;
    private TextView content;
    private RankPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        View content = inflater.inflate(R.layout.rank,null);
        initWidget(content);
        return view;
    }

    private void initWidget(View view){
        iniAdapter(view);
        activity = getActivity();
        listView = (ListView) view.findViewById(R.id.listView);
        empty_layout = (LinearLayout)view.findViewById(R.id.empty_layout);
        title = (LinearLayout) view.findViewById(R.id.title);
        content = (TextView) empty_layout.findViewById(R.id.content);

        presenter = new RankPresenter(this);
        presenter.getBranchRank(activity);
    }

    @Override
    public void setRank(List<Rank> ranks) {
        if(ranks!=null) {
            RankAdapter adapter = new RankAdapter(activity, ranks);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            empty_layout.setVisibility(View.GONE);
            title.setVisibility(View.VISIBLE);
        }else {
            empty_layout.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
            content.setText("暂无子机构");
        }
        showViewByTag("content");
    }

    @Override
    public void getRankError() {
        showViewByTag("error");
    }

    @Override
    public void reload(){
        if(activity == null)
            activity = getActivity();
        if(presenter == null)
            presenter = new RankPresenter(this);
        presenter.getBranchRank(activity);
    }
}


