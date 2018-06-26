package com.whucs.energyriver.Bean;


public class LoopStatus {
    private Boolean openStatus;
    private Long loopID;
    private String loopName;

    public Boolean getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Boolean openStatus) {
        this.openStatus = openStatus;
    }

    public Long getLoopID() {
        return loopID;
    }

    public void setLoopID(Long loopID) {
        this.loopID = loopID;
    }

    public String getLoopName() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName = loopName;
    }

    @Override
    public String toString() {
        return "LoopStatus{" +
                "openStatus=" + openStatus +
                ", loopID=" + loopID +
                ", loopName='" + loopName + '\'' +
                '}';
    }
}
