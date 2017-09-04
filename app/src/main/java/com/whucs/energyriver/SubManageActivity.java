package com.whucs.energyriver;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Adapter.SubManageAdapter;
import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.Presenter.SubUserManagePresenter;
import com.whucs.energyriver.Public.Layout;
import com.whucs.energyriver.View.SubUserView;
import com.whucs.energyriver.Widget.ScrollListView;
import com.whucs.energyriver.Widget.StateSwitchActivity;

import java.util.List;


public class SubManageActivity extends StateSwitchActivity implements View.OnClickListener,SubUserView,AdapterView.OnItemClickListener{
    ImageView back;
    ImageView add;//添加子用户
    ScrollListView subs;//子用户列表
    SubUserManagePresenter presenter;
    SubManageAdapter adapter;
    Resources res;
    private static final int ADD_USER = 0;
    private static final int UPDATE_USER = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.sub_manage,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        res = getResources();
        //添加空页面
        View empty = LayoutInflater.from(this).inflate(R.layout.empty,null);
        TextView title = (TextView) empty.findViewById(R.id.title);
        title.setText(res.getText(R.string.sub_manage));
        TextView content = (TextView)empty.findViewById(R.id.content);
        content.setText(res.getText(R.string.no_sub_user));
        ImageView back = (ImageView) empty.findViewById(R.id.back);
        back.setOnClickListener(this);
        addState("empty",empty);
        //初始化控件
        back = (ImageView) view.findViewById(R.id.back);
        subs = (ScrollListView) view.findViewById(R.id.subs);
        add = (ImageView) view.findViewById(R.id.add);
        back.setOnClickListener(this);
        add.setOnClickListener(this);

        presenter = new SubUserManagePresenter(this);
        presenter.getSubUser(this);
    }



    @Override
    public void reload(){
        if(presenter == null)
            presenter = new SubUserManagePresenter(this);
        presenter.getSubUser(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                SubManageActivity.this.finish();
                break;
            case R.id.add:
                Intent intent = new Intent(SubManageActivity.this,AddSubActivity.class);
                startActivityForResult(intent,0);
                break;
            default:
                break;
        }
    }

    @Override
    public void getSubSuccess(List<SubUser> userList) {
        if (userList == null || userList.size() == 0)
            showViewByTag("empty");
        else {
            adapter = new SubManageAdapter(this, userList);
            subs.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            subs.setOnItemClickListener(this);
            showViewByTag("content");
        }
    }

    @Override
    public void execError(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
        showViewByTag("error");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,EditSubActivity.class);
        SubUser user = (SubUser) adapter.getItem(i);
        intent.putExtra("userID",user.getUserID());
        intent.putExtra("userName",user.getUserName());
        intent.putExtra("workNum",user.getWorkNum());
        intent.putExtra("department",user.getDepartment());
        intent.putExtra("job",user.getJob());
        intent.putExtra("mobile",user.getMobile());
        intent.putExtra("IDNum",user.getIdentity());
        intent.putExtra("buildingID",user.getBuildingID());
        //intent.putExtra("password",user.getInitPassword());
        startActivityForResult(intent,UPDATE_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_USER:
                if(resultCode == 1)
                    reload();
                break;
            case UPDATE_USER:
                if(resultCode == 1)
                    reload();
                break;
        }
    }
}
