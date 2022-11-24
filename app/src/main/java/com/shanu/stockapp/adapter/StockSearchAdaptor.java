package com.shanu.stockapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.shanu.stockapp.R;
import com.shanu.stockapp.entity.BestMatchBody;

import java.util.ArrayList;
import java.util.List;

public class StockSearchAdaptor extends ArrayAdapter<BestMatchBody> {
    public StockSearchAdaptor(@NonNull Context context, @NonNull ArrayList<BestMatchBody> objects) {
        super(context, R.layout.list_item, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BestMatchBody bestMatchBody = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView symbol = (TextView) convertView.findViewById(R.id.symbol);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView region = (TextView) convertView.findViewById(R.id.region);
        TextView open = (TextView) convertView.findViewById(R.id.open);
        TextView close = (TextView) convertView.findViewById(R.id.close);
        // Populate the data into the template view using the data object
        symbol.setText(bestMatchBody.get1Symbol());
        type.setText(bestMatchBody.get3Type());
        region.setText(bestMatchBody.get4Region());
        open.setText(bestMatchBody.get5MarketOpen());
        close.setText(bestMatchBody.get6MarketClose());
        // Return the completed view to render on screen

        return convertView;
    }

}
