package com.shanu.stockapp.entity;

public class Stock {
    private String name;
    private float purchasePrice;
    private float price;
    private float priceToEarnings;
    private int units;
    private long investment;
    private long currentValue;

    public Stock(String name, float price, float priceToEarnings) {
        this.name = name;
        this.price = price;
        this.priceToEarnings = priceToEarnings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceToEarnings() {
        return priceToEarnings;
    }

    public void setPriceToEarnings(float priceToEarnings) {
        this.priceToEarnings = priceToEarnings;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public long getInvestment() {
        return investment;
    }

    public void setInvestment(long investment) {
        this.investment = investment;
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;
    }
}
