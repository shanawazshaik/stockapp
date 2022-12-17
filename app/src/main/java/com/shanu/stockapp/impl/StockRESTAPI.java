package com.shanu.stockapp.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.chaquo.python.PyException;
import com.shanu.stockapp.activity.StockDetailsActivity;
import com.shanu.stockapp.adapter.StockSearchAdaptor;
import com.shanu.stockapp.entity.BestMatch;
import com.shanu.stockapp.entity.BestMatchBody;
import com.shanu.stockapp.entity.GlobalQuote;
import com.shanu.stockapp.entity.GlobalQuoteBody;
import com.shanu.stockapp.intf.RESTInterface;
import com.shanu.stockapp.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockRESTAPI {
    static RESTInterface stockRESTAPI = null;

    public StockRESTAPI() {
        try{
            stockRESTAPI = RetrofitClient.getInstance().create(RESTInterface.class);
        } catch (PyException pe) {
            Log.e("Unable to create stockRESTAPI instance", pe.getMessage());
        }
    }
    public void showStockQuote(Activity activity, Context context, String symbol, ProgressBar quoteProgressBar) {
        Log.d("symbol from item click", symbol);
        Call<GlobalQuote> stockCall = stockRESTAPI.getStockInfo("GLOBAL_QUOTE",
                symbol, "G6ONJLPKW1FRF2M7");
        stockCall.enqueue(new Callback<GlobalQuote>() {
            @Override
            public void onResponse(@NonNull Call<GlobalQuote> call, @NonNull Response<GlobalQuote> response) {
                quoteProgressBar.setVisibility(ProgressBar.GONE);
                if (response.body() != null && response.body().getGlobalQuote() != null) {
                    GlobalQuoteBody quote = response.body().getGlobalQuote();
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("symbol", quote.get01Symbol());
                    bundle.putCharSequence("price", quote.get05Price());
                    bundle.putCharSequence("open", quote.get02Open());
                    bundle.putCharSequence("high", quote.get03High());
                    bundle.putCharSequence("low", quote.get04Low());
                    bundle.putCharSequence("volume", quote.get06Volume());
                    bundle.putCharSequence("change", quote.get09Change());
                    bundle.putCharSequence("change_percentage", quote.get10ChangePercent());
                    bundle.putCharSequence("previous_close", quote.get08PreviousClose());

                    Intent stockInfoActivity = new Intent(context, StockDetailsActivity.class);
                    stockInfoActivity.putExtras(bundle);
                    Log.d("Starting Activity for stock details", response.message());
                    activity.startActivity(stockInfoActivity);
                } else {
                    Log.d("Incorrect response", response.message());
                    Toast.makeText(context, "Unable to get Quote, Try again", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(@NonNull Call<GlobalQuote> call, @NonNull Throwable t) {
                Log.d("Error Response Received", t.getMessage());
                quoteProgressBar.setVisibility(ProgressBar.GONE);
                Toast.makeText(context, "Failed to get quote"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                stockCall.cancel();
            }
        });
    }
    public void searchStocks(String newText, ProgressBar searchProgressBar, Context context, ListView listView) {
        Log.d("new Text", newText);
        if (newText != null && !newText.isEmpty()) {
            Call<BestMatch> stockCall = stockRESTAPI.searchStocks("SYMBOL_SEARCH",
                    newText, "G6ONJLPKW1FRF2M7");
            stockCall.enqueue(new Callback<BestMatch>() {
                @Override
                public void onResponse(@NonNull Call<BestMatch> call, @NonNull Response<BestMatch> response) {
                    searchProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    if (response.body() != null) {
                        List<BestMatchBody> bestMatches = response.body().getBestMatches();
                        if(bestMatches != null && !bestMatches.isEmpty()) {
                            bestMatches = bestMatches.stream().filter(bestMatchBody -> bestMatchBody.get4Region().contains("India")).collect(Collectors.toList());
                        }
                        int size = bestMatches == null? 0: bestMatches.size();
                        if (size > 0) {
                            StockSearchAdaptor arr
                                    = new StockSearchAdaptor(
                                    context,
                                    (ArrayList<BestMatchBody>) bestMatches);
                            listView.setAdapter(arr);
                        } else {
                            Toast.makeText(context, "There is no such symbol", Toast.LENGTH_SHORT).show();
                            listView.setAdapter(null);
                        }
                    } else {
                        Toast.makeText(context, "There is no such symbol", Toast.LENGTH_SHORT).show();
                        listView.setAdapter(null);
                    }
                }
                @Override
                public void onFailure(@NonNull Call<BestMatch> call, @NonNull Throwable t) {
                    searchProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    Log.d("Error Response Received", t.getMessage());
                    Toast.makeText(context, "Failed to search"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    stockCall.cancel();
                }
            });
        } else {
            listView.setAdapter(null);
        }
    }
}
