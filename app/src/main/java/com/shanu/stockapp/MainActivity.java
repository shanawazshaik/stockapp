package com.shanu.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.shanu.stockapp.entity.Stock;
import com.shanu.stockapp.intf.RESTInterface;
import com.shanu.stockapp.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RESTInterface stockAPI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockAPI = RetrofitClient.getInstance().create(RESTInterface.class);
        /*TabItem portFolioTab = (TabItem) findViewById(R.id.portfolioTab);
        portFolioTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getPortFolioInfo();
            }
        });*/

    }

    private void getPortFolioInfo() {
        Call<Stock> stockCall = stockAPI.getStockInfo("GLOBAL_QUOTE", "IBM", "demo");
        stockCall.enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(Call<Stock> call, Response<Stock> response) {
                showPortFolio();
            }

            @Override
            public void onFailure(Call<Stock> call, Throwable t) {
                stockCall.cancel();
            }
        });
    }

    private void showPortFolio() {
        TableLayout tl = (TableLayout) findViewById(R.id.portfolioTable);
    }
}