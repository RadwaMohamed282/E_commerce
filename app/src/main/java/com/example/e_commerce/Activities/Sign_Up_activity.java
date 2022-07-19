package com.example.e_commerce.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Database.EcommerceDatabase;
import com.example.e_commerce.Models.CustomerModel;
import com.example.e_commerce.R;

import java.util.Calendar;

public class Sign_Up_activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText namesignup,Unamesignup,passwordsignup,jopsignup,password_Comf;
    TextView date;
    Spinner gender;
    Button signUp_btn;
    EcommerceDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_activity);
        intialize();
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    public void intialize(){
        namesignup = findViewById(R.id.name_signup);
        Unamesignup = findViewById(R.id.username_signup);
        passwordsignup = findViewById(R.id.password_signup);
        password_Comf = findViewById(R.id.password_signup);
        date = findViewById(R.id.date);
        jopsignup = findViewById(R.id.jop_signup);
        gender = findViewById(R.id.gender);
        signUp_btn = findViewById(R.id.btn_signup);
        database = new EcommerceDatabase(this);
    }

    public void signUp(){
        String name = namesignup.getText().toString();
        String Uname = Unamesignup.getText().toString();
        String password = passwordsignup.getText().toString();
        String Confirm_Pass = passwordsignup.getText().toString();
        String birthdate = date.getText().toString();
        String job = jopsignup.getText().toString();
        String gend = gender.getSelectedItem().toString();

        if(name.equals("")|| Uname.equals("") || password.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Some fields not entered",Toast.LENGTH_LONG).show();
        }
        else
        {
            if(password.equals(Confirm_Pass))
            {
                CustomerModel cust = new CustomerModel(name, Uname, password, birthdate, job, gend);
                database.customerSignUp(cust);
                Toast.makeText(getApplicationContext(), "Sign Up Successfully :)", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Sign_Up_activity.this, MainActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Password Not Match", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month+=1;
        String birthdate = ""+year+" / "+month+" / "+dayOfMonth+"";
        date.setText(birthdate);
    }
}