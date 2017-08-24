package com.whucs.energyriver.Bean;


public class ACCollect {
    Long accID;
    Long eID;
    String setTemp;
    String roomTemp;
    int mode;
    int speed;
    int EquipmentStatus;
    String maxTemp;
    String minTemp;
    Long GatewayId;
    String UpdateTime;

    public Long getAccID() {
        return accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }

    public Long geteID() {
        return eID;
    }

    public void seteID(Long eID) {
        this.eID = eID;
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

    public String getSetTemp() {
        return setTemp;
    }

    public void setSetTemp(String setTemp) {
        this.setTemp = setTemp;
    }

    public String getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(String roomTemp) {
        this.roomTemp = roomTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public String toString() {
        return "ACCollect{" +
                "accID=" + accID +
                ", eID=" + eID +
                ", setTemp='" + setTemp + '\'' +
                ", roomTemp='" + roomTemp + '\'' +
                ", mode=" + mode +
                ", speed=" + speed +
                ", EquipmentStatus=" + EquipmentStatus +
                ", maxTemp='" + maxTemp + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", GatewayId=" + GatewayId +
                ", UpdateTime='" + UpdateTime + '\'' +
                '}';
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public Long getGatewayId() {
        return GatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        GatewayId = gatewayId;
    }

    /*
    public void unset()throws Exception{
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method:methods) {
            if(method.toString().contains("set")){
                method.invoke(this,null);
            }
        }
    }
*/
}
