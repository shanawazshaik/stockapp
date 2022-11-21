package com.shanu.stockapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BestMatch {

    @SerializedName("bestMatches")
    @Expose
    private List<BestMatchBody> bestMatches = null;

    public List<BestMatchBody> getBestMatches() {
        return bestMatches;
    }

    public void setBestMatches(List<BestMatchBody> bestMatches) {
        this.bestMatches = bestMatches;
    }

}
