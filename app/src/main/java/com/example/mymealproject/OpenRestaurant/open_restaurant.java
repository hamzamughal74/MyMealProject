package com.example.mymealproject.OpenRestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mymealproject.R;

import java.util.ArrayList;

public class open_restaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView,mRecyclerViewDish;
    ArrayList<ModelCatagory> mFoodList;
    ArrayList<ModelDish>mDishList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_restaurant);

        mRecyclerView = findViewById(R.id.recyclerViewDR2);
        mRecyclerViewDish = findViewById(R.id.recyclerViewDR3);
        //for catagory
        mFoodList = new ArrayList<>();
        mFoodList.add(new ModelCatagory(R.drawable.food,"Steaks"));
        mFoodList.add(new ModelCatagory(R.drawable.food,"BBQ"));
        mFoodList.add(new ModelCatagory(R.drawable.food,"Steaks"));
        mFoodList.add(new ModelCatagory(R.drawable.food,"BBQ"));

        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mRecyclerView.setLayoutManager(mRvLayoutManager);
        CatagoryAdapter mAdapter = new CatagoryAdapter(this,mFoodList);
        mRecyclerView.setAdapter(mAdapter);

        //for dishes

        dishContent();

    }
    private void  dishContent(){
        mDishList = new ArrayList<>();
        mDishList.add(new ModelDish(R.drawable.food,"Chicken","50Rs"));
        mDishList.add(new ModelDish(R.drawable.food,"Chicken","50Rs"));
        mDishList.add(new ModelDish(R.drawable.food,"Chicken","50Rs"));
        mDishList.add(new ModelDish(R.drawable.food,"Chicken","50Rs"));

        LinearLayoutManager mLayoutMangerDish = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutMangerDish;
        mRecyclerViewDish.setLayoutManager(mRvLayoutManager);
        DishAdapter mAdapter = new DishAdapter(this,mDishList);
        mRecyclerViewDish.setAdapter(mAdapter);
    }

}
