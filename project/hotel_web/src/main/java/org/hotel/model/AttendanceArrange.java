package org.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class AttendanceArrange implements Serializable {
    private String id;

    private String orderId;

    private String orderName;

    private Date arrangeBegin;

    private Date arrangeEnd;

    private String createUser;

    private String createTime;

    private String modifyUser;

    private String modifyTime;

    private Byte delFlag;

    private String staffId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public Date getArrangeBegin() {
        return arrangeBegin;
    }

    public void setArrangeBegin(Date arrangeBegin) {
        this.arrangeBegin = arrangeBegin;
    }

    public Date getArrangeEnd() {
        return arrangeEnd;
    }

    public void setArrangeEnd(Date arrangeEnd) {
        this.arrangeEnd = arrangeEnd;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }
}