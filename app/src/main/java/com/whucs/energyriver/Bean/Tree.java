package com.whucs.energyriver.Bean;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    List<TreeNode>tree;//序列化的树
    TreeNode root;
    TreeNode<Building> parentNode;
    boolean isContains;

    //n叉树
    public Tree(List<Building> list){
        tree = new ArrayList<>();
        root = null;
        parentNode = null;
        for (int i=0;i<list.size();i++) {
            TreeNode<Building> node = new TreeNode<>();
            node.setData(list.get(i));
            node.setChild(true);
            node.setVisible(false);
            findParentInTree(node,list);
        }
        depthTravel(root,0);
        setExpandDepth(2);
    }

    //深度优先遍历n叉树 将树变为一序列存至List中
    private void depthTravel(TreeNode<Building> node,int level){
        node.setLayer(level);
        tree.add(node);
        int new_level = level+1;
        if(!node.isChild()){
            List<TreeNode>children = node.getChildren();
            for (TreeNode item:children)
                depthTravel(item,new_level);
        }
    }

    //递归寻找node的父节点
    private void findParentInTree(TreeNode<Building> node,List<Building> list){
        if(node.getData().getUpperBuildingID() == null && root == null) {
            node.setChild(false);
            node.setVisible(true);
            root = node;
        }else {
            isContains = false;
            if(root!=null)
                containsNode(node,root);
            if(!isContains) {
                for (Building build : list) {
                    if (build.getBuildingID().equals(node.getData().getUpperBuildingID())) {
                        if(root!=null)
                            findNodeByBuild(root, build);
                        if (parentNode == null) {
                            parentNode = new TreeNode<>();
                            parentNode.setData(build);
                            parentNode.setVisible(false);
                            parentNode.setChild(false);
                            findParentInTree(parentNode, list);
                            parentNode = null;
                            findNodeByBuild(root, build);
                        }
                        List<TreeNode> childList = parentNode.getChildren();
                        if (childList == null)
                            childList = new ArrayList<>();
                        node.setParent(parentNode);
                        childList.add(node);
                        parentNode.setChildren(childList);
                        parentNode.setChild(false);
                        if (parentNode != root)
                            saveParent(parentNode, parentNode.getParent());
                        break;
                    }
                }
            }//不存在该节点
        }//else
    }

    private void saveParent(TreeNode node,TreeNode parent){
        //递归更新
        List<TreeNode> childList = parent.getChildren();
        if (childList == null)
            childList = new ArrayList<>();
        int index = childList.indexOf(node);
        node.setParent(parent);
        if(index == -1)
            childList.add(node);
        else
            childList.set(index,node);
        parent.setChildren(childList);
        parent.setChild(false);
        if(parent != root){
            saveParent(parent,parent.getParent());
        }
    }

    private void findNodeByBuild(TreeNode<Building> begin,Building build){
        if(begin.getData().getBuildingID() == build.getBuildingID()){
            parentNode = begin;
        }else {
            List<TreeNode>list =  begin.getChildren();
            if(list!=null) {
                for (TreeNode item : list) {
                    findNodeByBuild(item, build);
                }
            }
        }
    }

    private void containsNode(TreeNode<Building> node,TreeNode<Building> root){
        if(root.getChildren()!=null) {
            for (TreeNode<Building> item : root.getChildren()) {
                if (item.getData().getBuildingID() == node.getData().getBuildingID()) {
                    isContains = true;
                    break;
                }
            }
        }
        if(!isContains) {
            List<TreeNode>list = root.getChildren();
            if(list!=null) {
                for (TreeNode<Building> item : list) {
                    containsNode(node, item);
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

    public String getBuildingPath(Building building,int layer){
        String builder = building.getBuildingName();
        do {
            parentNode = null;
            findNodeByBuild(root, building);
            parentNode = parentNode.getParent();
            if (parentNode != null) {
                builder = parentNode.getData().getBuildingName() + " - " + builder;
                building = parentNode.getData();
                layer--;
            }
        }while(layer>1 && parentNode!=null);
        return builder;
    }

}
