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
    private List<Notice> noticeResult;
    private SimpleDateFormat format;
    public NoticeAdapter(Context context,List<Notice> noticeList) {
        this.context = context;
        this.noticeResult = noticeList;
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
        view = LayoutInflater.from(context).inflate(R.layout.notice_detail_item, null);
        TextView noticeContent = (TextView) view.findViewById(R.id.notice_content);
        TextView noticeTime = (TextView) view.findViewById(R.id.notice_time);
        noticeContent.setText(notice.getnTitle());
        noticeTime.setText(format.format(new Date(Long.valueOf(notice.getnTime()))));
        return view;
    }

}
