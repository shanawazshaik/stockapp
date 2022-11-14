package com.shanu.stockapp.intf;

import com.shanu.stockapp.entity.GlobalQuote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTInterface {
    @GET("/query")
    public Call<GlobalQuote> getStockInfo(@Query("function") String function,
                                          @Query("symbol") String symbol,
                                          @Query("apikey") String apiKey);
}
