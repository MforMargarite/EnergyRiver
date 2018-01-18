package com.whucs.energyriver;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import com.whucs.energyriver.Adapter.NoticeAdapter;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Presenter.NoticePresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.NoticeView;
import com.whucs.energyriver.Widget.ScrollListView;
import com.whucs.energyriver.Widget.StateSwitchActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NotificationActivity extends StateSwitchActivity implements View.OnClickListener,NoticeView,AdapterView.OnItemClickListener{
    ImageView back;
    ScrollListView noticeListView;
    Resources res;
    ArrayList<ArrayList<Notice>> noticeList,tempList;
    NoticeAdapter adapter;
    NoticePresenter presenter;
    HashMap<String,Integer> countMap;//HashMap<类型,数量>
    int typeNum;//类型数量 用于判断是否加载完所有类型

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.notification,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        res = getResources();
        //初始化控件
        back = (ImageView) view.findViewById(R.id.back);
        noticeListView = (ScrollListView) view.findViewById(R.id.notices);
        back.setOnClickListener(this);
        //初始化按通知类型划分的List
        noticeList = new ArrayList<>();
        tempList = new ArrayList<>();
        for(int i=0;i<Common.noticeType.length;i++){
            Notice noticeTitle = new Notice();
            noticeTitle.setnID(-1L);
            noticeTitle.setnTitle(Common.noticeType[i]);
            noticeTitle.setnType(i);
            ArrayList<Notice> typeList = new ArrayList<>();
            typeList.add(noticeTitle);
            noticeList.add(typeList);
        }
        //按类型获取通知
        typeNum = 0;
        presenter = new NoticePresenter(this);
        for(int i=0;i<Common.noticeType.length;i++){
            presenter.getNoticeByType(this,i);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                NotificationActivity.this.finish();
                break;
        }
    }

    @Override
    public void setNoticeList(List<Notice> notices, int type) {
        ArrayList<Notice> noticeByType = noticeList.get(type);
        noticeByType.addAll(notices);
        noticeList.set(type,noticeByType);

        typeNum ++;
        if(typeNum == Common.noticeType.length){
            tempList.addAll(noticeList);//初始化备用List
            countMap = new HashMap<>();
            for (int i=0;i<noticeList.size();i++) {
                List list = noticeList.get(i);
                countMap.put(Common.noticeType[i],list.size()-1);
            }
            adapter = new NoticeAdapter(this,noticeList,countMap);
            noticeListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            noticeListView.setOnItemClickListener(this);
            showViewByTag("content");
        }
    }

    @Override
    public void execError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        showViewByTag("error");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Notice notice = (Notice) adapter.getItem(i);
        if(adapter.getItemId(i) == -1L){
            //折叠&展开
            ArrayList subList = noticeList.get(notice.getnType());
            if(subList.size() > 1) {
                Notice title = (Notice) subList.get(0);
                ArrayList<Notice> collapse = new ArrayList<>();
                collapse.add(title);
                noticeList.set(notice.getnType(), collapse);
            }else{
                noticeList.set(notice.getnType(), tempList.get(notice.getnType()));
            }
            adapter = new NoticeAdapter(this,noticeList,countMap);
            noticeListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            Intent intent = new Intent(this,NoticeDetailActivity.class);
            intent.putExtra("id",notice.getnID());
            intent.putExtra("time",notice.getnTime());
            intent.putExtra("content",notice.getnContent());
            intent.putExtra("title",notice.getnTitle());
            startActivityForResult(intent,0);
        }
    }

    public void reload(){
        if(presenter == null)
            presenter = new NoticePresenter(this);
        for(int i=0;i<Common.noticeType.length;i++){
            presenter.getNoticeByType(this,i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(resultCode == RESULT_OK) {
                    //清空原有数据
                    typeNum = 0;
                    noticeList.clear();
                    for(int i=0;i<Common.noticeType.length;i++){
                        Notice noticeTitle = new Notice();
                        noticeTitle.setnID(-1L);
                        noticeTitle.setnTitle(Common.noticeType[i]);
                        noticeTitle.setnType(i);
                        ArrayList<Notice> typeList = new ArrayList<>();
                        typeList.add(noticeTitle);
                        noticeList.add(typeList);
                    }
                    //重新加载
                    reload();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
