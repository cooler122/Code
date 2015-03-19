package cn.springmvc.model;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-3-15
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class Position {
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
