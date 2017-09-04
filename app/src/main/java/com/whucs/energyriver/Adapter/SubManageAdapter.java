package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.whucs.energyriver.Bean.SubUser;
import com.whucs.energyriver.R;
import com.whucs.energyriver.Widget.ScrollListView;

import java.util.ArrayList;
import java.util.List;


public class SubManageAdapter extends BaseAdapter{
    private Context context;
    private List<SubUser> userList;
    private Resources res;

    public SubManageAdapter(Context context, List<SubUser> userList){
        this.context = context;
        this.res = context.getResources();
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return userList.get(i).getUserID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        SubUser subUser = userList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sub_user_item, null);
            holder = new ViewHolder();
            holder.username = (TextView) view.findViewById(R.id.username);
            holder.avatar = (ImageView) view.findViewById(R.id.avatar);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.username.setText(subUser.getUserName());
            //holder.avatar.setImageBitmap();
        //}
        return view;
    }



    class ViewHolder{
        TextView username;
        ImageView avatar;
    }

}
