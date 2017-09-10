package com.whucs.energyriver.Bean;


import java.util.List;

public class Scene {
    private Long ID;            //场景ID
    private Long buildingID;    //所属建筑ID
    private String name;        //场景名称
    private Boolean status;     //场景状态
    private List<Loop> loopList;//对应回路列表

    //定时操作 暂时没有想好
/*
    private String startTime;
    private String endTime;
    private int[] period;       //例:{0,1}表示周日、周一
*/

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Long buildingID) {
        this.buildingID = buildingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Loop> getLoopList() {
        return loopList;
    }

    public void setLoopList(List<Loop> loopList) {
        this.loopList = loopList;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "ID=" + ID +
                ", buildingID=" + buildingID +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", loopList=" + loopList +
                '}';
    }
}
