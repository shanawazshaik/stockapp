package com.shanu.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RESTInterface stockAPI = null;
    SearchView searchView = null;
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockAPI = RetrofitClient.getInstance().create(RESTInterface.class);
        searchView =  findViewById(R.id.searchViewId);
        listView =  findViewById(R.id.searchListId);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchStocks(newText);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BestMatchBody bestMatchBody = (BestMatchBody)listView.getItemAtPosition(i);
                Log.d("item click", bestMatchBody.get1Symbol());
                showStockQuote(bestMatchBody.get1Symbol());
            }
        });
    }

    private void searchStocks(String newText) {
        Log.d("new Text", newText);
        if (newText != null && !newText.isEmpty()) {
            Call<BestMatch> stockCall = stockAPI.searchStocks("SYMBOL_SEARCH",
                    newText, "G6ONJLPKW1FRF2M7");
            stockCall.enqueue(new Callback<BestMatch>() {
                @Override
                public void onResponse(Call<BestMatch> call, Response<BestMatch> response) {
                    if (response.body() != null) {
                        List<BestMatchBody> bestMatches = response.body().getBestMatches();

                        int size = bestMatches == null? 0: bestMatches.size();
                        if (size > 0) {
                            StockSearchAdaptor arr
                                    = new StockSearchAdaptor(
                                    getApplicationContext(),
                                    (ArrayList)bestMatches);
                            listView.setAdapter(arr);
                        } else {
                            listView.setAdapter(null);
                        }
                    } else {
                        listView.setAdapter(null);
                    }
                }
                @Override
                public void onFailure(Call<BestMatch> call, Throwable t) {
                    Log.d("Error Response Received", t.getMessage());
                    stockCall.cancel();
                }
            });
        } else {
            listView.setAdapter(null);
        }
    }

    private void showStockQuote(String symbol) {
        Log.d("symbol from item click", symbol);
        Call<GlobalQuote> stockCall = stockAPI.getStockInfo("GLOBAL_QUOTE",
                symbol, "G6ONJLPKW1FRF2M7");
        stockCall.enqueue(new Callback<GlobalQuote>() {
            @Override
            public void onResponse(Call<GlobalQuote> call, Response<GlobalQuote> response) {
                GlobalQuoteBody quote = response.body().getGlobalQuote();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("symbol", symbol);
                bundle.putCharSequence("price", quote.get05Price());
                bundle.putCharSequence("open", quote.get02Open());
                bundle.putCharSequence("high", quote.get03High());
                bundle.putCharSequence("low", quote.get04Low());
                bundle.putCharSequence("volume", quote.get06Volume());
                bundle.putCharSequence("change", quote.get09Change());
                bundle.putCharSequence("change_percentage", quote.get10ChangePercent());
                bundle.putCharSequence("previous_close", quote.get08PreviousClose());
                Intent stockInfoActivity = new Intent(MainActivity.this, StockDetailsActivity.class);
                stockInfoActivity.putExtras(bundle);
                startActivity(stockInfoActivity);
            }
            @Override
            public void onFailure(Call<GlobalQuote> call, Throwable t) {
                Log.d("Error Response Received", t.getMessage());
                stockCall.cancel();
            }
        });
    }
}