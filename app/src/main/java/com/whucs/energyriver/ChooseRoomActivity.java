package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.whucs.energyriver.Adapter.BuildingAdapter;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Presenter.ChooseRoomPresenter;
import com.whucs.energyriver.View.ChooseRoomView;
import com.whucs.energyriver.Widget.StateSwitchActivity;
import java.util.List;

/*选择房间*/
public class ChooseRoomActivity extends StateSwitchActivity implements View.OnClickListener,ChooseRoomView{
    ImageView back;
    ListView building;
    ChooseRoomPresenter presenter;
    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(this).inflate(R.layout.choose_room,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);

        back = (ImageView) view.findViewById(R.id.back);
        building = (ListView) view.findViewById(R.id.building);
        back.setOnClickListener(this);

        presenter = new ChooseRoomPresenter(this);
        presenter.getBuildingInfo(this);
    }

    @Override
    public void reload(){
        presenter.getBuildingInfo(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                ChooseRoomActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showWaiting() {
        showLoading();
    }

    @Override
    public void setBuildingInfo(List<Building> buildings) {
        BuildingAdapter adapter = new BuildingAdapter(this,buildings);
        building.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        showContent();
    }

    @Override
    public void execError() {
        showError();
    }
}
