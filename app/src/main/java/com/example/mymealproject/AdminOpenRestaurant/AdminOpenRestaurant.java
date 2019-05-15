package com.example.mymealproject.AdminOpenRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.AdminOrderManagement.AdminOrderStatus;
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

import java.security.Key;
import java.util.ArrayList;

public class AdminOpenRestaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView,mRecyclerViewDish;
    ArrayList<AdminModelCatagory> mFoodList;
    DatabaseReference mDatabaseReference;
    ArrayList<MenuModel>mDishList;
    ArrayList<dataModelRest>mRestDetails;
    FirebaseAuth Auth;
    String key;
    TextView RestName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_open_restaurant);
        mRecyclerView = findViewById(R.id.recyclerViewDR2);
        mRecyclerViewDish = findViewById(R.id.recyclerViewDish);
        RestName = findViewById(R.id.RestName);
        BottomNavigationView bottomNav = findViewById(R.id.nav_StaffOpenRestaurant);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Auth = FirebaseAuth.getInstance();
        key = Auth.getCurrentUser().getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Restaurant");


        //for catagory


        mFoodList = new ArrayList<>();
        mFoodList.add(new AdminModelCatagory(R.drawable.food,"Steaks"));
        mFoodList.add(new AdminModelCatagory(R.drawable.food,"BBQ"));
        mFoodList.add(new AdminModelCatagory(R.drawable.food,"Steaks"));
        mFoodList.add(new AdminModelCatagory(R.drawable.food,"BBQ"));

        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager mCatagoryLayoutManager = mLayoutManger;
        mRecyclerView.setLayoutManager(mCatagoryLayoutManager);
        AdminCatagoryAdapter mCatagoryAdapter = new AdminCatagoryAdapter(this,mFoodList);
        mRecyclerView.setAdapter(mCatagoryAdapter);


        //for dishes

        dishContent();
        restDetails();

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.add:

                            Intent intent = new Intent(AdminOpenRestaurant.this , addMenu.class );
                            startActivity(intent);
                            break;
                        case R.id.nav_orders:
                        Intent intent1 = new Intent(AdminOpenRestaurant.this, AdminOrderStatus.class);
                        startActivity(intent1);
                        break;
                    }
                    return false;
                }
            };
}
