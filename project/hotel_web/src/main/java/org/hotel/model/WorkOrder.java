package org.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class WorkOrder implements Serializable {
    private String id;

    private String orderNo;

    private String beginWork;

    private String endWork;

    private Integer beginElasticTime;

    private Integer endElasticTime;

    private Integer workTime;

    private String workType;

    private String workTypeName;

    private Byte breakfast;

    private Byte lunch;

    private Byte dinner;

    private Byte nightEating;

    private Integer tomorrowEatNum;

    private String workDesc;

    private String createUser;

    private Date createTime;

    private String modifyUser;

    private Date modifyTime;

    private Byte delFlag;

    private String orgId;

    private String orgName;

    private String onStart;

    private String onEnd;

    private String offStart;

    private String offEnd;

    private String offOtherTime;

    private String onOtherTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getBeginWork() {
        return beginWork;
    }

    public void setBeginWork(String beginWork) {
        this.beginWork = beginWork == null ? null : beginWork.trim();
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork == null ? null : endWork.trim();
    }

    public Integer getBeginElasticTime() {
        return beginElasticTime;
    }

    public void setBeginElasticTime(Integer beginElasticTime) {
        this.beginElasticTime = beginElasticTime;
    }

    public Integer getEndElasticTime() {
        return endElasticTime;
    }

    public void setEndElasticTime(Integer endElasticTime) {
        this.endElasticTime = endElasticTime;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName == null ? null : workTypeName.trim();
    }

    public Byte getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Byte breakfast) {
        this.breakfast = breakfast;
    }

    public Byte getLunch() {
        return lunch;
    }

    public void setLunch(Byte lunch) {
        this.lunch = lunch;
    }

    public Byte getDinner() {
        return dinner;
    }

    public void setDinner(Byte dinner) {
        this.dinner = dinner;
    }

    public Byte getNightEating() {
        return nightEating;
    }

    public void setNightEating(Byte nightEating) {
        this.nightEating = nightEating;
    }

    public Integer getTomorrowEatNum() {
        return tomorrowEatNum;
    }

    public void setTomorrowEatNum(Integer tomorrowEatNum) {
        this.tomorrowEatNum = tomorrowEatNum;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc == null ? null : workDesc.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOnStart() {
        return onStart;
    }

    public void setOnStart(String onStart) {
        this.onStart = onStart == null ? null : onStart.trim();
    }

    public String getOnEnd() {
        return onEnd;
    }

    public void setOnEnd(String onEnd) {
        this.onEnd = onEnd == null ? null : onEnd.trim();
    }

    public String getOffStart() {
        return offStart;
    }

    public void setOffStart(String offStart) {
        this.offStart = offStart == null ? null : offStart.trim();
    }

    public String getOffEnd() {
        return offEnd;
    }

    public void setOffEnd(String offEnd) {
        this.offEnd = offEnd == null ? null : offEnd.trim();
    }

    public String getOffOtherTime() {
        return offOtherTime;
    }

    public void setOffOtherTime(String offOtherTime) {
        this.offOtherTime = offOtherTime == null ? null : offOtherTime.trim();
    }

    public String getOnOtherTime() {
        return onOtherTime;
    }

    public void setOnOtherTime(String onOtherTime) {
        this.onOtherTime = onOtherTime == null ? null : onOtherTime.trim();
    }
}