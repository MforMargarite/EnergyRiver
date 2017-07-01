package com.whucs.energyriver.Public;


import com.whucs.energyriver.Bean.Building;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeUtil {
    //将Json字符串转换成树形结构的ArrayList
    public static void JsonToArrList(JSONArray array,long upper,int layer,ArrayList<Building> list) throws Exception{
        int new_layer = layer+1;
        for(int i=0;i<array.length();i++) {
            JSONObject item = array.getJSONObject(i);
                if (item.getInt("upperBuildingID") == upper) {
                    Building building = new Building();
                    building.setBuildingName(item.getString("buildingName"));
                    building.setBuildingID(item.getLong("buildingID"));
                    building.setUpperBuildingID(item.getLong("upperBuildingID"));
                    building.setLayer(layer);
                    list.add(building);
                    array.remove(i);
                    JsonToArrList(array,item.getInt("buildingID"),new_layer,list);
                }
        }//for循环
    }

    //迭代法 根据用户ID获取第一个建筑单元ID(房间ID)
    public static Building getFirstChildID(List<Building> list){
        Building upper = list.get(0);
        //long upper = Long.parseLong(list.get(0).get("buildingID").toString());
        Building cur;
        while((cur = containsUpper(list,upper))!=upper){
            upper = cur;
        }
        return upper;
    }

    private static Building containsUpper(List<Building>list,Building upper){
        for (Building building: list) {
            if(building.getUpperBuildingID() == upper.getBuildingID())
                return building;
        }
        return upper;
    }
}

