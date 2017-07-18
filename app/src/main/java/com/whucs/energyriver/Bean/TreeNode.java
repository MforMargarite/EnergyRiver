package com.whucs.energyriver.Bean;


public class TreeNode<T> {
    T data;
    TreeNode parent;//父节点
    boolean isChild;//是叶子节点
    boolean isExpand;//是否展开
    boolean isVisible;//是否可见
    int layer;//层数

    public void setChild(boolean child) {
        isChild = child;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public boolean isChild() {
        return isChild;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", parent=" + parent +
                ", isChild=" + isChild +
                ", isExpand=" + isExpand +
                ", isVisible=" + isVisible +
                ", layer=" + layer +
                '}';
    }
}
