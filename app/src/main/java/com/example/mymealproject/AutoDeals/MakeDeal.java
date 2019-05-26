package com.example.mymealproject.AutoDeals;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymealproject.Cart;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Database;
import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MakeDeal extends AppCompatActivity {
    private RecyclerView rv1,rv2,rv3,rv4;
    private ArrayList<MenuModel> dishList1;
    private ArrayList<MenuModel> dishList2;
    private ArrayList<MenuModel> dishList3;
    private ArrayList<MenuModel> dishList4;
    RV1Adapter rv1Adapter;
    RV2Adapter rv2Adapter;
    RV3Adapter rv3Adapter;
    RV4Adapter rv4Adapter;
    Query query;
    DatabaseReference ref;
    private String person, budget ,rID,sBalance;
    //cart
    List<Order> cart = new ArrayList<>();
    TextView balance,totalBudget;
    Button Refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_deal);
        rv1 = findViewById(R.id.rv1);
        rv2 = findViewById(R.id.rv2);
        rv3 = findViewById(R.id.rv3);
        rv4 = findViewById(R.id.rv4);
        balance = findViewById(R.id.balance);
        totalBudget = findViewById(R.id.totalBudget);
        Refresh = findViewById(R.id.cart);
        rID = getIntent().getStringExtra("rID");
        person = getIntent().getStringExtra("person");
        budget = getIntent().getStringExtra("budget");

        new  Database(getBaseContext()).cleanCart();
        loadFoodList();
        getRice(person,budget,rID);
        getbbq(person,budget,rID);
        getPakistani(person,budget,rID);
        getFastFood(person,budget,rID);

    }
    public void btnCart(View view){
        Intent cart = new Intent(MakeDeal.this,Cart.class );
        cart.putExtra("get",rID);
        startActivity(cart);
    }
        public void refresh(View view){
        loadFoodList();
        }

    private void getFastFood(final String person, final String budget, final  String rID) {
        dishList4 = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu");
        query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("catagory").equalTo("FastFood");
        rv4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dishList4.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel menuModel = dataSnapshot1.getValue(MenuModel.class);
                        //Int conversion for conditions

                        String sPerson = menuModel.getPerson();
                        int iPerson = Integer.parseInt(sPerson);
                        int gPerson = Integer.parseInt(person);
                        String sPrice = menuModel.getPrice();
                        int iBudget = Integer.parseInt(budget);
                        int iPrice = Integer.parseInt(sPrice);
                        String iID = menuModel.getrID();
                        if (iID.equals(rID)) {
                            if (iPerson <= gPerson) {
                                if (iPrice < iBudget) {
                                    dishList4.add(menuModel);

                                }
                            }
                        }



                    }
                    rv4Adapter = new RV4Adapter(MakeDeal.this, dishList4);
                    rv4.setAdapter(rv4Adapter);
                }else {
                    dishList4.clear();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void getPakistani(final String person, final String budget, final  String rID) {
        dishList3 = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu");
        query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("catagory").equalTo("Pakistani");
        rv3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dishList3.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel menuModel = dataSnapshot1.getValue(MenuModel.class);
                        //Int conversion for conditions

                        String sPerson = menuModel.getPerson();
                        int iPerson = Integer.parseInt(sPerson);
                        int gPerson = Integer.parseInt(person);
                        String sPrice = menuModel.getPrice();
                        int iBudget = Integer.parseInt(budget);
                        int iPrice = Integer.parseInt(sPrice);
                        String iID = menuModel.getrID();
                            if (iID.equals(rID)) {
                                if (iPerson <= gPerson) {
                                    if (iPrice < iBudget) {
                                        dishList3.add(menuModel);

                                    }
                                }
                            }



                    }
                    rv3Adapter = new RV3Adapter(MakeDeal.this, dishList3);
                    rv3.setAdapter(rv3Adapter);
                }else {
                    dishList3.clear();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getbbq(final String person, final String budget,final  String rID) {
        dishList2 = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu");
      Query  query2 = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("catagory").equalTo("BarBeQue");
        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dishList2.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel menuModel = dataSnapshot1.getValue(MenuModel.class);
                        //Int conversion for conditions
                        String sPerson = menuModel.getPerson();
                        int iPerson = Integer.parseInt(sPerson);
                        int gPerson = Integer.parseInt(person);
                        String sPrice = menuModel.getPrice();
                        int iBudget = Integer.parseInt(budget);
                        int iPrice = Integer.parseInt(sPrice);
                        String iID = menuModel.getrID();
                        if (iID.equals(rID)) {
                            if (iPerson <= gPerson) {
                                if (iPrice < iBudget) {
                                    dishList2.add(menuModel);

                                }
                            }
                        }
                    }
                    rv2Adapter = new RV2Adapter(MakeDeal.this, dishList2);
                    rv2.setAdapter(rv2Adapter);
                }else {
                    dishList2.clear();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getRice(final String person, final String budget,final String rID) {
        dishList1 = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu");
        query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("catagory").equalTo("Rice");
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dishList1.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel menuModel = dataSnapshot1.getValue(MenuModel.class);
                        //Int conversion for conditions

                        String sPerson = menuModel.getPerson();
                        int iPerson = Integer.parseInt(sPerson);
                        int gPerson = Integer.parseInt(person);
                        String sPrice = menuModel.getPrice();
                        int iBudget = Integer.parseInt(budget);
                        int iPrice = Integer.parseInt(sPrice);
                        String iID = menuModel.getrID();
                        if (iID.equals(rID)) {
                            if (iPerson <= gPerson) {
                                if (iPrice < iBudget) {
                                    dishList1.add(menuModel);

                                }
                            }
                        }
                    }
                    rv1Adapter = new RV1Adapter(MakeDeal.this, dishList1);
                    rv1.setAdapter(rv1Adapter);
                }else {
                    dishList1.clear();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void loadFoodList() {

        cart = new Database(this).getCarts();
        // Calculate total price
        int total = 0;
        for (Order order : cart)
            total +=   (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        sBalance = String.valueOf (Integer.parseInt(budget)- total);
        balance.setText("Balance : "+sBalance+" Rs");
        totalBudget.setText("Budget :  "+budget+" Rs");

    }

}
