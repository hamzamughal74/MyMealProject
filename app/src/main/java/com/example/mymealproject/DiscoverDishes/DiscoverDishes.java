package com.example.mymealproject.DiscoverDishes;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.DashBoard2;
import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscoverDishes extends AppCompatActivity {
    private RecyclerView mFoodRecycleView;
    ArrayList<MenuModel> mFoodList;
    DatabaseReference mDatabaseReference;
    FoodAdapter mFoodAdapter;
    EditText search;
    Query query;
    String city = "no";
    private ImageButton locationbtn;
    private LocationManager mLocationManager;
    private double lat,lon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_restaurant);
        search=findViewById(R.id.search);
        mFoodRecycleView = findViewById(R.id.recyclerViewDR);
        locationbtn = findViewById(R.id.locationbtnn);
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mFoodRecycleView.setLayoutManager(mRvLayoutManager);
        mFoodList = new ArrayList<>();
        mFoodAdapter = new FoodAdapter(this,mFoodList);
        mFoodRecycleView.setAdapter(mFoodAdapter);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Menu");
//        search.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);


        foodListShow();
//        test2();
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    test();
                    //to close keypad on search button click
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });




    }

    //Functionality for BackPress*******************************************************************
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
//*************************************************************************************************

    public void btnDashBoard(View view){
        Intent intent = new Intent(DiscoverDishes.this , DashBoard2.class );
        startActivity(intent);
    }
    public void btnSearch(View view){
        test();
    }

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

    }
    public void test(){
        if (search.getText().toString().isEmpty()){
            mDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
            mFoodRecycleView.setVisibility(View.VISIBLE);

        }
        else {

            query=mDatabaseReference.orderByChild("name").startAt(search.getText().toString()).endAt(search.getText().toString());
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
                    if (city.equals("no")){
                        mFoodList.add(modelFood);
                    }else {
                        if (snapshot.hasChild("restCity")){
                            if (modelFood.getRestCity().equals(city)){
                                mFoodList.add(modelFood);
                            }
                        }

                    }
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

    public void btnLocation(View view){
      onGPS();
    }


    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable ImageView").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationbtn.setBackgroundResource(R.drawable.loc_on);
                    getLocation();
                    Toast.makeText(DiscoverDishes.this, "ImageView Based On", Toast.LENGTH_SHORT).show();
                }
                if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationbtn.setBackgroundResource(R.drawable.loc_off);
                    city = "no";
                }
                test2();
            }
        }, 2000);


    }
    private void getLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        android.location.Location location = mLocationManager.getLastKnownLocation(mLocationManager.NETWORK_PROVIDER);
        getLatLon(location);
        loc();
        Toast.makeText(this, ""+city, Toast.LENGTH_SHORT).show();
    }
    public void loc(){
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(lat,lon,1);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getLatLon(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
    }
}
