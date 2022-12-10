package com.shanu.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RESTInterface stockAPI = null;
    SearchView searchView = null;
    ListView listView = null;
    ProgressBar searchProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockAPI = RetrofitClient.getInstance().create(RESTInterface.class);
        searchView =  findViewById(R.id.searchViewId);
        listView =  findViewById(R.id.searchListId);
        searchProgressBar =  findViewById(R.id.progressId);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {
                    listView.setVisibility(ProgressBar.VISIBLE);
                    searchStocks(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && newText.length() >=3) {
                    searchProgressBar.setVisibility(ProgressBar.VISIBLE);
                    searchStocks(newText);
                }
                return false;
            }
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            BestMatchBody bestMatchBody = (BestMatchBody)listView.getItemAtPosition(i);
            if (bestMatchBody != null) {
                Log.d("item click", bestMatchBody.get1Symbol());
                ProgressBar quoteProgressBar =  view.findViewById(R.id.showQuoteProgressId);
                quoteProgressBar.setVisibility(ProgressBar.VISIBLE);
                showStockQuote(bestMatchBody.get1Symbol(), quoteProgressBar);
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
                                    getApplicationContext(),
                                    (ArrayList<BestMatchBody>) bestMatches);
                            listView.setAdapter(arr);
                        } else {
                            Toast.makeText(getApplicationContext(), "There is no such symbol", Toast.LENGTH_SHORT).show();
                            listView.setAdapter(null);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "There is no such symbol", Toast.LENGTH_SHORT).show();
                        listView.setAdapter(null);
                    }
                }
                @Override
                public void onFailure(@NonNull Call<BestMatch> call, @NonNull Throwable t) {
                    searchProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    Log.d("Error Response Received", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Failed to search"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    stockCall.cancel();
                }
            });
        } else {
            listView.setAdapter(null);
        }
    }

    private void showStockQuote(String symbol, ProgressBar quoteProgressBar) {
        Log.d("symbol from item click", symbol);
        Call<GlobalQuote> stockCall = stockAPI.getStockInfo("GLOBAL_QUOTE",
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

                    Intent stockInfoActivity = new Intent(MainActivity.this, StockDetailsActivity.class);
                    stockInfoActivity.putExtras(bundle);
                    Log.d("Starting Activity for stock details", response.message());
                    startActivity(stockInfoActivity);
                } else {
                    Log.d("Incorrect response", response.message());
                    Toast.makeText(getApplicationContext(), "Unable to get Quote, Try again", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(@NonNull Call<GlobalQuote> call, @NonNull Throwable t) {
                Log.d("Error Response Received", t.getMessage());
                quoteProgressBar.setVisibility(ProgressBar.GONE);
                Toast.makeText(getApplicationContext(), "Failed to get quote"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                stockCall.cancel();
            }
        });
    }
}