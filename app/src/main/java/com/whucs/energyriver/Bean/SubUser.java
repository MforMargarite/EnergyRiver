package com.whucs.energyriver.Bean;


public class SubUser {
    private long userID;
    private String userName;
    private String initPassword;
    private String workNum;
    private String department;
    private String job;
    private String mobile;
    private String identity;
    private long creatorID;
    private String createTime;
    private long buildingID;
    private String headImg;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInitPassword() {
        return initPassword;
    }

    public void setInitPassword(String initPassword) {
        this.initPassword = initPassword;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public long getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(long creatorID) {
        this.creatorID = creatorID;
    }

    public long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(long buildingID) {
        this.buildingID = buildingID;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "SubUser{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", initPassword='" + initPassword + '\'' +
                ", workNum=" + workNum +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", mobile='" + mobile + '\'' +
                ", identity='" + identity + '\'' +
                ", creatorID=" + creatorID +
                ", createTime='" + createTime + '\'' +
                ", buildingID=" + buildingID +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
