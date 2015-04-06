package com.zs.po;

import java.io.Serializable;
import java.util.List;


public class Company  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer companyId;
    private String companyName;
    private String companyInformation;
    private List<User> userList;
    private List<Position> positionList;

    public Company() {
    }

    public Company(String companyName, String companyInformation, List<User> userList, List<Position> positionList) {
        this.companyName = companyName;
        this.companyInformation = companyInformation;
        this.userList = userList;
        this.positionList = positionList;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyInformation() {
        return companyInformation;
    }

    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }
}
