package com.whucs.energyriver.Public;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeUtil {
    public void JsonToArrList(JSONArray array,long upper,int layer,ArrayList<HashMap<String,Object>> list) throws Exception{
        int new_layer = layer+1;
        for(int i=0;i<array.length();i++) {
            JSONObject item = array.getJSONObject(i);
                if (item.getInt("upperBuildingID") == upper) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("buildingName", item.get("buildingName"));
                    map.put("buildingID", item.get("buildingID"));
                    map.put("upperBuildingID", upper);
                    map.put("layer",layer);
                    list.add(map);
                    array.remove(i);
                    JsonToArrList(array,item.getInt("buildingID"),new_layer,list);
                }
        }//for循环
    }
/*
    public void JsonToMap(JSONArray array,long upper,int layer,HashMap<Long,ArrayList<HashMap<String,Object>>> allMap) throws Exception{
        int new_layer = layer+1;
        for(int i=0;i<array.length();i++) {
            JSONObject item = array.getJSONObject(i);
            if (item.getLong("upperBuildingID") == upper) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("buildingName", item.get("buildingName"));
                map.put("buildingID", item.get("buildingID"));
                map.put("upperBuildingID", upper);
                map.put("layer",layer);
                if(allMap.containsKey(upper)){
                    allMap.get(upper).add(map);
                }else{
                    ArrayList<HashMap<String,Object>>list = new ArrayList<>();
                    list.add(map);
                    allMap.put(upper,list);
                }
                array.remove(i);
                JsonToMap(array,item.getInt("buildingID"),new_layer,allMap);
            }
        }//for循环
    }*/
}

