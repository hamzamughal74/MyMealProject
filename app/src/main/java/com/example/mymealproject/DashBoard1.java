package com.example.mymealproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mymealproject.AutoDeals.AutoDeal;
import com.example.mymealproject.CustomerOrder.OrderStatus;

public class DashBoard1 extends AppCompatActivity {
    private String rID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        rID = getIntent().getStringExtra("rID");


    }
    public void btnOrders(View view){
        Intent intent1 = new Intent(DashBoard1.this, OrderStatus.class);
        startActivity(intent1);
    }
    public void btnAutoDeal(View view){
        Intent ad = new Intent(DashBoard1.this, AutoDeal.class);
        ad.putExtra("rID",rID);
        startActivity(ad);
    }
}
