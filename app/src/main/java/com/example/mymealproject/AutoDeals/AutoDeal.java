package com.example.mymealproject.AutoDeals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mymealproject.R;

public class AutoDeal extends AppCompatActivity {

    EditText mBudget,mPerson;
    String person,budget;
    String rID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_deal);

        mPerson = findViewById(R.id.person);
        mBudget = findViewById(R.id.budget);

        rID = getIntent().getStringExtra("rID");





    }

    public void pbDone(View view) {
        budget = mBudget.getText().toString();
       person = mPerson.getText().toString();
        Intent intent =  new Intent(AutoDeal.this,MakeDeal.class);
        intent.putExtra("rID",rID);
        intent.putExtra("person",person);
        intent.putExtra("budget",budget);
        startActivity(intent);
    }
}
