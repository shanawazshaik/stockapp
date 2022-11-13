package com.shanu.stockapp.intf;

import com.shanu.stockapp.entity.GlobalQuote;
import com.shanu.stockapp.entity.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTInterface {
    @GET("/query")
    public Call<ResponseBody> getStockInfo(@Query("function") String function,
                                          @Query("symbol") String symbol, @Query("apiKey") String apiKey);
    @GET("api/users/2")
    public Call<ResponseBody> getUser();
}
