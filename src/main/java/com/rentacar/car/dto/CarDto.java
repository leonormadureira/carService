package com.rentacar.car.dto;

public class CarDto {

    public Long id;
    public String brand;
    public String model;
    public boolean is_available;
    public Long user_id;

    @Override
    public String toString() {
        return "CarDto [id = " + id + ", brand = " + brand + ", model = " + model + ", is_available = "+ is_available + ", user_id = " + user_id + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
