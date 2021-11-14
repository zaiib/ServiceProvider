package com.serviceprovider.serviceprovider.Model;


public class UploadTaskModel {
    String Name;
    int Image;

    public UploadTaskModel(String name, int image) {
        Name = name;
        Image = image;
    }

    public String  getName() {
        return Name;
    }

    public int getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(int image) {
        Image = image;
    }
}
