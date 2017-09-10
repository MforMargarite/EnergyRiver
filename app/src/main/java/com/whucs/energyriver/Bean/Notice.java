package com.whucs.energyriver.Bean;


public class Notice {
    private Long nID;
    private Integer nSub;
    private int nType;
    private String nObject;
    private String nTitle;
    private String nContent;
    private String nTime;
    private String nTimeZone;
    private int nState;
    private int nHandle;
    private String nHandleContent;
    private String nHandleTime;
    private Long nRefUserID;
    private String nRefUserName;
    private Long nRefBranchID;
    private Long nRefLoopID;
    private Long nRefBuildingID;
    private Long eID;
    private Long loopID;
    private int isRemind;

    public Long getnID() {
        return nID;
    }

    public void setnID(Long nID) {
        this.nID = nID;
    }

    public Integer getnSub() {
        return nSub;
    }

    public void setnSub(Integer nSub) {
        this.nSub = nSub;
    }

    public int getnType() {
        return nType;
    }

    public void setnType(int nType) {
        this.nType = nType;
    }

    public String getnObject() {
        return nObject;
    }

    public void setnObject(String nObject) {
        this.nObject = nObject;
    }

    public String getnTitle() {
        return nTitle;
    }

    public void setnTitle(String nTitle) {
        this.nTitle = nTitle;
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent;
    }

    public String getnTime() {
        return nTime;
    }

    public void setnTime(String nTime) {
        this.nTime = nTime;
    }

    public String getnTimeZone() {
        return nTimeZone;
    }

    public void setnTimeZone(String nTimeZone) {
        this.nTimeZone = nTimeZone;
    }

    public int getnState() {
        return nState;
    }

    public void setnState(int nState) {
        this.nState = nState;
    }

    public int getnHandle() {
        return nHandle;
    }

    public void setnHandle(int nHandle) {
        this.nHandle = nHandle;
    }

    public String getnHandleContent() {
        return nHandleContent;
    }

    public void setnHandleContent(String nHandleContent) {
        this.nHandleContent = nHandleContent;
    }

    public String getnHandleTime() {
        return nHandleTime;
    }

    public void setnHandleTime(String nHandleTime) {
        this.nHandleTime = nHandleTime;
    }

    public Long getnRefUserID() {
        return nRefUserID;
    }

    public void setnRefUserID(Long nRefUserID) {
        this.nRefUserID = nRefUserID;
    }

    public String getnRefUserName() {
        return nRefUserName;
    }

    public void setnRefUserName(String nRefUserName) {
        this.nRefUserName = nRefUserName;
    }

    public Long getnRefBranchID() {
        return nRefBranchID;
    }

    public void setnRefBranchID(Long nRefBranchID) {
        this.nRefBranchID = nRefBranchID;
    }

    public Long getnRefLoopID() {
        return nRefLoopID;
    }

    public void setnRefLoopID(Long nRefLoopID) {
        this.nRefLoopID = nRefLoopID;
    }

    public Long getnRefBuildingID() {
        return nRefBuildingID;
    }

    public void setnRefBuildingID(Long nRefBuildingID) {
        this.nRefBuildingID = nRefBuildingID;
    }

    public Long geteID() {
        return eID;
    }

    public void seteID(Long eID) {
        this.eID = eID;
    }

    public Long getLoopID() {
        return loopID;
    }

    public void setLoopID(Long loopID) {
        this.loopID = loopID;
    }

    public int getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(int isRemind) {
        this.isRemind = isRemind;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "nID=" + nID +
                ", nSub=" + nSub +
                ", nType=" + nType +
                ", nObject='" + nObject + '\'' +
                ", nTitle='" + nTitle + '\'' +
                ", nContent='" + nContent + '\'' +
                ", nTime='" + nTime + '\'' +
                ", nTimeZone='" + nTimeZone + '\'' +
                ", nState=" + nState +
                ", nHandle=" + nHandle +
                ", nHandleContent='" + nHandleContent + '\'' +
                ", nHandleTime='" + nHandleTime + '\'' +
                ", nRefUserID=" + nRefUserID +
                ", nRefUserName='" + nRefUserName + '\'' +
                ", nRefBranchID=" + nRefBranchID +
                ", nRefLoopID=" + nRefLoopID +
                ", nRefBuildingID=" + nRefBuildingID +
                ", eID=" + eID +
                ", loopID=" + loopID +
                ", isRemind=" + isRemind +
                '}';
    }
}
