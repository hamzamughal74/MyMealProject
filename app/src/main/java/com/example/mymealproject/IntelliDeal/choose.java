package com.example.mymealproject.IntelliDeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymealproject.R;

public class choose extends AppCompatActivity {
    CheckBox chicken,beaf,mutton,pakistani,rice,bbq,fastfood;
    EditText budget,persons;
    String Cs,Bs,Ms,Pak,Rice,BBQ,FastFood,Budget,Person;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

         chicken = findViewById(R.id.chicken);
         beaf   = findViewById(R.id.beaf);
         mutton = findViewById(R.id.mutton);
         bbq = findViewById(R.id.BBQ);
        fastfood = findViewById(R.id.FastFood);
         pakistani   = findViewById(R.id.Pakistani);
         rice = findViewById(R.id.Rice);

         budget = findViewById(R.id.budget);
         persons = findViewById(R.id.person);


         Cs = "0" ; Bs="0"; Ms = "0"; Pak="0"; Rice="0";BBQ = "0";FastFood = "0";




    }
    public void  btnDone(View view){
        Budget = budget.getText().toString();
        Person = persons.getText().toString();

        if (chicken.isChecked()){
            Cs="1";
        }

        if (beaf.isChecked()){
            Bs="1";

        }
        if (mutton.isChecked()){
            Ms="1";
        }
        if (pakistani.isChecked()){
            Pak = "1";
        }
        if (rice.isChecked()){
            Rice = "1";
        }
        if (bbq.isChecked()){
            BBQ = "1";
        }
        if (fastfood.isChecked()){
            FastFood = "1";
        }

        Intent intent = new Intent(this,intelliDeal.class);
        intent.putExtra("Cs",Cs);
        intent.putExtra("Bs",Bs);
        intent.putExtra("Ms",Ms);
        intent.putExtra("Pak",Pak);
        intent.putExtra("Rice",Rice);
        intent.putExtra("BBQ",BBQ);
        intent.putExtra("FastFood",FastFood);
        intent.putExtra("Budget",Budget);
        intent.putExtra("Person",Person);
        startActivity(intent);
    }
}
