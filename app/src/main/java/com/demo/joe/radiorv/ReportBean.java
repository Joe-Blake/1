package com.demo.joe.radiorv;

/**
 * Created by weizijie on 2017/7/18.
 */

public class ReportBean {

    private String reason;
    private boolean check;

    public ReportBean(String reason, boolean check) {
        this.reason = reason;
        this.check = check;
    }

    public ReportBean(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }
}
