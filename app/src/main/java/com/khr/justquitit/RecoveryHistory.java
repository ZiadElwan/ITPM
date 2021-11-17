package com.khr.justquitit;

public class RecoveryHistory {
    private String recoveryDay;
    private String recoveryActivity;

    public RecoveryHistory(String recoveryDay, String recoveryActivity) {
        this.recoveryDay = recoveryDay;
        this.recoveryActivity = recoveryActivity;
    }

    public String getRecoveryDay() {
        return recoveryDay;
    }

    public void setRecoveryDay(String recoveryDay) {
        this.recoveryDay = recoveryDay;
    }

    public String getRecoveryActivity() {
        return recoveryActivity;
    }

    public void setRecoveryActivity(String recoveryActivity) {
        this.recoveryActivity = recoveryActivity;
    }
}
