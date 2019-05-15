package com.example.mymealproject.OpenRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.Cart;
import com.example.mymealproject.CustomerOrder.OrderStatus;
import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.dataModelRest;
import com.example.mymealproject.sqlDatabase.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class open_restaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView, mRecyclerViewDish;
    ArrayList<ModelCatagory> mFoodList;
    ArrayList<MenuModel> mDishList;
    DatabaseReference mDatabaseReference;
    DishAdapter mAdapter;
    TextView RestName;
    String string;
Database mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_restaurant);
        Bundle extras = getIntent().getExtras();
        string  = extras.getString("rID");
        mRecyclerView = findViewById(R.id.recyclerViewDR2);
        mRecyclerViewDish = findViewById(R.id.recyclerViewDish);
        RestName = findViewById(R.id.RestName);

        BottomNavigationView bottomNav = findViewById(R.id.openRestNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //for catagory
        mFoodList = new ArrayList<>();
        mFoodList.add(new ModelCatagory(R.drawable.food, "Steaks"));
        mFoodList.add(new ModelCatagory(R.drawable.food, "BBQ"));
        mFoodList.add(new ModelCatagory(R.drawable.food, "Steaks"));
        mFoodList.add(new ModelCatagory(R.drawable.food, "BBQ"));

        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mRecyclerView.setLayoutManager(mRvLayoutManager);
        CatagoryAdapter mAdapter = new CatagoryAdapter(this, mFoodList);
        mRecyclerView.setAdapter(mAdapter);

        //for dishes

        dishContent();
        restDetails();

        new  Database(getBaseContext()).cleanCart();

    }

    private void dishContent() {

        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("rID").equalTo(string);
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Menu");
        mDishList = new ArrayList<>();
        mRecyclerViewDish.setLayoutManager(new LinearLayoutManager(this));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDishList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel dishList = dataSnapshot1.getValue(MenuModel.class);
                        mDishList.add(dishList);
                    }
                    mAdapter = new DishAdapter(open_restaurant.this, mDishList);
                    mRecyclerViewDish.setAdapter(mAdapter);

                } else {
                    Toast.makeText(open_restaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(open_restaurant.this, "Oops..!Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void restDetails(){
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("rID");
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").orderByKey().equalTo(string);
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
                        case R.id.nav_orders:

                            Intent intent = new Intent(open_restaurant.this,Cart.class );
                            intent.putExtra("get",string);
                            startActivity(intent);
                            break;
                        case R.id.nav:
                            Intent intent1 = new Intent(open_restaurant.this, OrderStatus.class);
                            startActivity(intent1);
                            break;


                    }
                    return false;
                }
            };
}


