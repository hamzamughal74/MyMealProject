package com.example.mymealproject.DiscoverDishes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.DashBoard1;
import com.example.mymealproject.DashBoard2;
import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscoverDishes extends AppCompatActivity {
    private RecyclerView mFoodRecycleView;
    ArrayList<MenuModel> mFoodList;
    DatabaseReference mDatabaseReference;
    FoodAdapter mFoodAdapter;
    EditText search;
    Query query;
    TextView dc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_restaurant);
        search=findViewById(R.id.search);
        mFoodRecycleView = findViewById(R.id.recyclerViewDR);
        dc=findViewById(R.id.DC);
        dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mFoodRecycleView.setLayoutManager(mRvLayoutManager);
        mFoodList = new ArrayList<>();
        mFoodAdapter = new FoodAdapter(this,mFoodList);
        mFoodRecycleView.setAdapter(mFoodAdapter);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Menu");

//        BottomNavigationView bottomNav = findViewById(R.id.main_nav);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);

        foodListShow();
        test();
    }

    public void btnDashBoard(View view){
        Intent intent = new Intent(DiscoverDishes.this , DashBoard2.class );
        startActivity(intent);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(MenuItem menuItem) {
//                    switch (menuItem.getItemId()){
//                        case R.id.nav_discoverRestaurants:
//
//                            Intent intent = new Intent(DiscoverDishes.this , DiscoverDishes.class );
//                            startActivity(intent);
//                            break;
//
//
//                    }
//                    return false;
//                }
//            };

    public void foodListShow(){
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    mFoodList.clear();
//                        foodListShow();
                }

                @Override
                public void afterTextChanged(Editable s) {
                   test2();
                }
            });
//        if (search.getText().toString().isEmpty()){
//            mDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
//            mFoodRecycleView.setVisibility(View.VISIBLE);
//        }
//        else {
//            query=mDatabaseReference.orderByChild("name").startAt(search.getText().toString());
//            query.addListenerForSingleValueEvent(valueEventListener);
//
//        }





//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    mFoodList.clear();
//
//                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()){
//                        MenuModel modelFood = Snapshot.getValue(MenuModel.class);
//                        mFoodList.add(modelFood);
//                    }
//                    mFoodAdapter.notifyDataSetChanged();
//                }
//                else {
//                    Toast.makeText(DiscoverDishes.this, "No data found", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
    public void test(){
        if (search.getText().toString().isEmpty()){
            mDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
            mFoodRecycleView.setVisibility(View.VISIBLE);
        }
        else {
            query=mDatabaseReference.orderByChild("name").startAt(search.getText().toString());
            query.addListenerForSingleValueEvent(valueEventListener);

        }
    }
    public void test2(){
        if (search.getText().toString().isEmpty()){
            mDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
            mFoodRecycleView.setVisibility(View.VISIBLE);
        }

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mFoodList.clear();
            if (dataSnapshot.exists()) {
                mFoodRecycleView.setVisibility(View.VISIBLE);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MenuModel modelFood = snapshot.getValue(MenuModel.class);
                    mFoodList.add(modelFood);
                }
               mFoodAdapter .notifyDataSetChanged();
            }
            else {
//                mFoodRecycleView.setVisibility(View.GONE);
                mFoodList.clear();
                Toast.makeText(DiscoverDishes.this, "No data found", Toast.LENGTH_SHORT).show();
                mFoodAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
