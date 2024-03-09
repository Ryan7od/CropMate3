package com.example.cropmate.fieldManagement;

import android.text.Editable;

public class Field {

    private String fieldID;
    private String name;
    private Double area;
    private String crop;
    private Editable planted;
    private String soilHealth;
    private String location;

    public Field(String fieldID, String name, Double area, String crop, Editable planted, String soilHealth, String location){
        if(fieldID == null || name == null || area == null || crop == null || planted == null || soilHealth == null || location == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.fieldID = fieldID;
        this.name = name;
        this.area = area;
        this.crop = crop;
        this.planted = planted;
        this.soilHealth = soilHealth;
        this.location = location;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        if(fieldID == null) {
            throw new IllegalArgumentException("Field ID cannot be null");
        }
        this.fieldID = fieldID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        if(area == null) {
            throw new IllegalArgumentException("Area cannot be null");
        }
        this.area = area;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        if(crop == null) {
            throw new IllegalArgumentException("Crop cannot be null");
        }
        this.crop = crop;
    }

    public Editable getPlanted() {
        return planted;
    }

        public void setPlanted(Editable planted) {
        if(planted == null) {
            throw new IllegalArgumentException("Planted cannot be null");
        }
        this.planted = planted;
    }

    public String getSoilHealth() {
        return soilHealth;
    }

    public void setSoilHealth(String soilHealth) {
        if(soilHealth == null) {
            throw new IllegalArgumentException("Soil Health cannot be null");
        }
        this.soilHealth = soilHealth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if(location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = location;
    }
}