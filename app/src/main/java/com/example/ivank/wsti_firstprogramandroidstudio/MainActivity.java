package com.example.ivank.wsti_firstprogramandroidstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RadioGroup radioGroupFirstWord;
    private RadioButton radioButtonHello, radioButtonWorld;
    private CheckBox checkBox;
    private Button button;
    private Switch switch1;
    private String firstText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroupFirstWord = findViewById(R.id.radioGroupFirstWord);

        radioGroupFirstWord.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonHello) {
                    firstText = "Hello";
                } else if (checkedId == R.id.radioButtonWorld) {
                    firstText = "World";
                }
            }
        });

        radioButtonHello = findViewById(R.id.radioButtonHello);
        radioButtonWorld = findViewById(R.id.radioButtonWorld);
        checkBox = findViewById(R.id.checkBox);
        button = findViewById(R.id.button);
        switch1 = findViewById(R.id.switch1);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {

                    textView.setText(firstText + ", checkbox is checked");

                } else {

                    textView.setText(firstText);

                }

                if (switch1.isChecked()) {

                    String text = (String) textView.getText();

                    if (checkBox.isChecked()) {

                        textView.setText(text + " and switch is checked ;)");

                    } else {

                        textView.setText(text + ", switch is checked ;)");

                    }

                }

            }
        });

    }
}
