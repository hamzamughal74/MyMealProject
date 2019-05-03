package com.example.mymealproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.example.mymealproject.StaffOpenRestaurant.AdminOpenRestaurant;

public class Create extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    public void addRest(View view){
        Intent intent = new Intent(Create.this,AddRestaurant.class);
        finish();
        startActivity(intent);

    }
    public  void btnTest(View view){
        Intent intent = new Intent(Create.this, AdminOpenRestaurant.class);
        startActivity(intent);
    }


}
