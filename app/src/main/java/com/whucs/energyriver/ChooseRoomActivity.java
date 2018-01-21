package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.whucs.energyriver.Adapter.BuildingAdapter;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Bean.TreeNode;
import com.whucs.energyriver.Presenter.ChooseRoomPresenter;
import com.whucs.energyriver.View.ChooseRoomView;
import com.whucs.energyriver.Widget.StateSwitchActivity;

/*选择房间*/
public class ChooseRoomActivity extends StateSwitchActivity implements View.OnClickListener,ChooseRoomView,AdapterView.OnItemClickListener {
    ImageView back;
    ListView building;
    Tree tree;
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
        building.setOnItemClickListener(this);

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
        showViewByTag("loading");
    }

    @Override
    public void setBuildingInfo(Tree tree) {
        this.tree = tree;
        BuildingAdapter adapter = new BuildingAdapter(this,tree);
        building.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        showViewByTag("content");
    }

    @Override
    public void execError() {
        showViewByTag("error");
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TreeNode<Building> node = (TreeNode<Building>) adapterView.getAdapter().getItem(i);
        if(node.getData().getRoom()){
            Intent data = new Intent();
            data.putExtra("buildingID",node.getData().getBuildingID());
            data.putExtra("buildingName",tree.getBuildingPath(node.getData(),3));
            setResult(RESULT_OK,data);
            this.finish();
        }else{
            Toast.makeText(this,"请选择房间级别的建筑",Toast.LENGTH_SHORT).show();
        }
    }
}
