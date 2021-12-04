package com.khr.justquitit;

public class TipsFirebase {
    String Title;
    String Description;
    String Image;
    String tipsId;

    TipsFirebase(){

    }

    public TipsFirebase(String title, String description, String image, String tipsID) {
        Title = title;
        Description = description;
        Image = image;
        tipsId = tipsID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTipsId() {
        return tipsId;
    }

    public void setTipsId(String tipsId) {
        this.tipsId = tipsId;
    }
}
