package com.khr.justquitit;

public class TipsFirebase {
    String Title, Description, Image;

    TipsFirebase(){

    }

    public TipsFirebase(String title, String description, String image) {
        Title = title;
        Description = description;
        Image = image;
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
}
