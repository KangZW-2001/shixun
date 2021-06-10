package com.kangzw.practicetrain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employinfo")
public class EmployInfo {
    @Id
    private String id;

    @Field(value = "工作名")
    private String jobName;

    @Field(value = "薪水")
    private String salary;

    @Field(value = "地点")
    private String loc;

    @Field(value = "经验")
    private String exp;

    @Field(value = "学历")
    private String edu;

    @Field(value = "技能")
    private String skill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Field(value = "公司")
    private String company;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
