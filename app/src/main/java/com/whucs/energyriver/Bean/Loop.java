package com.whucs.energyriver.Bean;

public class Loop {
    private Long loopID;  //回路ID主键
    private String loopName;
    private String loopCode;
    private Long loopTypeID;//回路类型  外键
    private String loopLevel;
    private String remark;
    private Long upperLoopID;//上级回路 外键
    private String upperLoopName;//上级回路名称
    private Long buildingID;//建筑编号  外键
    private String buildingName;//所在建筑单元
    private String loopHases;//回路相别
    private String loopP;//回路P
    private Boolean openStatus;//开闭状态
    private Long meterID;//测量电表ID  外键
    private String loopRateCurrent;//回路额定电流
    private String realCurrent;//实时电流
    private String realVoltage;//实时电压
    private String realTemp;//实时温度
    private String realLeftCurrent;//实时剩余电流
    private String upCurrent;//电流上限
    private String lowCurrent;//电流下限
    private String upTemp;//温度上限
    private String lowTemp;//温度下限
    private String upLeftCu;//剩余电流上限
    private String lowLeftCu;//剩余电流下限
    private Boolean isAdmitControl;//是否允许控制
    private Boolean isBaseLoop;//是否基础回路
    private Boolean isIntensive;//是否重点监测回路
    private Boolean isConfigMeter;//是否配置电表
    private Boolean isConfigMeasureWay;    //是否配置回路电量计量  0为“假”  1为“真”
    private Boolean isAdmitCut;//是否允许强断电
    private Boolean isAbleCut;//是否可被断电
    private Boolean isMeasure;//是否计量电量
    private String createTime;    //创建时间
    private String modifyTime;    //修改时间
    private Long userID;        //创建人ID
    private String operation;    //操作


    /*2018.1.21 新增 boolean对象  */
    private Boolean ableCut;
    private Boolean admitControl;
    private Boolean admitCut;
    private Boolean baseLoop;
    private Boolean configMeasureWay;
    private Boolean configMeter;
    private Boolean intensive;
    private String isAdmitCutText;
    private String loopType;
    private Boolean measure;

    public String getLoopCode() {
        return loopCode;
    }

    public void setLoopCode(String loopCode) {
        this.loopCode = loopCode;
    }

    public Long getLoopTypeID() {
        return loopTypeID;
    }

    public Boolean getIsConfigMeasureWay() {
        return isConfigMeasureWay;
    }

    public void setIsConfigMeasureWay(Boolean isConfigMeasureWay) {
        this.isConfigMeasureWay = isConfigMeasureWay;
        configMeasureWay = isConfigMeasureWay;
    }

    public void setLoopTypeID(Long loopTypeID) {
        this.loopTypeID = loopTypeID;
    }

    public String getLoopLevel() {
        return loopLevel;
    }

    public void setLoopLevel(String loopLevel) {
        this.loopLevel = loopLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Long buildingID) {
        this.buildingID = buildingID;
    }

    public String getLoopHases() {
        return loopHases;
    }

    public void setLoopHases(String loopHases) {
        this.loopHases = loopHases;
    }

    public String getLoopP() {
        return loopP;
    }

    public void setLoopP(String loopP) {
        this.loopP = loopP;
    }

