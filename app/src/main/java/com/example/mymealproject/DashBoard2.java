package com.example.mymealproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mymealproject.CustomerOrder.OrderStatus;

public class DashBoard2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board2);

    }
    public void btnOrders(View view){
        Intent intent1 = new Intent(DashBoard2.this, OrderStatus.class);
        startActivity(intent1);
    }
}
