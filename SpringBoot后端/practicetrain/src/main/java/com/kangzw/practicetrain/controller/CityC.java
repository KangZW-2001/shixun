package com.kangzw.practicetrain.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kangzw.practicetrain.entity.CityInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
public class CityC {
    @Autowired
    private MongoTemplate mongoTemplate;

//    private List<CityInfo> citys;

    @GetMapping("/city")
    public JSON getCityJSON() {
        System.out.println("调用了citys中的get函数！！！！");
        JSONArray res = new JSONArray();
        MongoOperations mongoOperations = mongoTemplate;
        List<CityInfo> cityList = mongoOperations.findAll(CityInfo.class);
        JSONObject popularCity = (JSONObject) handlePopularCity(new JSONObject(true));
        JSONObject normalCity = (JSONObject) handleNormalCity(new JSONObject(true), cityList);
        // 创建热门城市的列表
        res.add(popularCity);
        res.add(normalCity);
        return res;
    }

    public JSON handlePopularCity(JSONObject jsonO){
        jsonO.put("label","热门城市");
        String [] citys = {"北京","上海","广东","南京"};
        String [] cityIDs = {"530", "538", "548", "635"};
        JSONArray options = new JSONArray();
        for(int i = 0; i < citys.length; i++){
            JSONObject temp = new JSONObject(true);
            temp.put("value", cityIDs[i]);
            temp.put("label", citys[i]);
            options.add(temp);
        }
        jsonO.put("options", options);
        return jsonO;
    }

    public JSON handleNormalCity(JSONObject jsonO, List cityList){
        jsonO.put("label","城市名");
        JSONArray options = new JSONArray();
        Iterator it = cityList.iterator();
        while(it.hasNext()){
            CityInfo cityInfo = (CityInfo) it.next();
            JSONObject temp = new JSONObject(true);
            temp.put("value",cityInfo.getID());
            temp.put("label",cityInfo.getCity());
            options.add(temp);
        }
        jsonO.put("options", options);
        return jsonO;
    }

}
