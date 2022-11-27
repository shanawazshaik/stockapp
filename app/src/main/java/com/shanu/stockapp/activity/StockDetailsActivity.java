package com.shanu.stockapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shanu.stockapp.R;

public class StockDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_detail_layout);
        TextView price = findViewById(R.id.price);
        TextView symbol =  findViewById(R.id.symbol);
        TextView open =  findViewById(R.id.open);
        TextView high =  findViewById(R.id.high);
        TextView low =  findViewById(R.id.low);
        TextView previousClose =  findViewById(R.id.previousClose);
        TextView change =  findViewById(R.id.change);
        TextView changePercentage = findViewById(R.id.changePercentage);
        TextView volume =  findViewById(R.id.volume);
        Bundle stockInfo = getIntent().getExtras();
        if(stockInfo != null) {
            Log.d("In the Activity for stock details", stockInfo.toString());
            symbol.setText(stockInfo.getString("symbol", ""));
            price.setText(String.format("%s\n%s", getString(R.string.price), stockInfo.getString("price", "")));
            high.setText(String.format("%s\n%s", getString(R.string.high), stockInfo.getString("high", "")));
            low.setText(String.format("%s\n%s", getString(R.string.low), stockInfo.getString("low", "")));
            change.setText(String.format("%s\n%s", getString(R.string.change), stockInfo.getString("change", "")));
            changePercentage.setText(String.format("%s\n%s", getString(R.string.change_percentage), stockInfo.getString("change_percentage", "")));
            volume.setText(String.format("%s\n%s", getString(R.string.volume), stockInfo.getString("volume", "")));
            open.setText(String.format("%s\n%s", getString(R.string.open), stockInfo.getString("open", "")));
            previousClose.setText(String.format("%s\n%s", getString(R.string.previous_close), stockInfo.getString("previous_close", "")));
        } else {
            Log.d("in stock details activity", "bundle is null");
        }
    }
}
