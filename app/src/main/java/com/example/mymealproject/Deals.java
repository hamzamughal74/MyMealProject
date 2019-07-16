package com.example.mymealproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mymealproject.AutoDeals.DealsDetailesAdapter;
import com.example.mymealproject.IntelliDeal.intelliDeal;
import com.example.mymealproject.sqlDatabase.Database;
import com.example.mymealproject.sqlDatabase.Order;

import java.util.ArrayList;

public class Deals extends AppCompatActivity {
    private RecyclerView rv;
    ArrayList<MenuModel> list = new ArrayList<>();
    String itemID,itemPrice,itemPerson,itemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
        rv = findViewById(R.id.dealsRV);
         list = getIntent().getParcelableArrayListExtra("D");
        rv.setLayoutManager(new LinearLayoutManager(this));
    DealsDetailesAdapter DDA = new DealsDetailesAdapter(list, Deals.this);
    rv.setAdapter(DDA);

    }
    public void btnDone(View view){
        int i;
        for (i = 0;i<list.size();i++){
            itemID = list.get(i).getID();
            itemName = list.get(i).getName();
            itemPrice = list.get(i).getPrice();
            itemPerson = list.get(i).getPerson();
            new Database(getBaseContext()).addToCart(new Order(
                    itemID,
                    itemName,
                    "1",
                    itemPrice
            ));

        }
        Toast.makeText(Deals.this, "Added to Cart", Toast.LENGTH_SHORT).show();
        finish();
    }
}
