package com.example.mymealproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_user extends Fragment {

    private Button Signin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = (View) inflater.inflate(R.layout.fragment_user,container ,false);
        Signin = view.findViewById(R.id.btn_login);

        Login();


        return view;

    }

    private void Login(){
      Signin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getActivity(),DiscoverRestaurant.class);
              startActivity(intent);
          }
      });
    }
}
