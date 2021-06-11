package com.kangzw.practicetrain.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import com.kangzw.practicetrain.entity.EmployInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.alibaba.fastjson.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestC {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test")
    public JSON test(){
//        Criteria
        Query query = new Query(Criteria.where("工作名").is("Java微服务"));
        MongoOperations mongoOperations = mongoTemplate;
        EmployInfo employInfo = mongoTemplate.findOne(query,EmployInfo.class);
        System.out.println(employInfo.getSkill());

        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("jobName",employInfo.getJobName());
        jsonObject.put("salary",employInfo.getSalary());
        jsonObject.put("loc",employInfo.getLoc());
        jsonObject.put("exp",employInfo.getExp());
        jsonObject.put("edu",employInfo.getEdu());
        jsonObject.put("skill",employInfo.getSkill());
        jsonObject.put("company",employInfo.getCompany());
        return jsonObject;
    }

    @GetMapping("/radar")
    public JSON getRadarInfo(){
        JSONObject res = new JSONObject(true);

        // 构造title
        JSONObject title = new JSONObject();
        title.put("text", "我的雷达图");

        // 构造legend
        JSONObject legend = new JSONObject();
        JSONArray legendData = new JSONArray();
        legendData.add("预算分配");
        legendData.add("实际开销");
        legend.put("data",legendData);

        // 构造radar
        JSONObject radar = new JSONObject();
        JSONArray indicator = new JSONArray();
        String names[] = {"销售","管理","信息技术","客服","研发","市场"};
        int maxs[] = {6500, 16000,30000,38000,52000,25000};
        for(int i = 0; i < 6; i++){
            JSONObject temp = new JSONObject();
            temp.put("name", names[i]);
            temp.put("max",maxs[i]);
            indicator.add(temp);
        }
        radar.put("indicator", indicator);

        // 构造series
        JSONObject series = new JSONObject();
        series.put("name", "预算 vs 开销");
        series.put("type", "radar");
        JSONArray data = new JSONArray();

        int values1[] = {4200, 3000, 20000, 35000, 50000,18000};
        int values2[] = {5000, 14000, 28000, 26000, 42000, 21000};
        JSONObject temp1 = new JSONObject();
        JSONObject temp2 = new JSONObject();
        temp1.put("value", values1);
        temp2.put("value", values2);
        temp1.put("name", "预算分配");
        temp2.put("name", "实际开销");
        data.add(temp1);
        data.add(temp2);
        series.put("data", data);

        res.put("title", title);
        res.put("legend", legend);
        res.put("radar", radar);
        res.put("series", series);

        return res;
    }



}
