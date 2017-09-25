package com.whucs.energyriver.Bean;


public class Percent {
    private Float total;
    private String loopTypeName;
    private Integer loopTypeID;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getLoopTypeName() {
        return loopTypeName;
    }

    public void setLoopTypeName(String loopTypeName) {
        this.loopTypeName = loopTypeName;
    }

    public Integer getLoopTypeID() {
        return loopTypeID;
    }

    public void setLoopTypeID(Integer loopTypeID) {
        this.loopTypeID = loopTypeID;
    }

    @Override
    public String toString() {
        return "Percent{" +
                "total=" + total +
                ", loopTypeName='" + loopTypeName + '\'' +
                ", loopTypeID=" + loopTypeID +
                '}';
    }
}
