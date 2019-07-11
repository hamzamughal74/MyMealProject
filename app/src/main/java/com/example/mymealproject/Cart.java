package com.example.mymealproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.CustomerOrder.CustomerOrderFeedback;
import com.example.mymealproject.CustomerOrder.OrderRequest;
import com.example.mymealproject.sqlDatabase.Database;
import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stepstone.apprating.AppRatingDialog;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth Auth;
    FirebaseDatabase database;
    DatabaseReference request;
    TextView txtTotalPrice;
    Button btnPlace;
    String restID;
    String orderID;
    String currentUID;
    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle extras = getIntent().getExtras();
        restID = extras.getString("get");
        orderID = UUID.randomUUID().toString();
        database = FirebaseDatabase.getInstance();
        request = database.getReference("OrderRequest");
        Auth = FirebaseAuth.getInstance();
        currentUID = Auth.getCurrentUser().getUid();
        //Init
        recyclerViewCart = findViewById(R.id.listCart);
        recyclerViewCart.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewCart.setLayoutManager(layoutManager);
        txtTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        loadFoodList();

    }

    public void btnPlaceOrder(View view) {
        showAlertDialog();
    }

    private void loadFoodList() {


        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this,restID);
        recyclerViewCart.setAdapter(adapter);

        // Calculate total price
        int total = 0;
        for (Order order : cart)
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        txtTotalPrice.setText(String.valueOf(total));
    }

    private void showAlertDialog() {

        AlertDialog.Builder ad = new AlertDialog.Builder(Cart.this);
        ad.setTitle("One more step!");
        ad.setMessage("Enter your Table No.");

        final EditText txtAdress = new EditText(Cart.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        txtAdress.setLayoutParams(layoutParams);
        ad.setView(txtAdress); // add to alert dialog
        ad.setIcon(R.drawable.add_icon);
        ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                OrderRequest orderRequest = new OrderRequest(
                        txtAdress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart,
                        restID,
                        currentUID,
                        orderID

                );

                request.child(orderID)
                        .setValue(orderRequest);

                //Delete Cart
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Thank you! Order Placed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad.show();

    }
}

