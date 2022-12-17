package com.shanu.stockapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockQuote {

    @SerializedName("averagePrice")
    @Expose
    private String averagePrice;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("closePrice")
    @Expose
    private String closePrice;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("dayHigh")
    @Expose
    private String dayHigh;
    @SerializedName("dayLow")
    @Expose
    private String dayLow;
    @SerializedName("extremeLossMargin")
    @Expose
    private String extremeLossMargin;
    @SerializedName("faceValue")
    @Expose
    private String faceValue;
    @SerializedName("high52")
    @Expose
    private String high52;
    @SerializedName("lastPrice")
    @Expose
    private String lastPrice;
    @SerializedName("low52")
    @Expose
    private String low52;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("pChange")
    @Expose
    private String pChange;
    @SerializedName("previousClose")
    @Expose
    private String previousClose;
    @SerializedName("series")
    @Expose
    private String series;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("totalBuyQuantity")
    @Expose
    private String totalBuyQuantity;
    @SerializedName("totalSellQuantity")
    @Expose
    private String totalSellQuantity;
    @SerializedName("totalTradedValue")
    @Expose
    private String totalTradedValue;
    @SerializedName("totalTradedVolume")
    @Expose
    private String totalTradedVolume;
    @SerializedName("varMargin")
    @Expose
    private String varMargin;

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(String dayHigh) {
        this.dayHigh = dayHigh;
    }

    public String getDayLow() {
        return dayLow;
    }

    public void setDayLow(String dayLow) {
        this.dayLow = dayLow;
    }

    public String getExtremeLossMargin() {
        return extremeLossMargin;
    }

    public void setExtremeLossMargin(String extremeLossMargin) {
        this.extremeLossMargin = extremeLossMargin;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public String getHigh52() {
        return high52;
    }

    public void setHigh52(String high52) {
        this.high52 = high52;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getLow52() {
        return low52;
    }

    public void setLow52(String low52) {
        this.low52 = low52;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getpChange() {
        return pChange;
    }

    public void setpChange(String pChange) {
        this.pChange = pChange;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    public void setTotalBuyQuantity(String totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public String getTotalSellQuantity() {
        return totalSellQuantity;
    }

    public void setTotalSellQuantity(String totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
    }

    public String getTotalTradedValue() {
        return totalTradedValue;
    }

    public void setTotalTradedValue(String totalTradedValue) {
        this.totalTradedValue = totalTradedValue;
    }

    public String getTotalTradedVolume() {
        return totalTradedVolume;
    }

    public void setTotalTradedVolume(String totalTradedVolume) {
        this.totalTradedVolume = totalTradedVolume;
    }

    public String getVarMargin() {
        return varMargin;
    }

    public void setVarMargin(String varMargin) {
        this.varMargin = varMargin;
    }

    @Override
    public String toString() {
        return "StockQuote{" +
                "averagePrice='" + averagePrice + '\'' +
                ", change='" + change + '\'' +
                ", closePrice='" + closePrice + '\'' +
                ", companyName='" + companyName + '\'' +
                ", dayHigh='" + dayHigh + '\'' +
                ", dayLow='" + dayLow + '\'' +
                ", extremeLossMargin='" + extremeLossMargin + '\'' +
                ", faceValue='" + faceValue + '\'' +
                ", high52='" + high52 + '\'' +
                ", lastPrice='" + lastPrice + '\'' +
                ", low52='" + low52 + '\'' +
                ", open='" + open + '\'' +
                ", pChange='" + pChange + '\'' +
                ", previousClose='" + previousClose + '\'' +
                ", series='" + series + '\'' +
                ", symbol='" + symbol + '\'' +
                ", totalBuyQuantity='" + totalBuyQuantity + '\'' +
                ", totalSellQuantity='" + totalSellQuantity + '\'' +
                ", totalTradedValue='" + totalTradedValue + '\'' +
                ", totalTradedVolume='" + totalTradedVolume + '\'' +
                ", varMargin='" + varMargin + '\'' +
                '}';
    }
}
