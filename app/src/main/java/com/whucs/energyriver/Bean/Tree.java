package com.whucs.energyriver.Bean;


import java.util.ArrayList;
import java.util.List;

public class Tree {
    List<TreeNode>tree;

    //构造建筑的树形列表
    public Tree(List<Building> list){
        tree = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            TreeNode<Building> node = new TreeNode<>();
            node.setData(list.get(i));
            node.setLayer(0);
            node.setVisible(false);
            int pos = -1;
            for (int index=0;index<tree.size();index++) {
                TreeNode<Building> parent = tree.get(index);
                if (node.getData().getUpperBuildingID() == parent.getData().getBuildingID()) {
                    node.setParent(parent);
                    node.setChild(true);
                    node.setLayer(parent.getLayer()+1);
                    updateLayer(node);
                    parent.setChild(false);
                    tree.set(index,parent);
                    pos = index;
                    break;
                }
            }
            tree.add((pos+1),node);
        }
        sort();
        setExpandDepth(2);
    }

    private void sort(){
        for(int each=0;each<tree.size();each++){
            TreeNode<Building> node = tree.get(each);
            if(node.getParent()==null)
                for (int index = 0; index < tree.size(); index++) {
                    TreeNode<Building> parent = tree.get(index);
                    if (node.getData().getUpperBuildingID() == parent.getData().getBuildingID()) {
                        node.setParent(parent);
                        node.setLayer(parent.getLayer() + 1);
                        tree.set(each,node);
                        updateLayer(node);
                        break;
                    }
                }
            }
        }

    private void setExpandDepth(int level){
        for (TreeNode node:tree) {
            if(node.getLayer()<level) {
                node.setVisible(true);
                if (node.getLayer() != level-1)
                    node.setExpand(true);
            }
        }
    }

    private void updateLayer(TreeNode node){
        for (int i=0;i<tree.size();i++) {
            TreeNode sub = tree.get(i);
            if(sub.getParent() == node){
                sub.setLayer(node.getLayer()+1);
                tree.set(i,sub);
                updateLayer(sub);
            }
        }
    }

    //展开某个树节点
    public void expand(TreeNode treeNode){
        if(!treeNode.isChild()) {
            tree.get(tree.indexOf(treeNode)).setExpand(true);
            for (TreeNode node:tree) {
                if(node.getParent() == treeNode){
                    node.setVisible(true);
                }
            }
        }
    }

    //收拢某个树节点
    public void collapse(TreeNode treeNode){
        if(!treeNode.isChild()) {
            tree.get(tree.indexOf(treeNode)).setExpand(false);
            for (TreeNode node:tree) {
                if (node.getParent() == treeNode) {
                    node.setVisible(false);
                    collapse(node);
                }
            }
        }
    }

    public List<TreeNode> getExpandOrCollapseTree(){
        List<TreeNode>nodes = new ArrayList<>();
        for (TreeNode node:tree) {
            if(node.isVisible())
                nodes.add(node);
        }
        return nodes;
    }

}
