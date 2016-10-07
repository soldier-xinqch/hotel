package org.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Staff implements Serializable {
    private String id;

    private String staffNo;

    private String staffName;

    private String orgId;

    private String orgName;

    private String parentId;

    private String sex;

    private String cardId;

    private String mac;
    
    private String staffCardNo;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date intoTime;

    private String telphone;

    private String staffAddress;

    private String email;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date birthday;

    private BigDecimal yearRestDay;

    private BigDecimal keepRestDay;

    private String createUser;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date createTime;

    private String modifyUser;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date modifyTime;

    private Byte delFlag;

    private String staffStatus;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date quitTime;

    private String quitCheckId;

    private String quitCheckName;

    private String quitDesc;

    private String quitMemo;

    private String ortherMemo;
    
    private String faceTemp;

    private String fingerTemp1;

    private String fingerTemp2;

    private String fingerTemp3;

    private String fingerTemp4;

    private String fingerTemp5;

    private String fingerTemp6;

    private String fingerTemp7;

    private String fingerTemp8;

    private String fingerTemp9;

    private String fingerTemp0;

    public String getFaceTemp() {
		return faceTemp;
	}

	public void setFaceTemp(String faceTemp) {
		this.faceTemp = faceTemp;
	}

	public String getFingerTemp1() {
		return fingerTemp1;
	}

	public void setFingerTemp1(String fingerTemp1) {
		this.fingerTemp1 = fingerTemp1;
	}

	public String getFingerTemp2() {
		return fingerTemp2;
	}

	public void setFingerTemp2(String fingerTemp2) {
		this.fingerTemp2 = fingerTemp2;
	}

	public String getFingerTemp3() {
		return fingerTemp3;
	}

	public void setFingerTemp3(String fingerTemp3) {
		this.fingerTemp3 = fingerTemp3;
	}

	public String getFingerTemp4() {
		return fingerTemp4;
	}

	public void setFingerTemp4(String fingerTemp4) {
		this.fingerTemp4 = fingerTemp4;
	}

	public String getFingerTemp5() {
		return fingerTemp5;
	}

	public void setFingerTemp5(String fingerTemp5) {
		this.fingerTemp5 = fingerTemp5;
	}

	public String getFingerTemp6() {
		return fingerTemp6;
	}

	public void setFingerTemp6(String fingerTemp6) {
		this.fingerTemp6 = fingerTemp6;
	}

	public String getFingerTemp7() {
		return fingerTemp7;
	}

	public void setFingerTemp7(String fingerTemp7) {
		this.fingerTemp7 = fingerTemp7;
	}

	public String getFingerTemp8() {
		return fingerTemp8;
	}

	public void setFingerTemp8(String fingerTemp8) {
		this.fingerTemp8 = fingerTemp8;
	}

	public String getFingerTemp9() {
		return fingerTemp9;
	}

	public void setFingerTemp9(String fingerTemp9) {
		this.fingerTemp9 = fingerTemp9;
	}

	public String getFingerTemp0() {
		return fingerTemp0;
	}

	public void setFingerTemp0(String fingerTemp0) {
		this.fingerTemp0 = fingerTemp0;
	}

	private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getStaffCardNo() {
        return staffCardNo;
    }

    public void setStaffCardNo(String staffCardNo) {
        this.staffCardNo = staffCardNo == null ? null : staffCardNo.trim();
    }

    public Date getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(Date intoTime) {
        this.intoTime = intoTime;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress == null ? null : staffAddress.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getYearRestDay() {
        return yearRestDay;
    }

    public void setYearRestDay(BigDecimal yearRestDay) {
        this.yearRestDay = yearRestDay;
    }

    public BigDecimal getKeepRestDay() {
        return keepRestDay;
    }

    public void setKeepRestDay(BigDecimal keepRestDay) {
        this.keepRestDay = keepRestDay;
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

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus == null ? null : staffStatus.trim();
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public String getQuitCheckId() {
        return quitCheckId;
    }

    public void setQuitCheckId(String quitCheckId) {
        this.quitCheckId = quitCheckId == null ? null : quitCheckId.trim();
    }

    public String getQuitCheckName() {
        return quitCheckName;
    }

    public void setQuitCheckName(String quitCheckName) {
        this.quitCheckName = quitCheckName == null ? null : quitCheckName.trim();
    }

    public String getQuitDesc() {
        return quitDesc;
    }

    public void setQuitDesc(String quitDesc) {
        this.quitDesc = quitDesc == null ? null : quitDesc.trim();
    }

    public String getQuitMemo() {
        return quitMemo;
    }

    public void setQuitMemo(String quitMemo) {
        this.quitMemo = quitMemo == null ? null : quitMemo.trim();
    }

    public String getOrtherMemo() {
        return ortherMemo;
    }

    public void setOrtherMemo(String ortherMemo) {
        this.ortherMemo = ortherMemo == null ? null : ortherMemo.trim();
    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}