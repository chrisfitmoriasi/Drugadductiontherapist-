package com.example.drugaddicationtherapysystem;

public class TherapistHelper {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TherapistHelper(String name, String phonenumber, String imageurl, String id) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.imageurl = imageurl;
        this.id = id;
    }

    private String name;
    private String phonenumber;
    private String imageurl;
    private String id;

    public TherapistHelper(){}
}
