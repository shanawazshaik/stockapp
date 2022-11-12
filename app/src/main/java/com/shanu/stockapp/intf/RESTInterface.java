package com.shanu.stockapp.intf;

import com.shanu.stockapp.entity.Stock;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTInterface {
    @FormUrlEncoded
    @GET("/query")
    public Call<Stock> getStockInfo(@Query("function") String function, @Query("symbol") String symbol, @Query("apiKey") String apiKey);
}
