package com.example.mymealproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mymealproject.AdminOpenRestaurant.AdminOpenRestaurant;
import com.example.mymealproject.AdminOpenRestaurant.addMenu;
import com.example.mymealproject.AdminOrderManagement.AdminOrderStatus;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


    }

    public void btnOrders(View view){
    Intent orders = new Intent(AdminDashboard.this, AdminOrderStatus.class);
                        startActivity(orders);
    }
    public void btnAddDish(View view){
        Intent ad = new Intent(AdminDashboard.this , addMenu.class );
                            startActivity(ad);
    }
}
