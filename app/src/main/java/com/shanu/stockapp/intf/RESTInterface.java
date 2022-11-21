package com.shanu.stockapp.intf;

import com.shanu.stockapp.entity.BestMatch;
import com.shanu.stockapp.entity.GlobalQuote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTInterface {
    @GET("/query")
    public Call<GlobalQuote> getStockInfo(@Query("function") String function,
                                          @Query("symbol") String symbol,
                                          @Query("apikey") String apiKey);
    @GET("/query")
    public Call<BestMatch> searchStocks(@Query("function") String function,
                                              @Query("keywords") String keywords,
                                              @Query("apikey") String apiKey);
}
