package com.example.ivank.wsti_firstprogramandroidstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText editText;
    private Intent intentFromMainActivity;
    private String[] activityParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = findViewById(R.id.editText);
    }

    private String[] getParamsFromMainActivity() {

        String[] localParams = new String[2];

        intentFromMainActivity = getIntent();
        Bundle paramsFromMainActivity = intentFromMainActivity.getBundleExtra("paramsFromMainActivity");
        localParams[0] = paramsFromMainActivity.getString("result", "-");

        return localParams;

    }

    @Override
    protected void onResume() {

        super.onResume();
        activityParams = getParamsFromMainActivity();
        editText.setText(activityParams[0]);

    }

    @Override
    public void onBackPressed() {

        String result = editText.getText().toString();
        Bundle resultPackageBundle = new Bundle();
        resultPackageBundle.putString("result", result);
        intentFromMainActivity.putExtra("paramsFromSecondActivity", resultPackageBundle);
        setResult(RESULT_OK, intentFromMainActivity);
        this.finish();
//        super.onBackPressed();

    }

}
