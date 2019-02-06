package com.example.ivank.wsti_firstprogramandroidstudio;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TableLayoutActivity extends AppCompatActivity {

    private Context context = null;
    private int countRow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        setTitle("Таблиця з даними");

        // Get TableLayout object in layout xml.
        final TableLayout tableLayout = findViewById(R.id.table_layout_table);

        context = getApplicationContext();

        // Get add table row button.
        Button buttonAdd = findViewById(R.id.buttonAdd);
        final EditText editTextTitle = findViewById(R.id.editTextTitle);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countRow++;

                // Create a new table row.
                TableRow tableRow = new TableRow(context);

                // Set new table row layout parameters.
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);

                // Add a TextView in the first column.
                TextView textView = new TextView(context);
                textView.setText("" + countRow);
                textView.setTextColor(Color.BLACK);
                tableRow.addView(textView, 0);

                // Add a button in the second column
                TextView textViewTitle = new TextView(context);
                textViewTitle.setText(editTextTitle.getText());
                textViewTitle.setTextColor(Color.BLACK);
                tableRow.addView(textViewTitle, 1);

                // Add a checkbox in the third column.
                CheckBox checkBox = new CheckBox(context);
                checkBox.setText("Delete");
                checkBox.setTextColor(Color.BLACK);
                tableRow.addView(checkBox, 2);

                tableLayout.addView(tableRow);

            }
        });


        // Get delete table row button.
        Button deleteRowButton = findViewById(R.id.buttonDeleteAllChecked);
        deleteRowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Get table row count.
                int rowCount = tableLayout.getChildCount();

                // Save delete row number list.
                List<Integer> deleteRowNumberList = new ArrayList<Integer>();

                // Loope each table rows.
                for(int i =0 ;i < rowCount;i++) {

                    // Get table row.
                    View rowView = tableLayout.getChildAt(i);

                    if(rowView instanceof TableRow) {
                        TableRow tableRow = (TableRow)rowView;

                        // Get row column count.
                        int columnCount = tableRow.getChildCount();

                        // Loop all columns in row.
                        for(int j = 0;j<columnCount;j++) {

                            View columnView = tableRow.getChildAt(j);

                            if(columnView instanceof CheckBox) {

                                // If columns is a checkbox and checked then save the row number in list.
                                CheckBox checkboxView = (CheckBox)columnView;

                                if(checkboxView.isChecked()) {

                                    deleteRowNumberList.add(i);
                                    break;

                                }

                            }

                        }

                    }

                }

                // Remove all rows by the selected row number.
                for(int rowNumber :deleteRowNumberList) {

                    tableLayout.removeViewAt(rowNumber);

                }
            }

        });

    }

}
