package com.example.passwordlocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    TextView textView;
    static int num;
    static String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textView = findViewById(R.id.letsStart_txt);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Opened", Context.MODE_PRIVATE);
        int isOpened = sharedPreferences.getInt("isRun",0);

        Intent intent = getIntent();
        num = intent.getIntExtra("addOption",0);

        if(isOpened == 1 && num == 0){
            Intent intent1 = new Intent(getApplicationContext() , MainActivity.class);
            startActivity(intent1);
            finish();
        }

        if(isOpened == 1 && num == 2){
            // do nothing
            textView.setText("Choose an application");
        }

    }

    public void getAppName(View view) {
        TextView textView = (TextView) view;
        appName = textView.getText().toString();

        Intent intent = new Intent(getApplicationContext() , EntryActivity.class);
        intent.putExtra("appName",appName);
        startActivity(intent);
    }

}