    public Boolean getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Boolean openStatus) {
        this.openStatus = openStatus;
    }

    public Long getMeterID() {
        return meterID;
    }

    public void setMeterID(Long meterID) {
        this.meterID = meterID;
    }

    public String getLoopRateCurrent() {
        return loopRateCurrent;
    }

    public void setLoopRateCurrent(String loopRateCurrent) {
        this.loopRateCurrent = loopRateCurrent;
    }

    public String getRealCurrent() {
        return realCurrent;
    }

    public void setRealCurrent(String realCurrent) {
        this.realCurrent = realCurrent;
    }

    public String getRealVoltage() {
        return realVoltage;
    }

    public void setRealVoltage(String realVoltage) {
        this.realVoltage = realVoltage;
    }

    public String getRealTemp() {
        return realTemp;
    }

    public void setRealTemp(String realTemp) {
        this.realTemp = realTemp;
    }

    public String getRealLeftCurrent() {
        return realLeftCurrent;
    }

    public void setRealLeftCurrent(String realLeftCurrent) {
        this.realLeftCurrent = realLeftCurrent;
    }

    public String getUpCurrent() {
        return upCurrent;
    }

    public void setUpCurrent(String upCurrent) {
        this.upCurrent = upCurrent;
    }

    public String getLowCurrent() {
        return lowCurrent;
    }

    public void setLowCurrent(String lowCurrent) {
        this.lowCurrent = lowCurrent;
    }

    public String getUpTemp() {
        return upTemp;
    }

    public void setUpTemp(String upTemp) {
        this.upTemp = upTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getUpLeftCu() {
        return upLeftCu;
    }

    public void setUpLeftCu(String upLeftCu) {
        this.upLeftCu = upLeftCu;
    }

    public String getLowLeftCu() {
        return lowLeftCu;
    }

    public void setLowLeftCu(String lowLeftCu) {
        this.lowLeftCu = lowLeftCu;
    }

    public Boolean getIsAdmitControl() {
        return isAdmitControl;
    }

    public void setIsAdmitControl(Boolean isAdmitControl) {
        this.isAdmitControl = isAdmitControl;
        admitControl = isAdmitControl;
    }

    public Boolean getIsBaseLoop() {
        return isBaseLoop;
    }

    public void setIsBaseLoop(Boolean isBaseLoop) {
        this.isBaseLoop = isBaseLoop;
        baseLoop = isBaseLoop;
    }

    public Boolean getIsIntensive() {
        return isIntensive;
    }

    public void setIsIntensive(Boolean isIntensive) {
        this.isIntensive = isIntensive;
        intensive = isIntensive;
    }

    public Long getLoopID() {
        return loopID;
    }

    public void setLoopID(Long loopID) {
        this.loopID = loopID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Boolean getIsConfigMeter() {
        return isConfigMeter;
    }

    public void setIsConfigMeter(Boolean isConfigMeter) {
        this.isConfigMeter = isConfigMeter;
        configMeter = isConfigMeter;
    }

    public Boolean getIsAdmitCut() {
        return isAdmitCut;
    }

    public void setIsAdmitCut(Boolean isAdmitCut) {
        this.isAdmitCut = isAdmitCut;
        admitCut = isAdmitCut;
    }

    public Boolean getIsAbleCut() {
        return isAbleCut;
    }

    public void setIsAbleCut(Boolean isAbleCut) {
        this.isAbleCut = isAbleCut;
        ableCut = isAbleCut;
    }

    public Boolean getIsMeasure() {
        return isMeasure;
    }

    public void setIsMeasure(Boolean isMeasure) {
        this.isMeasure = isMeasure;
        measure = isMeasure;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getLoopName() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName = loopName;
    }

    public Long getUpperLoopID() {
        return upperLoopID;
    }

    public void setUpperLoopID(Long upperLoopID) {
        this.upperLoopID = upperLoopID;
    }

    public String getUpperLoopName() {
        return upperLoopName;
    }

    public void setUpperLoopName(String upperLoopName) {
        this.upperLoopName = upperLoopName;
    }

    public Boolean getAdmitControl() {
        return isAdmitControl;
    }

    public void setAdmitControl(Boolean admitControl) {
        isAdmitControl = admitControl;
    }

    public Boolean getBaseLoop() {
        return isBaseLoop;
    }

    public void setBaseLoop(Boolean baseLoop) {
        isBaseLoop = baseLoop;
    }

    public Boolean getIntensive() {
        return isIntensive;
    }

    public void setIntensive(Boolean intensive) {
        isIntensive = intensive;
    }

    public Boolean getConfigMeter() {
        return isConfigMeter;
    }

    public void setConfigMeter(Boolean configMeter) {
        isConfigMeter = configMeter;
    }

    public Boolean getConfigMeasureWay() {
        return isConfigMeasureWay;
    }

    public void setConfigMeasureWay(Boolean configMeasureWay) {
        isConfigMeasureWay = configMeasureWay;
    }

    public Boolean getAdmitCut() {
        return isAdmitCut;
    }

    public void setAdmitCut(Boolean admitCut) {
        isAdmitCut = admitCut;
        this.admitCut = admitCut;
    }

    public Boolean getAbleCut() {
        return isAbleCut;
    }

    public void setAbleCut(Boolean ableCut) {
        isAbleCut = ableCut;
        this.ableCut = ableCut;
    }

    public Boolean getMeasure() {
        return isMeasure;
    }

    public void setMeasure(Boolean measure) {
        isMeasure = measure;
        this.measure = measure;
    }

    public String getIsAdmitCutText() {
        return isAdmitCutText;
    }

    public void setIsAdmitCutText(String isAdmitCutText) {
        this.isAdmitCutText = isAdmitCutText;
    }

    public String getLoopType() {
        return loopType;
    }

    public void setLoopType(String loopType) {
        this.loopType = loopType;
    }

    @Override
    public String toString() {
        return "Loop{" +
                "loopID=" + loopID +
                ", loopName='" + loopName + '\'' +
                ", loopCode='" + loopCode + '\'' +
                ", loopTypeID=" + loopTypeID +
                ", loopLevel='" + loopLevel + '\'' +
                ", remark='" + remark + '\'' +
                ", upperLoopID=" + upperLoopID +
                ", upperLoopName='" + upperLoopName + '\'' +
                ", buildingID=" + buildingID +
                ", buildingName='" + buildingName + '\'' +
                ", loopHases='" + loopHases + '\'' +
                ", loopP='" + loopP + '\'' +
                ", openStatus=" + openStatus +
                ", meterID=" + meterID +
                ", loopRateCurrent='" + loopRateCurrent + '\'' +
                ", realCurrent='" + realCurrent + '\'' +
                ", realVoltage='" + realVoltage + '\'' +
                ", realTemp='" + realTemp + '\'' +
                ", realLeftCurrent='" + realLeftCurrent + '\'' +
                ", upCurrent='" + upCurrent + '\'' +
                ", lowCurrent='" + lowCurrent + '\'' +
                ", upTemp='" + upTemp + '\'' +
                ", lowTemp='" + lowTemp + '\'' +
                ", upLeftCu='" + upLeftCu + '\'' +
                ", lowLeftCu='" + lowLeftCu + '\'' +
                ", isAdmitControl=" + isAdmitControl +
                ", isBaseLoop=" + isBaseLoop +
                ", isIntensive=" + isIntensive +
                ", isConfigMeter=" + isConfigMeter +
                ", isConfigMeasureWay=" + isConfigMeasureWay +
                ", isAdmitCut=" + isAdmitCut +
                ", isAbleCut=" + isAbleCut +
                ", isMeasure=" + isMeasure +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", userID=" + userID +
                ", operation='" + operation + '\'' +
                ", ableCut=" + ableCut +
                ", admitControl=" + admitControl +
                ", admitCut=" + admitCut +
                ", baseLoop=" + baseLoop +
                ", configMeasureWay=" + configMeasureWay +
                ", configMeter=" + configMeter +
                ", intensive=" + intensive +
                ", isAdmitCutText='" + isAdmitCutText + '\'' +
                ", loopType='" + loopType + '\'' +
                ", measure=" + measure +
                '}';
    }
}


