package org.hotel.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AttendanceRecord implements Serializable {
    private String id;

    private String staffId;

    private String staffName;

    private String orgId;

    private String orgName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date restTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date workTime;

    private String attendanceTypeId;

    private String attendanceTypeName;

    private Integer num;

    private String createUser;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;

    private String modifyId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date modifyTime;

    private Byte delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
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

    public Date getRestTime() {
        return restTime;
    }

    public void setRestTime(Date restTime) {
        this.restTime = restTime;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getAttendanceTypeId() {
        return attendanceTypeId;
    }

    public void setAttendanceTypeId(String attendanceTypeId) {
        this.attendanceTypeId = attendanceTypeId == null ? null : attendanceTypeId.trim();
    }

    public String getAttendanceTypeName() {
        return attendanceTypeName;
    }

    public void setAttendanceTypeName(String attendanceTypeName) {
        this.attendanceTypeName = attendanceTypeName == null ? null : attendanceTypeName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId == null ? null : modifyId.trim();
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