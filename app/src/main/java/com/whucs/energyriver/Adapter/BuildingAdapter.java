package com.whucs.energyriver.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.whucs.energyriver.Bean.Building;
import com.whucs.energyriver.Bean.Tree;
import com.whucs.energyriver.Bean.TreeNode;
import com.whucs.energyriver.R;
import java.util.List;


public class BuildingAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private Tree tree;
    private List<TreeNode>treeNodes;
    private Drawable expand,collapse;
    private int padding;

    public BuildingAdapter(Context context, Tree tree){
        this.context = context;
        this.tree = tree;
        this.treeNodes = tree.getExpandOrCollapseTree();
        this.expand = context.getResources().getDrawable(R.mipmap.expand);
        this.collapse = context.getResources().getDrawable(R.mipmap.collapse);
        this.padding = (int)context.getResources().getDimension(R.dimen.building_list_padding);
    }

    @Override
    public int getCount() {
        return treeNodes.size();
    }

    @Override
    public Object getItem(int i) {
        return treeNodes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.tree_item,null);
        TextView content = (TextView) view.findViewById(R.id.content);
        ImageView toggle = (ImageView) view.findViewById(R.id.toggle);
        LinearLayout wrapper = (LinearLayout) view.findViewById(R.id.wrapper);
        toggle.setOnClickListener(this);
        TreeNode<Building>node = treeNodes.get(i);
        if (!node.isChild()) {
            //加载展开/折叠按钮
            toggle.setVisibility(View.VISIBLE);
            toggle.setTag(node);
            initToggle(toggle,node);
        }else {
            toggle.setVisibility(View.INVISIBLE);
        }
        wrapper.setPadding(node.getLayer()*32,padding,0,padding);
        content.setText(node.getData().getBuildingName());
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.toggle:
                TreeNode node = (TreeNode) view.getTag();
                ImageView toggle  = (ImageView)view;
                if (node.isExpand()) {
                    toggle.setImageDrawable(collapse);
                    tree.collapse(node);
                } else {
                    toggle.setImageDrawable(expand);
                    node.setExpand(true);
                    tree.expand(node);
                }
                treeNodes = tree.getExpandOrCollapseTree();
                notifyDataSetInvalidated();
                break;
        }
    }

    private void initToggle(ImageView toggle,TreeNode node){
        if(node.isExpand()){
            toggle.setImageDrawable(expand);
        }else
            toggle.setImageDrawable(collapse);
    }

    class ViewHolder{
        TextView content;
        ImageView toggle;
        LinearLayout wrapper;
    }

}
