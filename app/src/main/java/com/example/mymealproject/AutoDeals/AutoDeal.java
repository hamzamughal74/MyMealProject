package com.example.mymealproject.AutoDeals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

       mBudget.setError(null);
       mPerson.setError(null);

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(budget)){
            mBudget.setError("Enter Budget");
            focusView = mBudget;
            cancel = true;

        }
        if (TextUtils.isEmpty(person)){
            mPerson.setError("Enter Price");
            focusView = mPerson;
            cancel = true;

        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // TODO: Call create FirebaseUser() here
          done(rID,person,budget);

        }




    }

    private void done(String rID,String person,String budget) {

        Intent intent =  new Intent(AutoDeal.this,MakeDeal.class);
        intent.putExtra("rID",rID);
        intent.putExtra("person",person);
        intent.putExtra("budget",budget);
        startActivity(intent);

    }

}
