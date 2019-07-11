package com.example.mymealproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mymealproject.AutoDeals.AutoDeal;
import com.example.mymealproject.CustomerOrder.OrderStatus;
import com.example.mymealproject.IntelliDeal.choose;
import com.example.mymealproject.IntelliDeal.intelliDeal;
import com.example.mymealproject.Sign.SignIn;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard1 extends AppCompatActivity {
    private String rID;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
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
    public void btnSignOut(View view){
        auth.signOut();
        finish();
        finishAffinity();
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
    public void btniDeal(View view){
        Intent ideal = new Intent(this, choose.class);
        startActivity(ideal);
    }
}
