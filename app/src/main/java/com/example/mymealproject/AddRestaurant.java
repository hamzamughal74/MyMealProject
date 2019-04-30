package com.example.mymealproject;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRestaurant extends AppCompatActivity{
    private EditText mRestName,mRestAdress,mRestContact;
    private Button mDone;
    private ImageButton mRestImage;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        mRestName = findViewById(R.id.RestName);
        mRestAdress = findViewById(R.id.RestAdress);
        mRestContact = findViewById(R.id.RestContact);
        mRestImage = findViewById(R.id.RestImage);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    }
    public void RestDone(View view){

    }


    private void createRestaurant(){
            String name = mRestName.getText().toString();
            String adress = mRestAdress.getText().toString();
            String contact = mRestContact.getText().toString();


    }
}
