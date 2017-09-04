package com.whucs.energyriver.Bean;


public class Bill {
    private Long billID;//账单编号
    private String time;//账单时间
    private String money;//账单金额
    private Boolean status;//账单状态

    public Long getBillID() {
        return billID;
    }

    public void setBillID(Long billID) {
        this.billID = billID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", time='" + time + '\'' +
                ", money='" + money + '\'' +
                ", status=" + status +
                '}';
    }
}
