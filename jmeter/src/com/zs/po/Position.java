package com.zs.po;

import java.io.Serializable;

public class Position  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer positionId;
    private String positionName;
    private Integer companyId;

    public Position() {
    }

    public Position( String positionName, Integer companyId) {
        this.positionName = positionName;
        this.companyId = companyId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
