package com.example.indiankullad;

public class Model {

    String kullad_name;
    String kullad_code;
    String price ;
    String description;
    String image;
    String address;
    String box_capacity;
    String quantity;

    public Model() {
    }

    public Model(String address, String box_capacity, String quantity) {
        this.address = address;
        this.box_capacity = box_capacity;
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBox_capacity() {
        return box_capacity;
    }

    public void setBox_capacity(String box_capacity) {
        this.box_capacity = box_capacity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
