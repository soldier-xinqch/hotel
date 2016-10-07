package org.hotel.model;

import java.io.Serializable;

public class StaffWithBLOBs extends Staff implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public String getFaceTemp() {
        return faceTemp;
    }

    public void setFaceTemp(String faceTemp) {
        this.faceTemp = faceTemp == null ? null : faceTemp.trim();
    }

    public String getFingerTemp1() {
        return fingerTemp1;
    }

    public void setFingerTemp1(String fingerTemp1) {
        this.fingerTemp1 = fingerTemp1 == null ? null : fingerTemp1.trim();
    }

    public String getFingerTemp2() {
        return fingerTemp2;
    }

    public void setFingerTemp2(String fingerTemp2) {
        this.fingerTemp2 = fingerTemp2 == null ? null : fingerTemp2.trim();
    }

    public String getFingerTemp3() {
        return fingerTemp3;
    }

    public void setFingerTemp3(String fingerTemp3) {
        this.fingerTemp3 = fingerTemp3 == null ? null : fingerTemp3.trim();
    }

    public String getFingerTemp4() {
        return fingerTemp4;
    }

    public void setFingerTemp4(String fingerTemp4) {
        this.fingerTemp4 = fingerTemp4 == null ? null : fingerTemp4.trim();
    }

    public String getFingerTemp5() {
        return fingerTemp5;
    }

    public void setFingerTemp5(String fingerTemp5) {
        this.fingerTemp5 = fingerTemp5 == null ? null : fingerTemp5.trim();
    }

    public String getFingerTemp6() {
        return fingerTemp6;
    }

    public void setFingerTemp6(String fingerTemp6) {
        this.fingerTemp6 = fingerTemp6 == null ? null : fingerTemp6.trim();
    }

    public String getFingerTemp7() {
        return fingerTemp7;
    }

    public void setFingerTemp7(String fingerTemp7) {
        this.fingerTemp7 = fingerTemp7 == null ? null : fingerTemp7.trim();
    }

    public String getFingerTemp8() {
        return fingerTemp8;
    }

    public void setFingerTemp8(String fingerTemp8) {
        this.fingerTemp8 = fingerTemp8 == null ? null : fingerTemp8.trim();
    }

    public String getFingerTemp9() {
        return fingerTemp9;
    }

    public void setFingerTemp9(String fingerTemp9) {
        this.fingerTemp9 = fingerTemp9 == null ? null : fingerTemp9.trim();
    }

    public String getFingerTemp0() {
        return fingerTemp0;
    }

    public void setFingerTemp0(String fingerTemp0) {
        this.fingerTemp0 = fingerTemp0 == null ? null : fingerTemp0.trim();
    }
}