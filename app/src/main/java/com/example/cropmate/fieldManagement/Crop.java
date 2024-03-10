package com.example.cropmate.fieldManagement;

public class Crop {

    private String name;
    private String averageYield;
    private String growthTime;
    private String monthToPlant;
    private String monthToHarvest;
    private String waterFrequency;

    public Crop(String cropName, String yield, String time, String plantMonth, String harvestMonth, String water){
        if(cropName == null || yield == null || time == null || plantMonth == null || harvestMonth == null || water == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        name = cropName;
        averageYield = yield;
        growthTime = time;
        monthToPlant = plantMonth;
        monthToHarvest = harvestMonth;
        waterFrequency = water;
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

    public String getAverageYield() {
        return averageYield;
    }

    public void setAverageYield(String averageYield) {
        if(averageYield == null) {
            throw new IllegalArgumentException("Average Yield cannot be null");
        }
        this.averageYield = averageYield;
    }

    public String getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(String growthTime) {
        if(growthTime == null) {
            throw new IllegalArgumentException("Growth Time cannot be null");
        }
        this.growthTime = growthTime;
    }

    public String getMonthToPlant() {
        return monthToPlant;
    }

    public void setMonthToPlant(String monthToPlant) {
        if(monthToPlant == null) {
            throw new IllegalArgumentException("Month to Plant cannot be null");
        }
        this.monthToPlant = monthToPlant;
    }

    public String getMonthToHarvest() {
        return monthToHarvest;
    }

    public void setMonthToHarvest(String monthToHarvest) {
        if(monthToHarvest == null) {
            throw new IllegalArgumentException("Month to Harvest cannot be null");
        }
        this.monthToHarvest = monthToHarvest;
    }

    public String getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(String waterFrequency) {
        if(waterFrequency == null) {
            throw new IllegalArgumentException("Water Frequency cannot be null");
        }
        this.waterFrequency = waterFrequency;
    }
}