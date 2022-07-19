package com.example.e_commerce.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Database.EcommerceDatabase;
import com.example.e_commerce.R;

public class MainActivity extends AppCompatActivity {

    EditText username,passwprd;
    Button login_btn,sign_up;
    TextView forget_pass;
    boolean login ;

    CheckBox rememberme;
    EcommerceDatabase database;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intialize();
        checkLogin();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerLogin();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sign_Up_activity.class);
                startActivity(i);
            }
        });

        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Recover_password.class);
                startActivity(intent);
            }
        });

    }

    protected void intialize(){
        username = findViewById(R.id.username_login);
        passwprd = findViewById(R.id.password_login);
        login_btn = findViewById(R.id.btn_login);
        forget_pass = findViewById(R.id.forget_password);
        sign_up = findViewById(R.id.go_sign_up_btn);
        rememberme = findViewById(R.id.remember);
        database = new EcommerceDatabase(this);

        sharedPref = getSharedPreferences("remember me",MODE_PRIVATE);
    }

    protected void CustomerLogin(){
        String Uname = username.getText().toString();
        String Password = passwprd.getText().toString();
        Cursor cursor = database.customerlogin(Uname,Password);
        if(cursor.getCount()<=0)
        {
            Toast.makeText(getApplicationContext(),"Please check username and password",Toast.LENGTH_LONG).show();
        }
        else
        {
            if(rememberme.isChecked())
            {
                keeplogin(Uname,Password);
            }
            Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
        }
    }

    protected void keeplogin(String userName, String Upass){
        editor = sharedPref.edit();
        editor.putString("username",userName);
        editor.putString("password",Upass);
        editor.putBoolean("login",true);
        editor.apply();
    }

    protected void checkLogin(){
        login = sharedPref.getBoolean("login",false);
        if(login == true)
        {
            Intent i = new Intent(MainActivity.this,Home.class);
            startActivity(i);
        }
    }
}