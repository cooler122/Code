package com.zs.po;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer userId;
    private String name;
    private Integer age;
    private String information;
    private User mate;
    private Company company;
    private Position position;
    private List<Child> childList;

    public User() {
    }

    public User(String name, Integer age, String information, User mate, Company company, Position position, List<Child> childList) {
        this.name = name;
        this.age = age;
        this.information = information;
        this.mate = mate;
        this.company = company;
        this.position = position;
        this.childList = childList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getMate() {
        return mate;
    }

    public void setMate(User mate) {
        this.mate = mate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
