package com.shanu.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.shanu.stockapp.activity.LoginActivity;
import com.shanu.stockapp.entity.BestMatchBody;
import com.shanu.stockapp.impl.StockPyAPI;
import com.shanu.stockapp.networking.PythonClient;

public class MainActivity extends AppCompatActivity {
    SearchView searchView = null;
    ListView listView = null;
    ProgressBar searchProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PythonClient.initPython(getApplicationContext());
        initStockViews();
        setSearchStockListener();
        setShowQuoteListener();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                return logout();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private boolean logout() {
        Intent logoutActivity = new Intent(MainActivity.this, LoginActivity.class);
        logoutActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logoutActivity);
        finish();
        return true;
    }
    private void initStockViews() {
        searchView = findViewById(R.id.searchViewId);
        listView = findViewById(R.id.searchListId);
        searchProgressBar = findViewById(R.id.progressId);
    }

    private void setSearchStockListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {
                    searchProgressBar.setVisibility(ProgressBar.VISIBLE);
                    StockPyAPI.getInstance().searchStocks(query, getApplicationContext(), listView);
                    searchProgressBar.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && newText.length() >= 3) {
                    searchProgressBar.setVisibility(View.VISIBLE);
                    StockPyAPI.getInstance().searchStocks(newText, getApplicationContext(), listView);
                    searchProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void setShowQuoteListener() {
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            BestMatchBody bestMatchBody = (BestMatchBody) listView.getItemAtPosition(i);
            if (bestMatchBody != null) {
                ProgressBar quoteProgressBar = view.findViewById(R.id.showQuoteProgressId);
                quoteProgressBar.setVisibility(ProgressBar.VISIBLE);
                StockPyAPI.getInstance().showStockQuote(this, MainActivity.this, bestMatchBody.get1Symbol());
               quoteProgressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }
}