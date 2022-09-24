package com.example.passwordlocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePassword extends AppCompatActivity {

    TextInputEditText password_txt , confirm_txt;
    Switch aSwitch;
    TextView next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        password_txt = findViewById(R.id.create_password);
        confirm_txt = findViewById(R.id.confirm_pass);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Opened", Context.MODE_PRIVATE);
        String isFirstTime = sharedPreferences.getString("firstTime","yes");

        if(isFirstTime.equals("no")){
            Intent intent = new Intent(getApplicationContext() , MainActivity.class);
            startActivity(intent);
            finish();
        }

        next_btn = findViewById(R.id.next_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validPasswords()){

                    SharedPreferences passwordForApp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = passwordForApp.edit();

                    String value_switch;

                    editor.putString("password",password_txt.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext() , "Passwords didn't matched",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }

    private boolean validPasswords() {
        if(password_txt.getText().toString().trim().length() == 0){
            password_txt.setError("Enter a password");
            return false;
        }
        if(confirm_txt.getText().toString().trim().length() == 0){
            confirm_txt.setError("Confirm password");
            return false;
        }
        if(!password_txt.getText().toString().equals(confirm_txt.getText().toString())){
            Toast.makeText(getApplicationContext() , "Passwords didn't matched",Toast.LENGTH_SHORT).show();
            confirm_txt.setError("Passwords didn't matched");
            return false;
        }

        password_txt.setError(null);
        confirm_txt.setError(null);

        return true;

    }
}