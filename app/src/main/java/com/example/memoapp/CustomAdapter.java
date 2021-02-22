package com.example.memoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(Context context, ArrayList<Map<String, String>> id_titleText) {
        super(context, 0, id_titleText);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Get the data item for this position

        // Lookup view for data population

        // Populate the data into the template view using the data object

        // Return the completed view to render on screen
        return convertView;
    }
}
