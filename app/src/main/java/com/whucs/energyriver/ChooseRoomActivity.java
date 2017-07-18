package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.whucs.energyriver.Adapter.BuildingAdapter;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Presenter.ChooseRoomPresenter;
import com.whucs.energyriver.View.ChooseRoomView;

import java.util.List;

/*选择房间*/
public class ChooseRoomActivity extends AppCompatActivity implements View.OnClickListener,ChooseRoomView{
    ImageView back;
    ListView building;
    ChooseRoomPresenter presenter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_room);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        building = (ListView) findViewById(R.id.building);
        back.setOnClickListener(this);

        presenter = new ChooseRoomPresenter(this);
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
        dialog = ProgressDialog
                .show(this, null, "加载中...", false);
        dialog.show();
    }

    @Override
    public void hideWaiting() {
        if(dialog.isShowing())
            dialog.dismiss();
    }


    @Override
    public void setBuildingInfo(List<Building> buildings) {
        BuildingAdapter adapter = new BuildingAdapter(this,buildings);
        building.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void execError() {

    }
}
