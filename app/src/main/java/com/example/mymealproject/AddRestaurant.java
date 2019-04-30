package com.example.mymealproject;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddRestaurant extends AppCompatActivity{
    private EditText mRestName,mRestAdress,mRestContact;
    private Button mDone;
    private ImageButton mRestImage;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        mRestName = findViewById(R.id.RestName);
        mRestAdress = findViewById(R.id.RestAdress);
        mRestContact = findViewById(R.id.RestContact);
        mRestImage = findViewById(R.id.RestImage);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Auth = FirebaseAuth.getInstance();

    }
    public void RestDone(View view){
        createRestaurant();
    }


    private void createRestaurant(){
        final String name = mRestName.getText().toString();
        final String adress = mRestAdress.getText().toString();
        final String contact = mRestContact.getText().toString();


        String CurrentUser = Auth.getCurrentUser().getUid();
            mDatabaseReference.child(CurrentUser).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String mRole = dataSnapshot.getValue(String.class);
                    if (mRole.equals("Admin")){
                        dataModelRest rest = new dataModelRest(name,adress,contact);

                        mDatabaseReference.child("Restaurant").setValue(rest);
                        Toast.makeText(AddRestaurant.this, "Details Submitted", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            dataModelRest rest = new dataModelRest(name,adress,contact);
            mDatabaseReference.child("Restaurant").setValue(rest);

            }

    }

