package com.whucs.energyriver.Public;


import com.whucs.energyriver.Bean.Building;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeUtil {

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

