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
            String changeStr = stockInfo.getString("change", "");
            try {
                float changeFloat = Float.parseFloat(changeStr);
                change.setText(String.format("%s\n%s", getString(R.string.change), changeFloat));
                if (changeFloat >= 0) {
                    change.setTextColor(getColor(R.color.green));
                } else {
                    change.setTextColor(getColor(R.color.red));
                }
            } catch (Exception e) {
                // value is String eg: 'None'
                change.setText(String.format("%s\n%s", getString(R.string.change), changeStr));
            }

            String changePercentageStr = stockInfo.getString("change_percentage", "");
            changePercentage.setText(String.format("%s\n%s", getString(R.string.change_percentage), changePercentageStr));
            if (changePercentageStr.startsWith("-")) {
                changePercentage.setTextColor(getColor(R.color.red));
            } else {
                changePercentage.setTextColor(getColor(R.color.green));
            }
            volume.setText(String.format("%s\n%s", getString(R.string.volume), stockInfo.getString("volume", "")));
            open.setText(String.format("%s\n%s", getString(R.string.open), stockInfo.getString("open", "")));
            previousClose.setText(String.format("%s\n%s", getString(R.string.previous_close), stockInfo.getString("previous_close", "")));
        } else {
            Log.d("in stock details activity", "bundle is null");
        }
    }
}
