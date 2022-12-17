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
        TextView symbol =  convertView.findViewById(R.id.symbol);
        TextView name =  convertView.findViewById(R.id.name);
        // Populate the data into the template view using the data object
        symbol.setText(bestMatchBody.get1Symbol());
        name.setText(bestMatchBody.get2Name());
        // Return the completed view to render on screen

        return convertView;
    }

}
