package com.khr.justquitit;

public class ProductList {
    String productname;
    int productimage;
    String productdescription;


    public ProductList(String productname, int productimage, String productdescription) {
        this.productname = productname;
        this.productimage = productimage;
        this.productdescription = productdescription;
    }

    public String getName() {
        return productname;
    }

    public void setName(String productname) {
        this.productname = productname;
    }

    public int getImage() {
        return productimage;
    }

    public void setImage(int productimage) {
        this.productimage = productimage;
    }

    public String getDescription() {
        return productdescription;
    }

    public void setDescription(String productdescription) {
        this.productdescription = productdescription;
    }
}
