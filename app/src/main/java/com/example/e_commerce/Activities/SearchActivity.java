package com.example.e_commerce.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Adapters.ProductListAdapter;
import com.example.e_commerce.Database.EcommerceDatabase;
import com.example.e_commerce.Models.ProductModel;
import com.example.e_commerce.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EcommerceDatabase database;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProductListAdapter adapter;
    ArrayList<ProductModel> searchList;
    Cursor cursor;
    ProductModel searchProducts;
    EditText searchedittxt;
    Button searchtextbtn,searchvoicebtn,searchcamerabtn;
    int voiceCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchedittxt = findViewById(R.id.searchedittext);
        searchtextbtn = findViewById(R.id.searchbtn);
        searchvoicebtn = findViewById(R.id.searchvoicebtn);
        searchcamerabtn = findViewById(R.id.searchcamerabtn);

        recyclerView = findViewById(R.id.recyclerviewsearch);
        database = new EcommerceDatabase(this);
        searchtextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchList = new ArrayList<>();
                cursor = database.searchProducts(searchedittxt.getText().toString());
                if(cursor != null)
                {
                    while (!cursor.isAfterLast())
                    {
                        searchProducts = new ProductModel(cursor.getInt(0),cursor.getString(1),
                                cursor.getString(2),cursor.getString(4),cursor.getInt(3));
                        searchList.add(searchProducts);
                        cursor.moveToNext();
                    }
                    adapter = new ProductListAdapter(searchList,getApplicationContext());
                    layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No matched Products", Toast.LENGTH_LONG).show();
                }
            }
        });

        searchvoicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent,voiceCode);
            }
        });

        /////// Search By Qr code /////////////////
        searchcamerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SearchActivity.this,
                        new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
                IntentIntegrator intentIntegrator = new IntentIntegrator(SearchActivity.this);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == voiceCode && resultCode == RESULT_OK)
        {
            ArrayList<String> voiceText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchedittxt.setText(voiceText.get(0));
        }
        searchList = new ArrayList<>();
        cursor = database.searchProducts(searchedittxt.getText().toString());
        if(cursor != null)
        {
            while (!cursor.isAfterLast())
            {
                searchProducts = new ProductModel(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(4),cursor.getInt(3));
                searchList.add(searchProducts);
                cursor.moveToNext();
            }
            adapter = new ProductListAdapter(searchList,getApplicationContext());
            layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }

        //////////////// Search By Qr code /////////////////

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult!=null)
        {
            searchedittxt.setText(intentResult.getContents());
            searchList = new ArrayList<>();
            cursor = database.SearchQrcode(searchedittxt.getText().toString());
            if(cursor != null)
            {
                while (!cursor.isAfterLast())
                {
                    searchProducts = new ProductModel(cursor.getInt(0),cursor.getString(1),
                            cursor.getString(2),cursor.getString(4),cursor.getInt(3));
                    searchList.add(searchProducts);
                    cursor.moveToNext();
                }
                adapter = new ProductListAdapter(searchList,getApplicationContext());
                layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
            else {
                Toast.makeText(getApplicationContext(),"No Matched Products",Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}