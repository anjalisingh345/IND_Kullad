package com.example.indiankullad;

public class Model {

    String kullad_name;
    String kullad_code;
    String price ;
    String description;
    String image;

    public Model() {
    }

    public Model(String kullad_name, String kullad_code, String price, String description, String image) {
        this.kullad_name = kullad_name;
        this.kullad_code = kullad_code;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getKullad_name() {
        return kullad_name;
    }

    public void setKullad_name(String kullad_name) {
        this.kullad_name = kullad_name;
    }

    public String getKullad_code() {
        return kullad_code;
    }

    public void setKullad_code(String kullad_code) {
        this.kullad_code = kullad_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
