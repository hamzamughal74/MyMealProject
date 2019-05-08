package com.example.mymealproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.mymealproject.AdminOpenRestaurant.AdminOpenRestaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Create extends Activity {
    FirebaseAuth Auth;
    DatabaseReference mDatabaseReference;
    Button btnAddRest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Auth = FirebaseAuth.getInstance();
        final String CurrentUID = Auth.getCurrentUser().getUid();
        btnAddRest = findViewById(R.id.addRest);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Restaurant");

//        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot data : dataSnapshot.getChildren()){
//                    if (dataSnapshot.hasChild(CurrentUID)){
//                        btnAddRest.setVisibility(View.GONE);
//                    }
//                    else {
//                        btnAddRest.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    public void addRest(View view){
        Intent intent = new Intent(Create.this,AddRestaurant.class);
        finish();
        startActivity(intent);

    }

}
