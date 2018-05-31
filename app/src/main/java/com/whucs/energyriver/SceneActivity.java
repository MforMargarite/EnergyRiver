package com.whucs.energyriver;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Adapter.SceneManageAdapter;
import com.whucs.energyriver.Bean.Scene;
import com.whucs.energyriver.Presenter.ScenePresenter;
import com.whucs.energyriver.View.SceneView;
import com.whucs.energyriver.Widget.StateSwitchActivity;

import java.util.ArrayList;
import java.util.List;

public class SceneActivity extends StateSwitchActivity implements View.OnClickListener,SceneView,AdapterView.OnItemLongClickListener,PopupMenu.OnMenuItemClickListener{
    ImageView back,add;
    TextView title;/*,add*/
    ListView scene_list;
    Resources res;
    List<Scene> scenes;
    SceneManageAdapter adapter;
    ScenePresenter presenter;
    PopupMenu popupMenu;
    PopupWindow oneMoreEnsure;
    View view,curView;
    int type;//1--场景 2--组控
    int curPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent!=null)
            type = intent.getIntExtra("type",1);
        if(view == null) {
            view = LayoutInflater.from(this).inflate(R.layout.scene, null);
            initWidget(view);
        }
    }

    private void initWidget(View view){
        iniAdapter(view);
        res = getResources();
        scenes = new ArrayList<>();

        back = (ImageView) view.findViewById(R.id.back);
        title = (TextView) view.findViewById(R.id.title);
       // add = (TextView) view.findViewById(R.id.add);
        add = (ImageView) view.findViewById(R.id.add);
        scene_list = (ListView) view.findViewById(R.id.scene_list);

        back.setOnClickListener(this);
        add.setOnClickListener(this);

        presenter = new ScenePresenter(this);
        if(type == 1)
            presenter.getSceneList(this);
        else
            presenter.getGroupControlList(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter == null)
            presenter = new ScenePresenter(this);
        if(type == 1)
            presenter.getSceneList(this);
        else
            presenter.getGroupControlList(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                this.finish();
                break;
            case R.id.add:
                Intent intent = new Intent(this,AddSceneActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
            case R.id.delete:
                //执行删除动作
                presenter.delSceneById(SceneActivity.this,curView,curPosition);
                curView.setSelected(false);
                curView = null;
                curPosition = -1;
                break;
            case R.id.cancel:
                break;
        }
    }

    @Override
    public void setSceneList(List<Scene> sceneList) {
        scenes.clear();
        scenes.addAll(sceneList);
        if(adapter == null){
            adapter = new SceneManageAdapter(this,scenes);
            scene_list.setAdapter(adapter);
            scene_list.setOnItemLongClickListener(this);
        }
        adapter.notifyDataSetChanged();
        showViewByTag("content");
    }

    @Override
    public void execError() {
        showViewByTag("error");
    }

    @Override
    public void delSuccess(int position) {
        scenes.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void delError() {
        Toast.makeText(this,"删除场景或组控模式失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        //弹出菜单 选择删除
        curView = view;
        curView.setSelected(true);
        curPosition = i;
        showDelMenu(view);
        return true;
    }

    //使用PopupMenu实现弹出菜单
    private void showDelMenu(View view){
        popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.del_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return oneMoreEnsure();
    }

    private boolean oneMoreEnsure(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.del_pop_window, null);
        // 设置按钮的点击事件
        TextView delete = (TextView) contentView.findViewById(R.id.delete);
        TextView cancel = (TextView) contentView.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        oneMoreEnsure = new PopupWindow(contentView,
                PercentRelativeLayout.LayoutParams.MATCH_PARENT, PercentRelativeLayout.LayoutParams.MATCH_PARENT, true);
        oneMoreEnsure.setTouchable(true);
        oneMoreEnsure.showAtLocation(view, Gravity.CENTER_VERTICAL,0,0);
        return true;
    }
}
