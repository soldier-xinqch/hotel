package org.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class AttendanceBrush implements Serializable {
    private String id;

    private String staffId;

    private String staffNo;

    private String staffName;

    private String orgId;

    private String orgName;

    private Date time;

    private String brush1;

    private String brush2;

    private String brush3;

    private String brush4;

    private String brush5;

    private String brush6;

    private String brush7;

    private String brush8;

    private String brush9;

    /**
     *  借用字段  保存天 方便查询
     */
    private String brush10;

    private String createUser;

    private Date createTime;

    private String modifyUser;

    private Date modifyTime;

    private String delFlag;

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

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo == null ? null : staffNo.trim();
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBrush1() {
        return brush1;
    }

    public void setBrush1(String brush1) {
        this.brush1 = brush1 == null ? null : brush1.trim();
    }

    public String getBrush2() {
        return brush2;
    }

    public void setBrush2(String brush2) {
        this.brush2 = brush2 == null ? null : brush2.trim();
    }

    public String getBrush3() {
        return brush3;
    }

    public void setBrush3(String brush3) {
        this.brush3 = brush3 == null ? null : brush3.trim();
    }

    public String getBrush4() {
        return brush4;
    }

    public void setBrush4(String brush4) {
        this.brush4 = brush4 == null ? null : brush4.trim();
    }

    public String getBrush5() {
        return brush5;
    }

    public void setBrush5(String brush5) {
        this.brush5 = brush5 == null ? null : brush5.trim();
    }

    public String getBrush6() {
        return brush6;
    }

    public void setBrush6(String brush6) {
        this.brush6 = brush6 == null ? null : brush6.trim();
    }

    public String getBrush7() {
        return brush7;
    }

    public void setBrush7(String brush7) {
        this.brush7 = brush7 == null ? null : brush7.trim();
    }

    public String getBrush8() {
        return brush8;
    }

    public void setBrush8(String brush8) {
        this.brush8 = brush8 == null ? null : brush8.trim();
    }

    public String getBrush9() {
        return brush9;
    }

    public void setBrush9(String brush9) {
        this.brush9 = brush9 == null ? null : brush9.trim();
    }

    public String getBrush10() {
        return brush10;
    }

    public void setBrush10(String brush10) {
        this.brush10 = brush10 == null ? null : brush10.trim();
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}