package com.shanu.stockapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalQuote {

    @SerializedName("Global Quote")
    @Expose
    private GlobalQuoteBody globalQuote;

    public GlobalQuoteBody getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuoteBody globalQuote) {
        this.globalQuote = globalQuote;
    }

}
