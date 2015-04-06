package com.zs.po;

import java.io.Serializable;
import java.util.Date;

public class Child implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Integer childId;
    private String childName;
//    private Integer parentId;
    private User parent;
    private Date birthday;
    private String childInformation;

    public Child() {
    }

    public Child( String childName, User parent, Date birthday, String childInformation) {
        this.childName = childName;
        this.parent = parent;
        this.birthday = birthday;
        this.childInformation = childInformation;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public String getChildInformation() {
        return childInformation;
    }

    public void setChildInformation(String childInformation) {
        this.childInformation = childInformation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
