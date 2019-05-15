package com.example.mymealproject.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymealproject.DiscoverDishes.DiscoverDishes;
import com.example.mymealproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.file.OpenOption;

public class fragment_user extends Fragment {

    private Button Signin;
    private FirebaseAuth auth;

    private EditText mUsername_login;
    private EditText mPassword_login;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = (View) inflater.inflate(R.layout.fragment_user,container ,false);
        Signin = view.findViewById(R.id.btn_login);

        mUsername_login = view.findViewById(R.id.username_login);
        mPassword_login = view.findViewById(R.id.password_login);
        auth = FirebaseAuth.getInstance();


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

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    showErrorDialog("There was problem signing in");
                } else {
                    Intent intent = new Intent(getActivity(), DiscoverDishes.class);
                    getActivity().finish();
                    startActivity(intent);
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
