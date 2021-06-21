package com.kangzw.practicetrain.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kangzw.practicetrain.entity.CityInfo;
import com.kangzw.practicetrain.entity.SkillInfo;
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
public class SkillC {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/skill")
    public JSON getSkillJSON(){
        JSONObject res = new JSONObject(true);
        System.out.println("调用了skill的get函数");
        MongoOperations mongoOperations = mongoTemplate;
        List<SkillInfo> skills = mongoOperations.findAll(SkillInfo.class);
        Iterator it = skills.iterator();
        while(it.hasNext()){
            Integer index = new Integer(1);
            SkillInfo skillInfo = (SkillInfo) it.next();
            String [] skillArray = skillInfo.getSkill().split(",");
            JSONArray skillsPerJob = new JSONArray();
            res.put(skillInfo.getJob(),skillInfo.getSkill());
            for(int i = 0; i < skillArray.length; i++){
                JSONObject temp = new JSONObject(true);
//                temp.put("value", "选项"+index.toString());
                temp.put("value", skillArray[i]);
                index ++ ;
                temp.put("label", skillArray[i]);
                skillsPerJob.add(temp);
            }
            res.put(skillInfo.getJob(), skillsPerJob);
        }
        return res;
    }
}
