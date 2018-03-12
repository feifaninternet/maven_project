package com.czh.core.constant;

import com.java.core.http.ApiGetUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.java.core.constant.ThirdPartConstant.TENCENT_MAP_RANDOM_LOCATION_URL;

/**
 * Latitude and longitude 经纬度位置获取服务的相关变量和方法
 * Created by huxingyue on 2017/7/27.
 *
 * @author Administrator
 */
public class LlConstant {
    /**
     * 最大生成数量
     */
    public static int MAX_LL_NUMBER = 6;
    /**
     * 最小生成数量
     */
    public static int MIN_LL_NUMBER = 3;
    /**
     * 生成范围 单位KM
     */
    public static double RANGE_NUMBER = 1;
    /**
     * 生成点之间的距离 单位KM
     */
    private static double DISTANCE_BETWEEN_POINT = RANGE_NUMBER / 3;

    public static List<Map<String, Double>> calculate3(double startLat, double startLng, double maxDist, int generatedNumber) {

        String poiOptions = "address_format=short;radius={radius};page_size=20;page_index=1;policy=3;category=便利店,超市,房产小区";
        int radius = (int) (maxDist * 1000);
        poiOptions = poiOptions.replace("{radius}", String.valueOf(radius));
        String url = TENCENT_MAP_RANDOM_LOCATION_URL;

        //处理请求参数
        url = url.replace("{poi_options}", poiOptions);
        url = url.replace("{location}", startLat + "," + startLng);

        //请求腾讯地图接口
        HttpGet httpGet = new HttpGet(url);
        String resultStr = ApiGetUtil.get(httpGet);
        JSONObject resultObject = JSONObject.fromObject(resultStr);
        if (resultObject.getInt("status") != 0) {
            return null;
        }
        JSONArray poIs = resultObject.getJSONObject("result").getJSONArray("pois");

        List<Map<String, Double>> gpsDataList = new LinkedList<>();

        for (int i = 0; i < poIs.size(); i++) {
            boolean flag = true;
            // 弧度转换成经纬度
            Map<String, Double> map = new HashMap<>(1, 2);

            double lat = poIs.getJSONObject(i).getJSONObject("location").getDouble("lat");
            double lng = poIs.getJSONObject(i).getJSONObject("location").getDouble("lng");

            //对生成的点的距离进行限制
            for (Map<String, Double> map1 : gpsDataList) {

                double distance = distance2(lng, lat, map1.get("longitude"), map1.get("latitude")) * 1000;
                //maxDist 单位是 km distance 单位是 m
                if (distance < DISTANCE_BETWEEN_POINT * 1000) {
                    flag = false;
                }
            }
            //让内层控制外层循环
            if (!flag) {
                continue;
            }

            //如果通过距离判断器，则加入待用列表
            map.put("latitude", poIs.getJSONObject(i).getJSONObject("location").getDouble("lat"));
            map.put("longitude", poIs.getJSONObject(i).getJSONObject("location").getDouble("lng"));
            gpsDataList.add(map);

            //数量足够，则跳出循环
            if (gpsDataList.size() == generatedNumber) {
                break;
            }
        }
        return gpsDataList;
    }

    /**
     * 转化为弧度(rad)
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    private static double distance2(double lon1, double lat1, double lon2, double lat2) {
        //赤道半径(单位m)
        double earthRadius = 6378.137;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * earthRadius;
        //s = Math.round(s * 10000) / 10000;
        return s;
    }

}
