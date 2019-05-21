package com.example.mymealproject.AdminOpenRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.AdminDashboard;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.example.mymealproject.dataModelRest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminOpenRestaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView,mRecyclerViewDish;
    ArrayList<AdminModelCatagory> mFoodList;
    DatabaseReference mDatabaseReference;
    ArrayList<MenuModel>mDishList;
    ArrayList<dataModelRest>mRestDetails;
    FirebaseAuth Auth;
    String key;
    TextView RestName,RestAddress;
    AdminDishAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_open_restaurant);

        mRecyclerViewDish = findViewById(R.id.recyclerViewDish);
        RestName = findViewById(R.id.RestName);
        RestAddress = findViewById(R.id.sRestAddress);

        Auth = FirebaseAuth.getInstance();
        key = Auth.getCurrentUser().getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Restaurant");


        //for catagory




        //for dishes

        dishContent();
        restDetails();

    }
    public void btnRice(View view){
        getCatagory("Rice");
    }

    private void getCatagory(final String cat) {
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("rID").equalTo(key);
        mDishList = new ArrayList<>();
        mRecyclerViewDish.setLayoutManager(new LinearLayoutManager(this));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDishList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel dishList = dataSnapshot1.getValue(MenuModel.class);
                        if (dishList.getCatagory().equals(cat)){
                            mDishList.add(dishList);
                        }

                    }
                    mAdapter = new AdminDishAdapter(AdminOpenRestaurant.this, mDishList);
                    mRecyclerViewDish.setAdapter(mAdapter);

                } else {
                    mDishList.clear();
                    Toast.makeText(AdminOpenRestaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminOpenRestaurant.this, "Oops..!Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void  dishContent(){
        mDishList = new ArrayList<>();

        LinearLayoutManager mLayoutMangerDish = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mDishLayoutManager = mLayoutMangerDish;
        mRecyclerViewDish.setLayoutManager(mDishLayoutManager);
        final AdminDishAdapter mDishAdapter = new AdminDishAdapter(this,mDishList);
        mRecyclerViewDish.setAdapter(mDishAdapter);





        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu")
            .orderByChild("rID").equalTo(key);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDishList.clear();

                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                        MenuModel menuModel = Snapshot.getValue(MenuModel.class);
                        mDishList.add(menuModel);

                    }
                    mDishAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(AdminOpenRestaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void restDetails(){
        String CurrentUID = Auth.getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").orderByKey().equalTo(CurrentUID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    dataModelRest dataModelRest = Snapshot.getValue(dataModelRest.class);
                    RestName.setText(dataModelRest.getName());
                    RestAddress.setText(dataModelRest.getAdress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void btnDashBoard(View view){
        Intent db = new Intent(AdminOpenRestaurant.this, AdminDashboard.class);
        startActivity(db);
    }


}
