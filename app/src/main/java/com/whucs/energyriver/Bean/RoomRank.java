package com.whucs.energyriver.Bean;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RoomRank {
    private String user;        //用户
    private String location;    //位置
    private String dept;        //部门
    private String money;       //金额

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "RoomRank{" +
                "user='" + user + '\'' +
                ", location='" + location + '\'' +
                ", dept='" + dept + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public void merge(RoomRank rank) throws Exception{
        Field[] fields = rank.getClass().getDeclaredFields();
        Field.setAccessible(fields,true);
        for (Field field: fields) {
            String attr = field.getName();
            attr = attr.substring(0, 1).toUpperCase() + attr.substring(1);
            Method m = rank.getClass().getDeclaredMethod("get" + attr);
            if(m.invoke(rank)!=null){
                String type = field.getGenericType().toString();
                Method model;
                switch (type){
                    case "class java.lang.String":
                        String value_string = (String) m.invoke(rank);
                        System.out.println(value_string);
                        model = this.getClass().getMethod("set"+attr,String.class);
                        model.invoke(this, value_string);
                        break;
                    case "class java.lang.Long":
                        Long value_long = (Long) m.invoke(rank);
                        model = this.getClass().getMethod("set"+attr,Long.class);
                        model.invoke(this, value_long);
                        break;
                    case "class java.lang.Integer":
                        Integer value_int = (Integer) m.invoke(rank);
                        model = this.getClass().getMethod("set"+attr,Integer.class);
                        model.invoke(this, value_int);
                        break;
                }
            }
        }//for
    }
}
