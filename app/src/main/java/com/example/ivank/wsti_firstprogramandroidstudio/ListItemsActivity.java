package com.example.ivank.wsti_firstprogramandroidstudio;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ListItemsActivity extends AppCompatActivity {

    private ListView listView;
    private String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand", "China", "australia", "Portugle", "America", "NewZealand", "China", "australia", "Portugle", "America", "NewZealand", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items_list, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        listView = findViewById(R.id.listView);
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(countryList));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_item, R.id.textViewForItem, list);
        listView.setAdapter(arrayAdapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setTitle("Title");

// Set up the input
                final EditText input = new EditText(ListItemsActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.add(input.getText().toString());
                        Toast.makeText(ListItemsActivity.this, "You added new item to list, it`s : " + input.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final String item = list.get(position);

                AlertDialog alertDialog = new AlertDialog.Builder(ListItemsActivity.this).create();
                alertDialog.setTitle("Delete " + item);
                alertDialog.setMessage("Are you sure you want to delete this item?");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        list.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(ListItemsActivity.this, "You delete : " + item, Toast.LENGTH_SHORT).show();


                    }

                });
                alertDialog.show();

            }

        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.back_to_home:
                super.onBackPressed();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }


}
