package com.whucs.energyriver.Bean;


public class Building {
    private Long buildingID;		//建筑编号
    private String buildingName;	//建筑名称
    private String buildingCode;	//建筑编码
    private Float buildingArea;	    //占地面积
    private String chargeMan;	    //责任人
    private String contactWay;      //联系方式
    private String remark;          //备注
    private Integer buildingLevelID;  //建筑级别ID
    private Integer floorNum;       //楼层数
    private Integer rengShu;        //容纳人数；
    private Long upperBuildingID;   //上级建筑单元ID
    private String createTime;       //创建时间
    private String modifyTime;       //修改时间
    private Long userID;        //创建人ID
    private Integer isPublic;      //是否公共区域,0代表是，1代表否
    private String location;       //所在坐标
    private Integer buildingTypeID;   //建筑性质类型
    private Float lng;            //地图坐标的经度
    private Float lat;             //地图坐标的纬度
    private Boolean isRoom;//是否是房间

    public Long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Long buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Float getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Float buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBuildingLevelID() {
        return buildingLevelID;
    }

    public void setBuildingLevelID(Integer buildingLevelID) {
        this.buildingLevelID = buildingLevelID;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getRengShu() {
        return rengShu;
    }

    public void setRengShu(Integer rengShu) {
        this.rengShu = rengShu;
    }

    public Long getUpperBuildingID() {
        return upperBuildingID;
    }

    public void setUpperBuildingID(Long upperBuildingID) {
        this.upperBuildingID = upperBuildingID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getBuildingTypeID() {
        return buildingTypeID;
    }

    public void setBuildingTypeID(Integer buildingTypeID) {
        this.buildingTypeID = buildingTypeID;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Boolean getRoom() {
        if(isRoom!=null)
            return isRoom;
        return false;
    }

    public void setRoom(Boolean room) {
        isRoom = room;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingID=" + buildingID +
                ", buildingName='" + buildingName + '\'' +
                ", buildingCode='" + buildingCode + '\'' +
                ", buildingArea=" + buildingArea +
                ", chargeMan='" + chargeMan + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", remark='" + remark + '\'' +
                ", buildingLevelID=" + buildingLevelID +
                ", floorNum=" + floorNum +
                ", rengShu=" + rengShu +
                ", upperBuildingID=" + upperBuildingID +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", userID=" + userID +
                ", isPublic=" + isPublic +
                ", location='" + location + '\'' +
                ", buildingTypeID=" + buildingTypeID +
                ", lng=" + lng +
                ", lat=" + lat +
                ", isRoom=" + isRoom +
                '}';
    }
}

