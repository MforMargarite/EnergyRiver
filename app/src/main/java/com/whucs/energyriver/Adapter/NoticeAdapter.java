package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.whucs.energyriver.Bean.Notice;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class NoticeAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Notice> noticeResult;
    private HashMap<String,Integer> countMap;
    private SimpleDateFormat format;
    public NoticeAdapter(Context context,ArrayList<ArrayList<Notice>> noticeList,HashMap<String,Integer>countMap) {
        this.context = context;
        this.countMap = countMap;
        //初始化事件通知列表
        noticeResult = new ArrayList<>();
        for (int i=0;i<noticeList.size();i++) {
            ArrayList list = noticeList.get(i);
            noticeResult.addAll(list);
        }
        format = new SimpleDateFormat("yyyy-MM-dd\nHH:mm");
    }

    @Override
    public int getCount() {
        return noticeResult.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeResult.get(i);
    }

    @Override
    public long getItemId(int i) {
        return noticeResult.get(i).getnID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Notice notice = noticeResult.get(i);
        if(notice.getnID().equals(-1L) ){
            //类型
            view = LayoutInflater.from(context).inflate(R.layout.notice_type_item,null);
            TextView noticeText = (TextView) view.findViewById(R.id.notice_text);
            TextView noticeNum = (TextView) view.findViewById(R.id.notice_num);
            noticeText.setText(notice.getnTitle());
            int count = countMap.get(notice.getnTitle());
            if(count == 0)
                noticeNum.setVisibility(View.GONE);
            else
                noticeNum.setText(count +" 未读");
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.notice_detail_item, null);
            TextView noticeContent = (TextView) view.findViewById(R.id.notice_content);
            TextView noticeTime = (TextView) view.findViewById(R.id.notice_time);
            noticeContent.setText(notice.getnTitle());
            noticeTime.setText(format.format(new Date(Long.valueOf(notice.getnTime()))));
        }
        return view;
    }

}
