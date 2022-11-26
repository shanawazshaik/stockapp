package com.shanu.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
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
            symbol.setText(stockInfo.getString("symbol", ""));
            price.setText(getString(R.string.price) + "\n" + stockInfo.getString("price", ""));
            high.setText(getString(R.string.high) + "\n" + stockInfo.getString("high", ""));
            low.setText(getString(R.string.low) + "\n" + stockInfo.getString("low", ""));
            change.setText(getString(R.string.change) + "\n" + stockInfo.getString("change", ""));
            changePercentage.setText(getString(R.string.change_percentage) + "\n" + stockInfo.getString("change_percentage", ""));
            volume.setText(getString(R.string.volume) + "\n" + stockInfo.getString("volume", ""));
            open.setText(getString(R.string.open) + "\n" + stockInfo.getString("open", ""));
            previousClose.setText(getString(R.string.previous_close) + "\n" + stockInfo.getString("previous_close", ""));
        }
    }
}
