package org.hotel.model;

import java.io.Serializable;

public class ScheduleWithBLOBs extends Schedule implements Serializable {
    private String staffId;

    private String scheduleMsg;

    private String placeMsg;

    private static final long serialVersionUID = 1L;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getScheduleMsg() {
        return scheduleMsg;
    }

    public void setScheduleMsg(String scheduleMsg) {
        this.scheduleMsg = scheduleMsg == null ? null : scheduleMsg.trim();
    }

    public String getPlaceMsg() {
        return placeMsg;
    }

    public void setPlaceMsg(String placeMsg) {
        this.placeMsg = placeMsg == null ? null : placeMsg.trim();
    }
}