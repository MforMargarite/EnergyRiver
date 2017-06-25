package com.whucs.energyriver;

import com.whucs.energyriver.Public.TreeUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() throws Exception{
        TreeUtil util = new TreeUtil();
        String jsonString = "[{\"buildingArea\":1500,\"buildingCode\":\"420106002\",\"buildingID\":1,\"buildingLevelID\":1,\"buildingName\":\"武汉大学本部\",\"buildingTypeID\":1,\"chargeMan\":\"窦校长\",\"contactWay\":\"48765531\",\"createTime\":null,\"floorNum\":80,\"isPublic\":0,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"湖北省/武汉市/武昌区/430072\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":2300,\"upperBuildingID\":75,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001001\",\"buildingID\":2,\"buildingLevelID\":null,\"buildingName\":\"计算机学院大楼\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"68770001\",\"createTime\":null,\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":230,\"upperBuildingID\":1,\"userID\":1},{\"buildingArea\":300,\"buildingCode\":\"420106001001001\",\"buildingID\":3,\"buildingLevelID\":1,\"buildingName\":\"A区\",\"buildingTypeID\":1,\"chargeMan\":\"窦校长\",\"contactWay\":\"1262568\",\"createTime\":null,\"floorNum\":8,\"isPublic\":0,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":1000,\"upperBuildingID\":2,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001001002\",\"buildingID\":4,\"buildingLevelID\":null,\"buildingName\":\"B区\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"\",\"createTime\":null,\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":23,\"upperBuildingID\":2,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001\",\"buildingID\":5,\"buildingLevelID\":null,\"buildingName\":\"武汉大学信息学部\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"\",\"createTime\":null,\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":23,\"upperBuildingID\":75,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001001004\",\"buildingID\":8,\"buildingLevelID\":null,\"buildingName\":\"生科院\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"\",\"createTime\":\"2017-05-05 17:12:37\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":23,\"upperBuildingID\":1,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001001005\",\"buildingID\":20,\"buildingLevelID\":null,\"buildingName\":\"电信院\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 13:25:27\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":23,\"upperBuildingID\":1,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001001\",\"buildingID\":21,\"buildingLevelID\":null,\"buildingName\":\"经济与管理学院\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 13:30:58\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":23,\"upperBuildingID\":1,\"userID\":1},{\"buildingArea\":1500,\"buildingCode\":\"420106001001\",\"buildingID\":23,\"buildingLevelID\":null,\"buildingName\":\"马克思学院\",\"buildingTypeID\":null,\"chargeMan\":\"窦校长\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 13:41:17\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"山东省/青岛市/市南区/111\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":23,\"upperBuildingID\":1,\"userID\":1},{\"buildingArea\":150,\"buildingCode\":\"420106001001001001\",\"buildingID\":41,\"buildingLevelID\":null,\"buildingName\":\"a101\",\"buildingTypeID\":null,\"chargeMan\":\"张\",\"contactWay\":\"12345678911\",\"createTime\":\"2017-05-06 20:35:02\",\"floorNum\":null,\"isPublic\":0,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":122,\"upperBuildingID\":3,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001001002\",\"buildingID\":42,\"buildingLevelID\":null,\"buildingName\":\"a102\",\"buildingTypeID\":null,\"chargeMan\":\"王\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 20:36:26\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":3,\"userID\":1},{\"buildingArea\":50,\"buildingCode\":\"420106001001002001\",\"buildingID\":43,\"buildingLevelID\":null,\"buildingName\":\"b206\",\"buildingTypeID\":null,\"chargeMan\":\"张老师\",\"contactWay\":\"1555\",\"createTime\":\"2017-05-06 20:39:27\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":45,\"upperBuildingID\":4,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001003\",\"buildingID\":44,\"buildingLevelID\":null,\"buildingName\":\"C区\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 20:45:07\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":2,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001004\",\"buildingID\":45,\"buildingLevelID\":null,\"buildingName\":\"D区\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 20:45:29\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":2,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001005\",\"buildingID\":46,\"buildingLevelID\":null,\"buildingName\":\"E区\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 20:46:00\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":2,\"userID\":1},{\"buildingArea\":15220,\"buildingCode\":\"42001\",\"buildingID\":47,\"buildingLevelID\":null,\"buildingName\":\"武汉大学工学部\",\"buildingTypeID\":null,\"chargeMan\":\"15\",\"contactWay\":\"\",\"createTime\":\"2017-05-06 20:57:20\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":null,\"modifyTime\":null,\"remark\":\"\",\"rengShu\":152,\"upperBuildingID\":75,\"userID\":1},{\"buildingArea\":200,\"buildingCode\":\"42001d\",\"buildingID\":51,\"buildingLevelID\":null,\"buildingName\":\"工学部1教\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"2012326574\",\"createTime\":\"2017-05-08 10:37:53\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"测试 lanyu\",\"rengShu\":1000,\"upperBuildingID\":47,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"42001d0\",\"buildingID\":53,\"buildingLevelID\":null,\"buildingName\":\"1100\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-08 10:38:14\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":51,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"42001d01\",\"buildingID\":54,\"buildingLevelID\":null,\"buildingName\":\"01\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-08 10:38:23\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":51,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001003507\",\"buildingID\":57,\"buildingLevelID\":null,\"buildingName\":\"c507\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-08 10:42:29\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":44,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001004012\",\"buildingID\":58,\"buildingLevelID\":null,\"buildingName\":\"d312\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-08 10:42:48\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":45,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"42010600100100415889\",\"buildingID\":60,\"buildingLevelID\":null,\"buildingName\":\"海洋生物管理所\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-05-08 10:44:02\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":30.545,\"lng\":114.366875,\"location\":\"\",\"modifyTime\":null,\"remark\":\"\",\"rengShu\":null,\"upperBuildingID\":8,\"userID\":1},{\"buildingArea\":10000,\"buildingCode\":\"420120\",\"buildingID\":75,\"buildingLevelID\":null,\"buildingName\":\"武汉大学\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"68779110\",\"createTime\":\"2017-06-01 10:03:36\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":null,\"lat\":null,\"lng\":null,\"location\":null,\"modifyTime\":null,\"remark\":\"测试\",\"rengShu\":100000,\"upperBuildingID\":0,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001\",\"buildingID\":76,\"buildingLevelID\":null,\"buildingName\":\"国际软件学院教学楼\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-06-08 09:25:16\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":false,\"lat\":null,\"lng\":null,\"location\":\"\",\"modifyTime\":null,\"remark\":\"测试\",\"rengShu\":null,\"upperBuildingID\":5,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001001\",\"buildingID\":77,\"buildingLevelID\":null,\"buildingName\":\"主教1-01\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-06-08 09:26:52\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":null,\"lng\":null,\"location\":\"\",\"modifyTime\":null,\"remark\":\"测试\",\"rengShu\":null,\"upperBuildingID\":76,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001002\",\"buildingID\":78,\"buildingLevelID\":null,\"buildingName\":\"主教1-02\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-06-08 09:27:17\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":null,\"lng\":null,\"location\":\"\",\"modifyTime\":null,\"remark\":\"测试\",\"rengShu\":null,\"upperBuildingID\":76,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001002002\",\"buildingID\":79,\"buildingLevelID\":null,\"buildingName\":\"B507\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-06-08 09:28:45\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":null,\"lng\":null,\"location\":\"\",\"modifyTime\":null,\"remark\":\"CES\",\"rengShu\":null,\"upperBuildingID\":4,\"userID\":1},{\"buildingArea\":null,\"buildingCode\":\"420106001001002003\",\"buildingID\":80,\"buildingLevelID\":null,\"buildingName\":\"B508\",\"buildingTypeID\":null,\"chargeMan\":\"\",\"contactWay\":\"\",\"createTime\":\"2017-06-08 09:29:12\",\"floorNum\":null,\"isPublic\":null,\"isRoom\":true,\"lat\":null,\"lng\":null,\"location\":\"\",\"modifyTime\":null,\"remark\":\"测试\",\"rengShu\":null,\"upperBuildingID\":4,\"userID\":1}]";
        JSONArray array = new JSONArray(jsonString);
        JSONObject item = array.getJSONObject(0);

        ArrayList<HashMap<String,Object>>list = new ArrayList<>();
        util.JsonToArrList(array,item.getInt("upperBuildingID"),0,list);
        for(int i=0;i<list.size();i++) {
            HashMap<String,Object>map = list.get(i);
            for(int j=0;j<Integer.parseInt(map.get("layer").toString());j++)
                System.out.print("\t");
            System.out.println(map.get("buildingID")+"\t"+map.get("buildingName")+"\t"+map.get("upperBuildingID"));
        }
       /*
        HashMap<Long,ArrayList<HashMap<String,Object>>>map = new HashMap<>();
        util.JsonToMap(array,item.getInt("upperBuildingID"),0,map);
        Iterator<Map.Entry<Long, ArrayList<HashMap<String,Object>>>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Long, ArrayList<HashMap<String,Object>>> entry = it.next();
            ArrayList<HashMap<String,Object>>list = entry.getValue();
            System.out.print("key= " + entry.getKey() + " and value= "  );
            for(int i=0;i<list.size();i++)
                System.out.println(list.get(i).get("buildingName")+"\t");
        }*/
    }

}