package com.example.passwordlocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EnterPassword extends AppCompatActivity {

    TextInputEditText checkPassword;
    TextView userName , appName , go_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        checkPassword = findViewById(R.id.check_password);
        userName  = findViewById(R.id.check_username);
        appName = findViewById(R.id.check_appname);
        go_btn = findViewById(R.id.go_btn);

        Intent intent = getIntent();

        userName.setText(intent.getStringExtra("userName"));
        appName.setText(intent.getStringExtra("appName"));

        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String password = settings.getString("password", "none");

                if(!checkPassword.getText().toString().trim().isEmpty()){
                    if(checkPassword.getText().toString().equals(password)){
                        Intent intent1 = new Intent(getApplicationContext() , showInfo.class);
                        intent1.putExtra("userName",userName.getText().toString());
                        startActivity(intent1);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext() , "Wrong password",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext() , "Enter the password first" , Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}