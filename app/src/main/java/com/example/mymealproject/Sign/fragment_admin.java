package com.example.mymealproject.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymealproject.AdminOpenRestaurant.AdminOpenRestaurant;
import com.example.mymealproject.Create;
import com.example.mymealproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_admin extends Fragment {
    private Button Signin;
    private FirebaseAuth Auth;

    private EditText mUsername_login;
    private EditText mPassword_login;
    DatabaseReference mReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_staff,container,false);
        mUsername_login = view.findViewById(R.id.adminUsername);
        mPassword_login = view.findViewById(R.id.adminPassword);
        Signin = view.findViewById(R.id.btn_login);
        Auth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference("Users");


        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        return view;



    }
    public void Login(){
        String email = mUsername_login.getText().toString();
        String password = mPassword_login.getText().toString();
        if (email.equals("") || password.equals("")) return;
        Toast.makeText(getActivity(), "Login in progress", Toast.LENGTH_SHORT).show();

        //TODO: login with firebase

        Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    showErrorDialog("There was problem signing in");
                } else {
                    final String CurrentId = Auth.getCurrentUser().getUid();
                    mReference.child(CurrentId).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String mRole =dataSnapshot.getValue(String.class);
                            if(!mRole.equals("Admin")){
                                Toast.makeText(getActivity(), "Invalid Attempt", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                            else {
                                FirebaseDatabase.getInstance().getReference("Restaurant").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                       for (DataSnapshot data : dataSnapshot.getChildren())
                                           if (dataSnapshot.hasChild(CurrentId)){
                                               Intent intent = new Intent(getActivity(), AdminOpenRestaurant.class);
                                                getActivity().finish();
                                               startActivity(intent);

                                           }
                                           else {
                                               Intent intent = new Intent(getActivity(),Create.class);
                                               getActivity().finish();
                                               startActivity(intent);
                                           }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });
    }
    private void showErrorDialog(String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
