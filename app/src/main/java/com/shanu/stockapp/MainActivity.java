package com.shanu.stockapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.shanu.stockapp.adapter.StockSearchAdaptor;
import com.shanu.stockapp.entity.BestMatch;
import com.shanu.stockapp.entity.BestMatchBody;
import com.shanu.stockapp.entity.GlobalQuote;
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

/*        TabLayout stockTabLayout =  findViewById(R.id.stockInfoTab);
        stockTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                handleTabSelection(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                handleTabSelection(tab.getPosition());
            }
        });*/
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

    private void handleTabSelection(int position) {
        switch(position) {
            case 0:
                getPortFolioInfo();
                break;
            default:
                break;
        }
    }
    private void getPortFolioInfo() {
        Call<GlobalQuote> stockCall = stockAPI.getStockInfo("GLOBAL_QUOTE",
                "BEL.BSE", "G6ONJLPKW1FRF2M7");
        stockCall.enqueue(new Callback<GlobalQuote>() {
            @Override
            public void onResponse(Call<GlobalQuote> call, Response<GlobalQuote> response) {
                Log.d("Symbol", response.body().getGlobalQuote().get01Symbol());
                showPortFolio();
            }
            @Override
            public void onFailure(Call<GlobalQuote> call, Throwable t) {
                Log.d("Error Response Received", t.getMessage());
                stockCall.cancel();
            }
        });
    }
    private void showPortFolio() {
    }
}