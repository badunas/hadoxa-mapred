package com.hadoxa.so.pojo;

public class Badge {

    private String id;
    private String userId;
    private String name;
    private String date;
    private String classValue;
    private String tagBased;

    public Badge(String id,
                 String userId,
                 String name,
                 String date,
                 String classValue,
                 String tagBased) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.classValue = classValue;
        this.tagBased = tagBased;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }

    public String getTagBased() {
        return tagBased;
    }

    public void setTagBased(String tagBased) {
        this.tagBased = tagBased;
    }
}
