package com.example.mymealproject.Sign;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mymealproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth auth;



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


        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        auth = FirebaseAuth.getInstance();


    setAdapter();
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


