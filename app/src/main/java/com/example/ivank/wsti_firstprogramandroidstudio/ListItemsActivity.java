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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListItemsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
//    private String[] countryList = {};

    List<String> countryList = new ArrayList<>();

    final String LOG_TAG = "myLogs";
    final String FILENAME = "karbashevskyi";

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
        getAllInformation();

        listView = findViewById(R.id.listView);

        list = new ArrayList<String>(countryList);


        arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_item, R.id.textViewForItem, list);
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
                        saveInformation();
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
                        saveInformation();
                        Toast.makeText(ListItemsActivity.this, "You delete : " + item, Toast.LENGTH_SHORT).show();


                    }

                });
                alertDialog.show();

            }

        });

    }

    private void saveInformation() {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            for (int i=0;i<arrayAdapter.getCount();i++){
                bw.write(arrayAdapter.getItem(i) + ",");
            }
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан, count: " + arrayAdapter.getCount());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllInformation() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";
            boolean haveData = false;
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
                String test[] = str.split(",");
                if (test.length > 0) {
                    haveData  = true;
                    Log.d(LOG_TAG, test.length + " - count country saved");
                    for (int i = 0; i < test.length; i++) {
                        countryList.add(test[i] + "");
                    }
                }
            }
            Log.d(LOG_TAG, haveData ? "true" : "false");
            if (!haveData) {
                countryList.add("India");
                countryList.add("China");
                countryList.add("australia");
                countryList.add("Portugle");
                countryList.add("America");
                countryList.add("NewZealand");
                countryList.add("China");
                countryList.add("australia");
                countryList.add("Portugle");
                countryList.add("America");
                countryList.add("NewZealand");
                countryList.add("China");
                countryList.add("australia");
                countryList.add("Portugle");
                countryList.add("America");
                countryList.add("NewZealand");
                countryList.add("China");
                countryList.add("australia");
                countryList.add("Portugle");
                countryList.add("America");
                countryList.add("NewZealand");
                countryList.add("Karbashevskyi");
                saveInformation();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
