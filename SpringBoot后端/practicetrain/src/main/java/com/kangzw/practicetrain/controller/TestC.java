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
}
