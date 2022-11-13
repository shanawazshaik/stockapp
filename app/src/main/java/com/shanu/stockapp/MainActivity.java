package com.shanu.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.shanu.stockapp.entity.GlobalQuote;
import com.shanu.stockapp.intf.RESTInterface;
import com.shanu.stockapp.networking.RetrofitClient;

import java.io.IOException;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RESTInterface stockAPI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockAPI = RetrofitClient.getInstance().create(RESTInterface.class);
        TabLayout tabLayout =  findViewById(R.id.stockInfoTab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        getPortFolioInfo();
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("tab is re clicked",  (String) tab.getText());
            }
        });


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