package com.example.mymealproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth auth;

    private EditText mUsername_login;
    private EditText mPassword_login;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    Button btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_signUp = (Button) findViewById(R.id.btnSignupPanel);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        mUsername_login = findViewById(R.id.username_login);
        mPassword_login = findViewById(R.id.password_login);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        auth = FirebaseAuth.getInstance();


    setAdapter();
    }

    public void LoginExistingUser(View view) {
        login();
    }

    private void login() {

        String email = mUsername_login.getText().toString();
        String password = mPassword_login.getText().toString();
        if (email.equals("") || password.equals("")) return;
        Toast.makeText(this, "Login in progress", Toast.LENGTH_SHORT).show();

        //TODO: login with firebase

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
              showErrorDialog("There was problem signing in");
                    Intent intent = new Intent(SignIn.this, MainRestaurantsActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SignIn.this, MainRestaurantsActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });


    }

    // TODO: Show error on screen with an alert dialog
    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private void setAdapter(){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        //adding fragment
        viewPageAdapter.AddFragment(new fragment_user(),"SignIn as Customer");
        viewPageAdapter.AddFragment(new fragment_user(),"SignIn in Staff");
        mViewPager.setAdapter(viewPageAdapter);
        mViewPager.setAdapter(viewPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}


