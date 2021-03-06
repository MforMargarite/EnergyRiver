package com.whucs.energyriver.Bean;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ACAlter {
    Long acaID;
    Long eID;
    Float SetTemp;
    int mode;
    int speed;
    int EquipmentStatus;
    Float MaxTemp;
    Float MinTemp;
    String UpdateTime;
    int ResponseStatus;

    public Long getAcaID() {
        return acaID;
    }

    public void setAcaID(Long acaID) {
        this.acaID = acaID;
    }

    public Long geteID() {
        return eID;
    }

    public void seteID(Long eID) {
        this.eID = eID;
    }

    public Float getSetTemp() {
        return SetTemp;
    }

    public void setSetTemp(Float setTemp) {
        SetTemp = setTemp;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEquipmentStatus() {
        return EquipmentStatus;
    }

    public void setEquipmentStatus(int equipmentStatus) {
        EquipmentStatus = equipmentStatus;
    }

    public Float getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(Float maxTemp) {
        MaxTemp = maxTemp;
    }

    public Float getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(Float minTemp) {
        MinTemp = minTemp;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public int getResponseStatus() {
        return ResponseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        ResponseStatus = responseStatus;
    }

    @Override
    public String toString() {
        return "ACAlter{" +
                "acaID=" + acaID +
                ", eID=" + eID +
                ", SetTemp=" + SetTemp +
                ", mode=" + mode +
                ", speed=" + speed +
                ", EquipmentStatus=" + EquipmentStatus +
                ", MaxTemp=" + MaxTemp +
                ", MinTemp=" + MinTemp +
                ", UpdateTime='" + UpdateTime + '\'' +
                ", ResponseStatus=" + ResponseStatus +
                '}';
    }

    public void clear(){
        try {
            Field[] attrs = this.getClass().getDeclaredFields();
            for (Field attr : attrs) {
                String firstUpper = attr.toString().substring(0, 1).toUpperCase();
                String concat = firstUpper + attr.toString().substring(1);
                Method m = this.getClass().getDeclaredMethod("set" + concat);
                switch (attr.getType().toString()) {
                    case "class java.lang.Integer":
                        m.invoke(0, attr);
                        break;
                    default: m.invoke(null, attr);
                        break;
                }
            }
        }catch (Exception e){
            acaID = null;eID = null;
            SetTemp = null;MaxTemp = null;MinTemp = null;
            speed = 0;mode = 0;EquipmentStatus = 0;ResponseStatus = 0;
            UpdateTime = null;
        }
    }
}
