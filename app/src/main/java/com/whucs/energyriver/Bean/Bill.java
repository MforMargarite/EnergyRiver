package com.whucs.energyriver.Bean;


public class Bill {
    private Long billID;
    private Long branchID;
    private String updateTime;
    private String yearMonth;
    private Float startRate;
    private Float endRate;
    private Float basicCost;
    private Float averageCost;
    private Float managementCost;
    private Float waterCost;
    private Float lateFee;
    private Float record1;
    private Float record2;
    private Integer state;

    public Long getBillID() {
        return billID;
    }

    public void setBillID(Long billID) {
        this.billID = billID;
    }

    public Long getBranchID() {
        return branchID;
    }

    public void setBranchID(Long branchID) {
        this.branchID = branchID;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Float getStartRate() {
        return startRate;
    }

    public void setStartRate(Float startRate) {
        this.startRate = startRate;
    }

    public Float getEndRate() {
        return endRate;
    }

    public void setEndRate(Float endRate) {
        this.endRate = endRate;
    }

    public Float getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(Float basicCost) {
        this.basicCost = basicCost;
    }

    public Float getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(Float averageCost) {
        this.averageCost = averageCost;
    }

    public Float getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(Float managementCost) {
        this.managementCost = managementCost;
    }

    public Float getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(Float waterCost) {
        this.waterCost = waterCost;
    }

    public Float getLateFee() {
        return lateFee;
    }

    public void setLateFee(Float lateFee) {
        this.lateFee = lateFee;
    }

    public Float getRecord1() {
        return record1;
    }

    public void setRecord1(Float record1) {
        this.record1 = record1;
    }

    public Float getRecord2() {
        return record2;
    }

    public void setRecord2(Float record2) {
        this.record2 = record2;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", branchID=" + branchID +
                ", updateTime='" + updateTime + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                ", startRate=" + startRate +
                ", endRate=" + endRate +
                ", basicCost=" + basicCost +
                ", averageCost=" + averageCost +
                ", managementCost=" + managementCost +
                ", waterCost=" + waterCost +
                ", lateFee=" + lateFee +
                ", record1=" + record1 +
                ", record2=" + record2 +
                ", state=" + state +
                '}';
    }
}
