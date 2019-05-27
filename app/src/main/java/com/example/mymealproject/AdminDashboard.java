package com.example.mymealproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mymealproject.AdminOpenRestaurant.AdminOpenRestaurant;
import com.example.mymealproject.AdminOpenRestaurant.addMenu;
import com.example.mymealproject.AdminOrderManagement.AdminOrderStatus;
import com.example.mymealproject.Sign.SignIn;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        auth= FirebaseAuth.getInstance();

    }

    public void btnOrders(View view){
    Intent orders = new Intent(AdminDashboard.this, AdminOrderStatus.class);
                        startActivity(orders);
    }
    public void btnAddDish(View view){
        Intent ad = new Intent(AdminDashboard.this , addMenu.class );
                            startActivity(ad);
    }
    public void  btnAdminSignOut(View view){
        auth.signOut();
        finish();
        finishAffinity();
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
