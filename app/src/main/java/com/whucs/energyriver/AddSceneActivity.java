package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.whucs.energyriver.Adapter.ControlLoopAdapter;
import com.whucs.energyriver.Bean.LoopStatus;
import com.whucs.energyriver.Presenter.ScenePresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.ValidStandard;
import com.whucs.energyriver.View.SceneEditView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddSceneActivity extends AppCompatActivity implements SceneEditView,View.OnClickListener{
    ListView loop_list;
    ImageView back;
    TextView save,title;
    EditText scene_name_input;
    int type; //1-场景 2-组控
    HashMap<String,Object> post_scene;
    ArrayList<LoopStatus> loops;
    ControlLoopAdapter adapter;
    ScenePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene_edit);
        Intent intent = getIntent();
        if(intent!=null)
            type = intent.getIntExtra("type",1);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        save = (TextView) findViewById(R.id.save);
        loop_list = (ListView) findViewById(R.id.loop_list);
        title = (TextView) findViewById(R.id.title);
        scene_name_input = (EditText) findViewById(R.id.scene_name_input);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        if(type == 2)
            title.setText(getResources().getText(R.string.add_group));

        post_scene = new HashMap<>();
        loops = new ArrayList<>();
        post_scene.put("UserId", Common.getID(this));
        post_scene.put("BranchId",Common.getBranchID(this));
        post_scene.put("SceneType",type);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                AddSceneActivity.this.finish();
                break;
            case R.id.save:
                if(!ValidStandard.isLegal(scene_name_input))
                    Toast.makeText(AddSceneActivity.this,"场景名称不能为空",Toast.LENGTH_SHORT).show();
                else if(adapter.getControlLoops().size() == 0) {
                    Toast.makeText(AddSceneActivity.this,"请至少选择一条可控回路",Toast.LENGTH_SHORT).show();
                }else{
                    post_scene.put("sceneName", scene_name_input.getText().toString());
                    post_scene.put("info", adapter.getControlLoops());
                    String final_post =  new Gson().toJson(post_scene);
                    presenter.addScene(AddSceneActivity.this, final_post);
                    Log.e("what", final_post);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setControlLoops(List<LoopStatus> controlLoops) {
        loops.clear();
        loops.addAll(controlLoops);
        if(adapter == null){
            adapter = new ControlLoopAdapter(this,type,loops);
            loop_list.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void execError(String hint) {
        Toast.makeText(this,hint,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postSuccess() {
        String typeStr = type==1?"场景":"组控";
        Toast.makeText(this,"新增"+typeStr+"成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("type",type);
        this.setResult(RESULT_OK,intent);
        this.finish();
    }

    @Override
    public void postError() {
        String typeStr = type==1?"场景":"组控";
        Toast.makeText(this,"新增"+typeStr+"失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter == null)
            presenter = new ScenePresenter(this);
        presenter.getControlLoops(this);
    }
}
