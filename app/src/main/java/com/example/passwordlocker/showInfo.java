package com.example.passwordlocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class showInfo extends AppCompatActivity {

    DBHelper dbHelper;

    private TextView UserName_txt , Password_txt , AppName_txt , desc_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        UserName_txt = findViewById(R.id.info_userName);
        Password_txt = findViewById(R.id.info_password);
        AppName_txt = findViewById(R.id.info_appName);
        desc_txt = findViewById(R.id.info_desc);



        dbHelper = new DBHelper(getApplicationContext());

        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");

        InfromationClass infromationClass = dbHelper.getItemValue(userName);


        UserName_txt.setText(infromationClass.getUserName());
        AppName_txt.setText(infromationClass.getApplicationName());
        Password_txt.setText(infromationClass.getPassword());
        desc_txt.setText(infromationClass.getDescription());





    }
}