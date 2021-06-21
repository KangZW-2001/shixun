package com.kangzw.practicetrain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cityinfo")
public class CityInfo {
    @Id
    private String id;

    @Field(value = "city")
    private String city;

    @Field(value = "ID")
    private String ID;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
