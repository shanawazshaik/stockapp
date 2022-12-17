package com.shanu.stockapp.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shanu.stockapp.activity.StockDetailsActivity;
import com.shanu.stockapp.adapter.StockSearchAdaptor;
import com.shanu.stockapp.entity.BestMatchBody;
import com.shanu.stockapp.entity.StockQuote;

import java.util.ArrayList;

public class StockPyAPI {
    PyObject stockPyAPIInstance = null;
    static StockPyAPI stockPyAPI = null;
    public StockPyAPI() {
        try{
            PyObject stockAPIClass = Python.getInstance().getModule("StockAPI").get("StockAPI");
            if (stockAPIClass != null) {
                stockPyAPIInstance = stockAPIClass.call();
            }
        } catch (PyException pe) {
            Log.e("Unable to create stockAPI instance", pe.getMessage());
        }
    }
    public static StockPyAPI getInstance() {
        if (stockPyAPI == null) {
            stockPyAPI = new StockPyAPI();
        }

        return stockPyAPI;
    }
    public StockQuote getStockQuote(String symbol) {
        try {
            if (stockPyAPIInstance != null) {
                PyObject quoteResponse = stockPyAPIInstance.callAttr("getQuote", symbol);
                if (quoteResponse != null) {
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonObject object = (JsonObject) parser.parse(quoteResponse.toString());
                    StockQuote quote = gson.fromJson(object, StockQuote.class);
                    return quote;
                } else {
                    Log.d("getStockQuote", "quote response is null" );
                }
            } else {
                Log.d("getStockQuote", "stockPyAPIInstance is null" );
            }
        } catch (Exception pe) {
            Log.e("Unable to getQuote", pe.getMessage());
        }

        return null;
    }
    public void showStockQuote(Activity activity, Context context, String symbol) {
        Log.d("symbol from item click", symbol);
        StockQuote stockQuote = getInstance().getStockQuote(symbol);
        if (stockQuote == null) {
            Toast.makeText(context, "Unable to get Quote at the moment", Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            bundle.putCharSequence("symbol", stockQuote.getSymbol());
            bundle.putCharSequence("price", stockQuote.getLastPrice());
            bundle.putCharSequence("open", stockQuote.getOpen());
            bundle.putCharSequence("high", stockQuote.getDayHigh());
            bundle.putCharSequence("low", stockQuote.getDayLow());
            bundle.putCharSequence("volume", stockQuote.getTotalTradedVolume());
            bundle.putCharSequence("change", stockQuote.getChange());
            bundle.putCharSequence("change_percentage", stockQuote.getpChange());
            bundle.putCharSequence("previous_close", stockQuote.getPreviousClose());

            Intent stockInfoActivity = new Intent(context, StockDetailsActivity.class);
            stockInfoActivity.putExtras(bundle);
            activity.startActivity(stockInfoActivity);
        }
    }

    public void searchStocks(String query, Context context, ListView listView) {
        try {
            if (stockPyAPIInstance != null) {
                PyObject searchResults = stockPyAPIInstance.callAttr("getStocksDetails", query.toUpperCase());
                if (searchResults != null) {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonSearchResults = (JsonObject) parser.parse(searchResults.toString());
                    ArrayList<BestMatchBody> bestMatches = new ArrayList<>();
                    for (String symbol: jsonSearchResults.keySet()) {
                        BestMatchBody bestMatchBody = new BestMatchBody();
                        bestMatchBody.set1Symbol(symbol);
                        bestMatchBody.set2Name(jsonSearchResults.get(symbol).getAsString());
                        bestMatches.add(bestMatchBody);
                    }
                    StockSearchAdaptor adaptor
                            = new StockSearchAdaptor(context, bestMatches);
                    listView.setAdapter(adaptor);
                } else {
                    Log.d("searchStocks", "searchResults is empty" );
                    Toast.makeText(context, "Unable to get stock details at the moment", Toast.LENGTH_SHORT).show();
                    if (listView.getAdapter() != null) {
                        ((ArrayAdapter) listView.getAdapter()).clear();
                        ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                }
            } else {
                Log.d("searchStocks", "Unable to initialize stock python API" );
            }
        } catch (Exception pe) {
            Log.e("Unable to searchStocks", pe.getMessage());
        }
    }
}
