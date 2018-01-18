package com.whucs.energyriver;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.whucs.energyriver.Adapter.NoticeAdapter;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Presenter.NoticePresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.NoticeView;
import com.whucs.energyriver.Widget.RefreshListView;
import com.whucs.energyriver.Widget.StateSwitchActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificationActivity extends StateSwitchActivity implements View.OnClickListener,NoticeView,AdapterView.OnItemClickListener,RefreshListView.RefreshInterface{
    Resources res;
    ImageView back;
    TextView elec_safety,elec_param,envir_param;
    RefreshListView noticeListView;

    Map<Integer,List<Notice>> noticeList;
    List<Notice>cur_list;
    NoticeAdapter adapter;
    NoticePresenter presenter;
    FrameLayout refresh_footer;
    ProgressBar progressBar;
    TextView hint;

    Map<Integer,Integer>countMap;//记录类型对应的未读消息数量
    int cur=0;//当前选中的类型
    int pageIndex = 0;//当前加载页面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.notification,null);
        initWidget(view);
    }

    private void initWidget(View view){
        iniAdapter(view);
        res = getResources();
        noticeList = new HashMap<>();
        countMap = new HashMap<>();

        //初始化控件
        elec_safety = (TextView) view.findViewById(R.id.elec_safety);//用电安全
        elec_param = (TextView) view.findViewById(R.id.elec_param);//电能参数
        envir_param = (TextView) view.findViewById(R.id.envir_param);//环境参数
        elec_safety.setOnClickListener(this);
        elec_param.setOnClickListener(this);
        envir_param.setOnClickListener(this);

        noticeListView = (RefreshListView) view.findViewById(R.id.notices);
        refresh_footer = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.refresh_footer,null);
        progressBar = (ProgressBar) refresh_footer.findViewById(R.id.progressbar);
        hint = (TextView) refresh_footer.findViewById(R.id.load_finish);
        back = (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(this);
        noticeListView.setRefreshListener(this);

        //按类型获取通知
        presenter = new NoticePresenter(this);
        for(int i=0;i<Common.noticeType.length;i++){
            presenter.getNoticeByType(this,i, pageIndex,20);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                NotificationActivity.this.finish();
                break;
            case R.id.elec_safety:
                if(cur!=0) {
                    pageIndex = 0;
                    cur = 0;
                    cur_list = noticeList.get(cur);
                    adapter = new NoticeAdapter(this,cur_list);
                    noticeListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    if(cur_list == null || cur_list.size() == 0|| (noticeListView.getFirstVisiblePosition()==0 && noticeListView.getCount()==noticeListView.getLastVisiblePosition()+1))
                        noticeListView.removeFooterView(refresh_footer);
                    else {
                        if (noticeListView.getFooterViewsCount() == 0)
                            noticeListView.addFooterView(refresh_footer);
                    }
                }
                hidePullToRefresh();
                break;
            case R.id.elec_param:
                if(cur!=1) {
                    pageIndex = 0;
                    cur = 1;
                    cur_list = noticeList.get(cur);
                    adapter = new NoticeAdapter(this,cur_list);
                    noticeListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    if(cur_list == null || cur_list.size() == 0|| (noticeListView.getFirstVisiblePosition()==0 && noticeListView.getCount()==noticeListView.getLastVisiblePosition()+1))
                        noticeListView.removeFooterView(refresh_footer);
                    else {
                        if (noticeListView.getFooterViewsCount() == 0)
                            noticeListView.addFooterView(refresh_footer);
                    }
                }
                hidePullToRefresh();
                break;
            case R.id.envir_param:
                if(cur!=2) {
                    pageIndex = 0;
                    cur = 2;
                    cur_list = noticeList.get(cur);
                    adapter = new NoticeAdapter(this, cur_list);
                    noticeListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    if (cur_list == null || cur_list.size() == 0 || (noticeListView.getFirstVisiblePosition() == 0 && noticeListView.getCount() == noticeListView.getLastVisiblePosition() + 1))
                        noticeListView.removeFooterView(refresh_footer);
                    else {
                        if (noticeListView.getFooterViewsCount() == 0)
                            noticeListView.addFooterView(refresh_footer);
                    }
                }
                hidePullToRefresh();
                break;
            default:
                break;
        }
    }

    @Override
    public void setNoticeList(List<Notice> notices, int type,int total) {
        if(!noticeList.containsKey(type)) {
            noticeList.put(type, notices);
            countMap.put(type,total);
            String text = "";
            if(total>0)
                text="("+total+")";
            text = Common.types[type]+text;
            switch(type){
                case 0:
                    elec_safety.setText(text);
                    break;
                case 1:
                    elec_param.setText(text);
                    break;
                case 2:
                    envir_param.setText(text);
                    break;
            }
        }
        if(type == cur) {
            cur_list = noticeList.get(cur);
            adapter = new NoticeAdapter(this, cur_list);
            if(noticeListView.getFooterViewsCount() == 0)
                noticeListView.addFooterView(refresh_footer);
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
    public void setNoticeListAppend(List<Notice> notices) {
        cur_list.addAll(notices);
        adapter.notifyDataSetChanged();
        hidePullToRefresh();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Notice notice = (Notice) adapter.getItem(i);
        Intent intent = new Intent(this,NoticeDetailActivity.class);
        intent.putExtra("id",notice.getnID());
        intent.putExtra("time",notice.getnTime());
        intent.putExtra("content",notice.getnContent());
        intent.putExtra("title",notice.getnTitle());
        startActivityForResult(intent,0);
    }

    public void reload(){
        if(presenter == null)
            presenter = new NoticePresenter(this);
        presenter.getNoticeByType(this,cur,pageIndex,20);
    }

    public void refresh() {
        if(presenter == null)
            presenter = new NoticePresenter(this);
        presenter.getNoticeByTypeAppend(this,cur,++pageIndex,20);

    }

    @Override
    public boolean showPullToRefresh(){
        refresh_footer.setPadding(0, 0, 0, 0);
        if((pageIndex+1)*20<countMap.get(cur)) {
            hint.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            refresh();
        }else{
            progressBar.setVisibility(View.GONE);
            hint.setVisibility(View.VISIBLE);
        }
        return true;
    }

    public void hidePullToRefresh(){
        refresh_footer.setPadding(0, -refresh_footer.getMeasuredHeight(), 0, 0);
        hint.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(resultCode == RESULT_OK) {
                    //清空当前页面对应数据
                    noticeList.remove(cur);
                    //重新加载
                    reload();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
