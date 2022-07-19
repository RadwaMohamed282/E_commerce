package com.example.e_commerce.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Adapters.CartListAdapter;
import com.example.e_commerce.Database.EcommerceDatabase;
import com.example.e_commerce.Models.ProductModel;
import com.example.e_commerce.R;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    EcommerceDatabase database;
    ArrayList<ProductModel> cartList = new ArrayList<>();
    CartListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recycler = (RecyclerView)findViewById(R.id.recyclercart);
        database = new EcommerceDatabase(getApplicationContext());

        Cursor cursor = database.getCartProducts();

        while (!cursor.isAfterLast()){
            ProductModel cartProducts = new ProductModel(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3), cursor.getInt(4));
            cartList.add(cartProducts);
            cursor.moveToNext();
        }
        adapter = new CartListAdapter(cartList,getApplicationContext());

        layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);

        TextView showcost = (TextView)findViewById(R.id.totaltxt);

        showcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView cost = (TextView)findViewById(R.id.costtxt);
                Integer totalcost=0, quantity, price;
                for (int i = 0; i<cartList.size(); i++)
                {
                    price = Integer.parseInt(cartList.get(i).getPrice());
                    quantity = cartList.get(i).getQuantity();
                    totalcost += price * quantity;
                }
                cost.setText(totalcost.toString());
            }
        });

        Button location = (Button)findViewById(R.id.Location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this,Location_Maps.class);
                startActivity(i);
            }
        });

        Button submit = (Button)findViewById(R.id.submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Your order Submited Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CartActivity.this,Home.class);
                startActivity(intent);
            }
        });


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            ProductModel name = cartList.remove(viewHolder.getAdapterPosition());
            database.deleteCartProducts(name.getName());
            Toast.makeText(getApplicationContext(),name.getName()+" removed",Toast.LENGTH_LONG).show();

            adapter.notifyDataSetChanged();
        }
    };
}