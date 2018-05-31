package com.whucs.energyriver;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

import java.util.ArrayList;
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
    TextView hint,no_msg;

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
        cur_list = new ArrayList<>();

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
        no_msg = (TextView) view.findViewById(R.id.no_msg);
        back = (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(this);
        noticeListView.setRefreshListener(this);

        setTab(cur);
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
                    changeNoticeType(0);
                }
                break;
            case R.id.elec_param:
                if(cur!=1) {
                    changeNoticeType(1);
                }
                break;
            case R.id.envir_param:
                if(cur!=2) {
                    changeNoticeType(2);
                }
                break;
            default:
                break;
        }
    }

    private void changeNoticeType(int type){
        clearTab(cur);
        cur = type;
        setTab(cur);
        pageIndex = 0;
        cur_list.clear();
        cur_list.addAll(noticeList.get(cur));
        if(cur_list.size() == 0) {
            noticeListView.setVisibility(View.GONE);
            no_msg.setVisibility(View.VISIBLE);
        }
        else {
            noticeListView.setVisibility(View.VISIBLE);
            no_msg.setVisibility(View.INVISIBLE);
            if (cur_list.size() == 0 || cur_list.size() < (noticeListView.getLastVisiblePosition() - noticeListView.getFirstVisiblePosition()))
                noticeListView.removeFooterView(refresh_footer);
            else {
                if (noticeListView.getFooterViewsCount() == 0)
                    noticeListView.addFooterView(refresh_footer);
            }
            adapter.notifyDataSetChanged();
        }
        hidePullToRefresh();
    }

    private void clearTab(int position){
        switch (position){
            case 0:
                elec_safety.setTextColor(res.getColor(R.color.white));
                elec_safety.setBackgroundDrawable(res.getDrawable(R.drawable.right_line));
                break;
            case 1:
                elec_param.setTextColor(res.getColor(R.color.white));
                elec_param.setBackgroundDrawable(res.getDrawable(R.drawable.right_line));
                break;
            case 2:
                envir_param.setTextColor(res.getColor(R.color.white));
                envir_param.setBackgroundColor(res.getColor(R.color.baseColor));
                break;
        }
    }

    private void setTab(int position){
        switch (position){
            case 0:
                elec_safety.setTextColor(res.getColor(R.color.baseColor));
                elec_safety.setBackgroundColor(res.getColor(R.color.white));
                break;
            case 1:
                elec_param.setTextColor(res.getColor(R.color.baseColor));
                elec_param.setBackgroundColor(res.getColor(R.color.white));
                break;
            case 2:
                envir_param.setTextColor(res.getColor(R.color.baseColor));
                envir_param.setBackgroundColor(res.getColor(R.color.white));
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

            text = Common.noticeType[type]+"\n"+text;
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
            cur_list.clear();
            cur_list.addAll(noticeList.get(cur));
            if(cur_list.size() == 0) {
                noticeListView.setVisibility(View.GONE);
                no_msg.setVisibility(View.VISIBLE);
            }
            else {
                noticeListView.setVisibility(View.VISIBLE);
                no_msg.setVisibility(View.INVISIBLE);
                //初始化adapter
                if(adapter == null) {
                    adapter = new NoticeAdapter(this, cur_list);
                    noticeListView.setAdapter(adapter);
                    noticeListView.setOnItemClickListener(this);
                }
                //添加/移除footer判断
                if (noticeListView.getFooterViewsCount()>0 && cur_list.size() == 0 || cur_list.size() < (noticeListView.getLastVisiblePosition()-noticeListView.getFirstVisiblePosition()))
                    noticeListView.removeFooterView(refresh_footer);
                else {
                    if (noticeListView.getFooterViewsCount() == 0)
                        noticeListView.addFooterView(refresh_footer);
                }
                adapter.notifyDataSetChanged();
            }
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
            return true;
        }else{
            progressBar.setVisibility(View.GONE);
            hint.setVisibility(View.VISIBLE);
            return false;
        }
    }

    public void hidePullToRefresh(){
        refresh_footer.setPadding(0, -refresh_footer.getMeasuredHeight(), 0, 0);
        hint.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        noticeListView.resetRefreshState();
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
