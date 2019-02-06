package com.example.ivank.wsti_firstprogramandroidstudio;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RadioGroup radioGroupFirstWord;
    private RadioButton radioButtonHello, radioButtonWorld;
    private CheckBox checkBox;
    private Button button;
    private Button buttonOpenContactList;
    private Button buttonOpenSecondPage;
    private Button buttonSelectImage;
    private Switch switch1;
    private ProgressBar progressBar;
    private String firstText = "";
    private ImageView imageView;
    private Button buttonOpenGoogleMaps;
    private Button buttonOpenTablePage;

    private static final TimeInterpolator GAUGE_ANIMATION_INTERPOLATOR = new DecelerateInterpolator(2);
    private static final int MAX_LEVEL = 100;
    private static final long GAUGE_ANIMATION_DURATION = 2500;

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

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        buttonOpenTablePage = findViewById(R.id.buttonOpenTablePage);
        buttonOpenGoogleMaps = findViewById(R.id.buttonOpenGoogleMaps);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonOpenContactList = findViewById(R.id.buttonOpenContactList);
        buttonOpenSecondPage = findViewById(R.id.buttonOpenSecondPage);
        switch1 = findViewById(R.id.switch1);
        textView = findViewById(R.id.textView);

        buttonOpenSecondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ListItemsActivity.class));

            }
        });

        buttonOpenTablePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TableLayoutActivity.class));

            }
        });

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        buttonOpenGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Search for restaurants nearby
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + textView.getText());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        buttonOpenContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "2";
                runner.execute(sleepTime);

                final ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, MAX_LEVEL);
                animator.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
                animator.setDuration(GAUGE_ANIMATION_DURATION);
                animator.start();

            }
        });

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


    public void goSecondPage(View view) {

        Intent goSecondPageIntent = new Intent(getApplicationContext(), SecondActivity.class);
        Bundle packageParams = new Bundle();
        packageParams.putString("result", textView.getText().toString());

        goSecondPageIntent.putExtra("paramsFromMainActivity", packageParams);

        startActivityForResult(goSecondPageIntent, 1111);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1111 && resultCode == RESULT_OK) {

            Bundle localBundle = data.getBundleExtra("paramsFromSecondActivity");
            textView.setText(localBundle.getString("result", "-"));

        } else if (requestCode == 0 && resultCode == RESULT_OK) {

            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {

            progressBar.setProgress(0);
            Uri contactData = data.getData();
            Cursor c = getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst()) {

                int phoneIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String num = c.getString(phoneIndex);
                String phoneName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(MainActivity.this, "Number=" + num + " Name=" + phoneName, Toast.LENGTH_LONG).show();
                textView.setText(phoneName + ", " + num);

            }

        }

    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Loading..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pickContact, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            textView.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Loading...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            textView.setText(text[0]);

        }
    }

}
