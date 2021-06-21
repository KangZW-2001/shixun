package com.kangzw.practicetrain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "skillinfo")
public class SkillInfo {
    @Id
    private String id;

    @Field(value = "job")
    private String job;

    @Field(value = "skill")
    private String skill;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
