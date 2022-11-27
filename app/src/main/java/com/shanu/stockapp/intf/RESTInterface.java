package com.shanu.stockapp.intf;

import com.shanu.stockapp.entity.BestMatch;
import com.shanu.stockapp.entity.GlobalQuote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTInterface {
    @GET("/query")
    Call<GlobalQuote> getStockInfo(@Query("function") String function,
                                   @Query("symbol") String symbol,
                                   @Query("apikey") String apiKey);
    @GET("/query")
    Call<BestMatch> searchStocks(@Query("function") String function,
                                              @Query("keywords") String keywords,
                                              @Query("apikey") String apiKey);
}
