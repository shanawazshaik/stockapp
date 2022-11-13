package com.shanu.stockapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalQuote {

    @SerializedName("Global Quote")
    @Expose
    private GlobalQuote_Body globalQuote;

    public GlobalQuote_Body getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuote_Body globalQuote) {
        this.globalQuote = globalQuote;
    }

}
