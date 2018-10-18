package com.example.ivank.wsti_firstprogramandroidstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListItemsActivity extends AppCompatActivity {

    ListView listView;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand", "China", "australia", "Portugle", "America", "NewZealand", "China", "australia", "Portugle", "America", "NewZealand", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_item, R.id.textViewForItem, countryList);
        listView.setAdapter(arrayAdapter);

    }
}
