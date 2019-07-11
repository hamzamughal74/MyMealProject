package com.example.mymealproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mymealproject.CustomerOrder.OrderStatus;
import com.example.mymealproject.Sign.SignIn;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard2 extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board2);

        auth = FirebaseAuth.getInstance();
    }
    public void btnOrders(View view){
        Intent intent1 = new Intent(DashBoard2.this, OrderStatus.class);
        startActivity(intent1);
    }
    public void btnSignOut2(View view){
        auth.signOut();
        finish();
        finishAffinity();
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
    public void btnMap(View view){
        Intent map = new Intent(DashBoard2.this,Map.class);
        startActivity(map);
    }
    public void btnLocation(View view){
        Intent location = new Intent(this, ImageView.class);
        startActivity(location);
    }
}
