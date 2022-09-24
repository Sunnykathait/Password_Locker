package com.example.passwordlocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EntryActivity extends AppCompatActivity {

    private long backPressedTime;

    String applicationName;

    LinearLayout LL_appName_edt;
    TextView appName_txt , add_btn;
    EditText userName_edit , desc_edit , applicationName_edit;
    TextInputEditText password_edit , conf_password_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        LL_appName_edt = findViewById(R.id.LL_appName_gone);

        appName_txt = findViewById(R.id.appName_txt);
        add_btn = findViewById(R.id.add_info);

        applicationName_edit = findViewById(R.id.appName_edt);
        userName_edit = findViewById(R.id.userName_edt);
        password_edit = findViewById(R.id.password_edt);
        conf_password_edit = findViewById(R.id.confirmPassword_edt);
        desc_edit = findViewById(R.id.discription_edt);

        Intent intent = getIntent();
        applicationName = intent.getStringExtra("appName");

        if(applicationName.equals("Any Other")){
            LL_appName_edt.setVisibility(View.VISIBLE);
        }

        appName_txt.setText(applicationName);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validInfo()){
//                    Toast.makeText(getApplicationContext() , "DONE",Toast.LENGTH_SHORT).show();
                    DBHelper dbHelper = new DBHelper(getApplicationContext());

                    if(applicationName.equals("Any Other")){
                        applicationName = applicationName_edit.getText().toString();
                    }

                    InfromationClass infromationClass = new InfromationClass(userName_edit.getText().toString(),
                            applicationName , password_edit.getText().toString() ,
                            desc_edit.getText().toString());

                    try {
                        dbHelper.addNewApplication(infromationClass);
                        Toast.makeText(getApplicationContext() , "Successfully added",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext() , CreatePassword.class);
                        startActivity(intent1);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext() , "Error while creating the batch. Pleas try again later",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext() , "Some error occured",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validInfo() {

        if(applicationName.equals("Any Other")){
            if(applicationName_edit.getText().toString().trim().isEmpty()){
                applicationName_edit.setError("Provide the application name");
                return false;
            }
        }

        if(userName_edit.getText().toString().trim().isEmpty()){
            userName_edit.setError("Username required");
            return false;
        }
        if(password_edit.getText().toString().trim().isEmpty()){
            password_edit.setError("Password required");
            return false;
        }
        if(conf_password_edit.getText().toString().trim().isEmpty()){
            conf_password_edit.setError("Confirm password required");
            return false;
        }
        if(!conf_password_edit.getText().toString().equals(password_edit.getText().toString())){
            conf_password_edit.setError("Passwords didn't matched");
            Toast.makeText(getApplicationContext() , "Passwords didn't matched",Toast.LENGTH_SHORT);
            return false;
        }

        userName_edit.setError(null);
        password_edit.setError(null);
        conf_password_edit.setError(null);

        return true;


    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            if((userName_edit.getText().toString().trim().length() > 0) ||
                    (password_edit.getText().toString().trim().length() > 0) ||
                    (conf_password_edit.getText().toString().trim().length() > 0) ||
                    (desc_edit.getText().toString().trim().length() > 0)){
                Toast.makeText(getApplicationContext() , "If you'll go back then all your current details will get lost " +
                        "(Press Again to go back)",Toast.LENGTH_SHORT).show();
            }
        }

        backPressedTime = System.currentTimeMillis();
    }

}