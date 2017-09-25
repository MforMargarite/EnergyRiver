package com.whucs.energyriver.Bean;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Rank {
    private String name;        //用户
    private String money;       //金额
    private String percent;      //比例
    private Integer upOrDown;       //变化趋势 0--上升 1--下降 2--不变

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Integer getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(Integer upOrDown) {
        this.upOrDown = upOrDown;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", percent=" + percent +
                ", upOrDown=" + upOrDown +
                '}';
    }

    public void merge(Rank rank) throws Exception{
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
                    case "class java.lang.Float":
                        Float value_long = (Float) m.invoke(rank);
                        model = this.getClass().getMethod("set"+attr,Float.class);
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
