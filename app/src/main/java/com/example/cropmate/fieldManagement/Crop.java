package com.example.cropmate.fieldManagement;

public class Crop {

    private String name;
    private Double averageYield;
    private Integer growthTime;
    private String monthToPlant;
    private String monthToHarvest;
    private Integer waterFrequency;

    public Crop(String cropName, Double yield, Integer time, String plantMonth, String harvestMonth, Integer water){
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

    public Double getAverageYield() {
        return averageYield;
    }

    public void setAverageYield(Double averageYield) {
        if(averageYield == null) {
            throw new IllegalArgumentException("Average Yield cannot be null");
        }
        this.averageYield = averageYield;
    }

    public Integer getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(Integer growthTime) {
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

    public Integer getWaterFrequency() {
        return waterFrequency;
    }

    public void setWaterFrequency(Integer waterFrequency) {
        if(waterFrequency == null) {
            throw new IllegalArgumentException("Water Frequency cannot be null");
        }
        this.waterFrequency = waterFrequency;
    }
}