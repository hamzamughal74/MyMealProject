package com.example.mymealproject.AdminOrderManagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mymealproject.CustomerOrder.OrderAdapter;
import com.example.mymealproject.CustomerOrder.OrderRequest;
import com.example.mymealproject.CustomerOrder.OrderStatus;
import com.example.mymealproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class AdminOrderStatus extends AppCompatActivity {
    public RecyclerView recyclerView;
    ArrayList<OrderRequest> orderList;

    FirebaseDatabase database;
    DatabaseReference requests;
    FirebaseAuth Auth;
    String currentUID;
    String UPDATE = "Update";
    String DELETE = "Delete";

    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_status);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("OrderRequest");

        recyclerView = findViewById(R.id.listOrders);
//        recyclerView.setHasFixedSize(true);

        Auth = FirebaseAuth.getInstance();
        currentUID = Auth.getCurrentUser().getUid();



        orderListShow();

    }

    private void orderListShow() {

        orderList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLayoutManager);
        final AdminOrderAdapter adminOrderAdapter = new AdminOrderAdapter(this, orderList);
        recyclerView.setAdapter(adminOrderAdapter);


        Query query = requests.orderByChild("restID").equalTo(currentUID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    orderList.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        OrderRequest orderRequest = snapshot.getValue(OrderRequest.class);
                        orderList.add(orderRequest);
                    }
                    adminOrderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AdminOrderStatus.this, "No data found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

//
        final AdminOrderAdapter adminOrderAdapter = new AdminOrderAdapter(this, orderList);
        recyclerView.setAdapter(adminOrderAdapter);

        if (item.getIntent().equals(UPDATE))
            showUpdateDialog();
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminOrderStatus.this);
        alertDialog.setTitle("Update Order");
        alertDialog.setMessage("Please  choose status");

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_order,null);

        spinner = (MaterialSpinner) view.findViewById(R.id.statusSpinner);
        spinner.setItems("Pending","In making","Ready");

        alertDialog.setView(view);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


            }
        });

    }
}
