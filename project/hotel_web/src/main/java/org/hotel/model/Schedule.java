package org.hotel.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Schedule implements Serializable {
    private String id;

    private String scheduleName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date endTime;

    private String orgId;

    private String orgName;

    private String userId;

    private String userName;

    private String createUser;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;

    private String modifyUser;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date modifyTime;

    private Byte delFlag;
    
    private String scheduleMsg;

    private String placeMsg;
    
    public String getScheduleMsg() {
		return scheduleMsg;
	}

	public void setScheduleMsg(String scheduleMsg) {
		this.scheduleMsg = scheduleMsg;
	}

	public String getPlaceMsg() {
		return placeMsg;
	}

	public void setPlaceMsg(String placeMsg) {
		this.placeMsg = placeMsg;
	}


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName == null ? null : scheduleName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
}