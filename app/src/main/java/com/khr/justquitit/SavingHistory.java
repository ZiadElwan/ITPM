package com.khr.justquitit;

public class SavingHistory {
    //Model Class
    private String savingDate;
    private String savingActivity;
    private String saving;

    public SavingHistory(String savingDate, String savingActivity, String saving) {
        this.savingDate = savingDate;
        this.savingActivity = savingActivity;
        this.saving = saving;
    }

    public String getSavingDate() {
        return savingDate;
    }

    public void setSavingDate(String savingDate) {
        this.savingDate = savingDate;
    }

    public String getSavingActivity() {
        return savingActivity;
    }

    public void setSavingActivity(String savingActivity) {
        this.savingActivity = savingActivity;
    }

    public String getSaving() {
        return saving;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }
}
