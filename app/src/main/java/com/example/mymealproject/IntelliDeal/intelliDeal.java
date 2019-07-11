    package com.example.mymealproject.IntelliDeal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.mymealproject.CustomerOrder.Orders;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Database;
import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

    public class intelliDeal extends AppCompatActivity {
        private RecyclerView mRecyclerView;
        intelliAdapter adapter;
        private ArrayList<MenuModel> list;
        private  ArrayList<MenuModel> AL1, AL2 ,AL3,AL4,testList1,testList2;
        private  ArrayList<MenuModel> combineList; private  ArrayList<MenuModel> combineList2;

        DatabaseReference dRef;
        String[] array = new String[2];
        int catSize = 0, choiceSize=0; int AL1Size=0; int AL2Size=0;int AL3Size=0;int AL4Size=0;int model=0;
        double PersonIn= 0;double BudgetIn = 0;
        int person1=0;int person2=0; int person3=0; int person4    =0;
        int price1=0,price2=0,price3=0,price4=0;
        List<String> catagoryList = new ArrayList<>();
        List<String> choiceList = new ArrayList<>();
        String Cs,pakistani,rice,fastfood,bbq,Budget,Person;
        int a = 0;

        String itemID,itemPrice,itemPerson,itemName;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intelli_deal);
            mRecyclerView = findViewById(R.id.iRV);
            dRef = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu");
            Cs = getIntent().getStringExtra("Cs");
            final String Bs = getIntent().getStringExtra("Bs");

            String Ms = getIntent().getStringExtra("Ms");
            pakistani = getIntent().getStringExtra("Pak");
            rice = getIntent().getStringExtra("Rice");
            bbq = getIntent().getStringExtra("BBQ");
            fastfood = getIntent().getStringExtra("FastFood");
            Budget = getIntent().getStringExtra("Budget");
            Person = getIntent().getStringExtra("Person");


            catagoryList = new ArrayList<>();
            if (pakistani.equals("1")) {
                catagoryList.add("Pakistani");
                catSize = catagoryList.size();
            }
            if (rice.equals("1")){
                    catagoryList.add("Rice");
                    catSize = catagoryList.size();
                }
            if (bbq.equals("1")){
                catagoryList.add("BarBeQue");
                catSize = catagoryList.size();
            }
            if (fastfood.equals("1")){
                catagoryList.add("FastFood");
                catSize = catagoryList.size();
            }


            //choice list
            choiceList = new ArrayList<>();
            if (Cs.equals("1")){
                choiceList.add("Chicken");
                choiceSize = choiceList.size();

            }
            if (Bs.equals("1")){
                choiceList.add("Beaf");
                choiceSize = choiceList.size();
            }
            if (Ms.equals("1")){
                choiceList.add("Mutton");
                choiceSize = choiceList.size();
            }


                call(choiceList,catagoryList, catSize,choiceSize);

        new Database(getBaseContext()).cleanCart();


        }

        public  void btnDone(View view){
            int i;
        for (i = 0;i<combineList.size();i++){
            itemID = combineList.get(i).getID();
            itemName = combineList.get(i).getName();
            itemPrice = combineList.get(i).getPrice();
//            itemPerson = combineList.get(i).getPerson();
            new Database(getBaseContext()).addToCart(new Order(
                    itemID,
                    itemName,
                    "1",
                    itemPrice
            ));

        }
            Toast.makeText(intelliDeal.this, "Added to Cart", Toast.LENGTH_SHORT).show();
        }





    public  void call(final List<String> choiceList, final List<String> catagoryList, final int catSize,final int choiceSize){
        AL1 = new ArrayList<>();
        AL2 = new ArrayList<>();
        AL3 = new ArrayList<>();
        AL4 = new ArrayList<>();
        list = new ArrayList<>();
        combineList = new ArrayList<>();
        combineList2 = new ArrayList<>();
        testList1 = new ArrayList<>();
        testList2 = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    MenuModel menuModel = dataSnapshot1.getValue(MenuModel.class);


                    int i = 0;
                    int e = 0;



                    while (i<catSize){
                        String checkCat,checkChoice;

                        checkCat =  catagoryList.get(i);


                        if (checkCat.equals(menuModel.getCatagory()))
                        {
                            while (e<choiceSize) {
                                checkChoice =  choiceList.get(e);
                                if (checkChoice.equals(menuModel.getTag())) {
                                    if (menuModel.getCatagory().equals("Pakistani")){
                                        AL1.add(menuModel);
                                        AL1Size= AL1.size();
                                    }
                                    if (menuModel.getCatagory().equals("Rice")){
                                        AL2.add(menuModel);
                                        AL2Size = AL2.size();
                                    }
                                    if (menuModel.getCatagory().equals("BarBeQue")){
                                        AL3.add(menuModel);
                                        AL3Size = AL3.size();
                                    }
                                    if (menuModel.getCatagory().equals("FastFood")){
                                        AL4.add(menuModel);
                                        AL4Size = AL4.size();
                                    }
                                }
                                e++;

                            }

                        }
                        i++;
                    }




                }
                ifs();


                   loop1:
                    for (int a = 0; a < AL1Size; a++) {
                        if (!AL1.isEmpty()) {
                            person1 = Integer.parseInt(AL1.get(a).getPerson());
                            price1 = Integer.parseInt(AL1.get(a).getPrice());
                        }

                        loop2:
                        for (int b = 0; b < AL2Size; b++) {

                            if (!AL2.isEmpty()) {

                                person2 = person1 + Integer.parseInt(AL2.get(b).getPerson());
                                price2 = price1 + Integer.parseInt(AL2.get(b).getPrice());
                            }
                            loop3:
                            for (int c = 0; c < AL3Size; c++) {

                                if (!AL3.isEmpty()) {
                                    person3 = person2 + Integer.parseInt(AL3.get(c).getPerson());
                                    price3 = price2 + Integer.parseInt(AL3.get(c).getPrice());
                                }

                                loop4:
                                for (int d = 0; d < AL4Size; d++) {
                                    if (!AL4.isEmpty()) {
                                        person4 = person3 + Integer.parseInt(AL4.get(d).getPerson());
                                        price4 = price3 + Integer.parseInt(AL4.get(d).getPrice());
                                    }

                                    PersonIn = Double.parseDouble(Person);
                                    BudgetIn = Double.parseDouble(Budget);


                                    if (price4 <= BudgetIn) {
                                        if (person4 == PersonIn) {


                                            if (!AL1.isEmpty()) {
                                                combineList.add(AL1.get(a));
                                            }
                                            if (!AL2.isEmpty()) {
                                                combineList.add(AL2.get(b));
                                            }
                                            if (!AL3.isEmpty()) {
                                                combineList.add(AL3.get(c));
                                            }
                                            if (!AL4.isEmpty()){
                                                combineList.add(AL4.get(d));
                                            }
                                        break loop1;
                                        }

                                    }

                                }


                            }
                        }
                    }
                mRecyclerView.setVisibility(View.VISIBLE);
                adapter = new intelliAdapter(intelliDeal.this, combineList);
                mRecyclerView.setAdapter(adapter);
                if (combineList.isEmpty()){
                    mRecyclerView.setVisibility(View.GONE);
                }
            }

            private void ifs() {
                //1
                if (AL1Size<1){
                    load1();
                }
                //2
                if (AL2Size<1){
                    load2();
                }
                //3
                if (AL3Size<1){
                    load3();
                }
                //4
                if (AL4Size<1){
                    load7();
                }
                //1,2
                if (AL1Size < 1 && AL2Size<1){
                    load4();
                }
                //1,3
                if (AL1Size<1 && AL3Size<1){
                    load5();
                }
                // 2,3
                if (AL2Size<1 && AL3Size<1 ){
                    load6();
                }
                //1,4
                if (AL1Size<1 && AL4Size<1){
                    load8();
                }
                //1,2,3
                if (AL1Size<1 && AL2Size<1 && AL3Size<1){
                    load9();
                }
                //1,2,4
                if (AL1Size<1 && AL2Size<1 && AL4Size<1){
                    load10();
                }
                //2,3,4
                if (AL4Size<4 && AL2Size<1 && AL3Size<1){
                    load11();
                }
                //1,3,4
                if (AL1Size<1 && AL3Size<1 && AL4Size<1){
                    load15();
                }
                //2,4
                if (AL2Size<1 && AL4Size<1){
                    load12();
                }
                //3,4
                if (AL3Size<1 && AL4Size<1){
                    load14();
                }
            }

            private void load15() {

                    loop2:
                    for (int b = 0; b < AL2Size; b++) {

                        if (!AL2.isEmpty()) {

                            person2 =Integer.parseInt(AL2.get(b).getPerson());
                            price2 = Integer.parseInt(AL2.get(b).getPrice());
                        }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price2<= BudgetIn) {
                                    if (person2 == PersonIn) {



                                        if (!AL2.isEmpty()) {
                                            combineList.add(AL2.get(b));
                                        }
                                        break;
                                    }

                                }

                            }


                        }
            private void load14() {
                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }

                    loop2:
                    for (int b = 0; b < AL2Size; b++) {

                        if (!AL2.isEmpty()) {

                            person2 = person1 + Integer.parseInt(AL2.get(b).getPerson());
                            price2 = price1 + Integer.parseInt(AL2.get(b).getPrice());
                        }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price2 <= BudgetIn) {
                                    if (person2 == PersonIn) {

                                            combineList.add(AL1.get(a));
                                            combineList.add(AL2.get(b));

                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
            private void load12() {
                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }


                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 = person1 + Integer.parseInt(AL3.get(c).getPerson());
                                price3 = price1 + Integer.parseInt(AL3.get(c).getPrice());
                            }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price3 <= BudgetIn) {
                                    if (person3 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }

                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }
                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
            private void load13() {
                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }


                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 = person1 + Integer.parseInt(AL3.get(c).getPerson());
                                price3 = price1 + Integer.parseInt(AL3.get(c).getPrice());
                            }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price3 <= BudgetIn) {
                                    if (person3 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }

                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }
                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
            private void load11() {
                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }
                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price1 <= BudgetIn) {
                                    if (person1 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }
                                        break;
                                    }

                                }

                            }


                        }
            private void load10() {

                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 = Integer.parseInt(AL3.get(c).getPerson());
                                price3 =Integer.parseInt(AL3.get(c).getPrice());
                            }
                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price3 <= BudgetIn) {
                                    if (person3 == PersonIn) {



                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }
                                        break;
                                    }

                                }

                            }


                        }
            private void load9() {
                loop4:
                for (int d = 0; d < AL4Size; d++) {
                    if (!AL4.isEmpty()) {
                        person4 =Integer.parseInt(AL4.get(d).getPerson());
                        price4 = Integer.parseInt(AL4.get(d).getPrice());
                    }

                    PersonIn = Double.parseDouble(Person);
                    BudgetIn = Double.parseDouble(Budget);


                    if (price4 <= BudgetIn) {
                        if (person4 == PersonIn) {

                            if (!AL4.isEmpty()){
                                combineList.add(AL4.get(d));
                            }
                                        break;
                        }

                    }

                }
            }
            private void load8() {
                loop2:
                for (int b = 0; b < AL2Size; b++) {

                    if (!AL2.isEmpty()) {

                        person2 =Integer.parseInt(AL2.get(b).getPerson());
                        price2 = Integer.parseInt(AL2.get(b).getPrice());
                    }
                    loop3:
                    for (int c = 0; c < AL3Size; c++) {

                        if (!AL3.isEmpty()) {
                            person3 = person2 + Integer.parseInt(AL3.get(c).getPerson());
                            price3 = price2 + Integer.parseInt(AL3.get(c).getPrice());
                        }



                            PersonIn = Double.parseDouble(Person);
                            BudgetIn = Double.parseDouble(Budget);


                            if (price3 <= BudgetIn) {
                                if (person3 == PersonIn) {


                                    if (!AL2.isEmpty()) {
                                        combineList.add(AL2.get(b));
                                    }
                                    if (!AL3.isEmpty()) {
                                        combineList.add(AL3.get(c));
                                    }
                                        break loop2;
                                }

                            }

                        }


                    }
                }
            private void load7() {
                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }

                    loop2:
                    for (int b = 0; b < AL2Size; b++) {

                        if (!AL2.isEmpty()) {

                            person2 = person1 + Integer.parseInt(AL2.get(b).getPerson());
                            price2 = price1 + Integer.parseInt(AL2.get(b).getPrice());
                        }
                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 = person2 + Integer.parseInt(AL3.get(c).getPerson());
                                price3 = price2 + Integer.parseInt(AL3.get(c).getPrice());
                            }
                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price3 <= BudgetIn) {
                                    if (person3 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }
                                        if (!AL2.isEmpty()) {
                                            combineList.add(AL2.get(b));
                                        }
                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }

                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
                }
            private void load6() {

                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }
                            loop4:
                            for (int d = 0; d < AL4Size; d++) {
                                if (!AL4.isEmpty()) {
                                    person4 = person1 + Integer.parseInt(AL4.get(d).getPerson());
                                    price4 = price1 + Integer.parseInt(AL4.get(d).getPrice());
                                }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price4 <= BudgetIn) {
                                    if (person4 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }
                                        if (!AL4.isEmpty()){
                                            combineList.add(AL4.get(d));
                                        }
                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
            private void load5() {





                    loop2:
                    for (int b = 0; b < AL2Size; b++) {

                        if (!AL2.isEmpty()) {

                            person2 =Integer.parseInt(AL2.get(b).getPerson());
                            price2 =Integer.parseInt(AL2.get(b).getPrice());
                        }


                            loop4:
                            for (int d = 0; d < AL4Size; d++) {
                                if (!AL4.isEmpty()) {
                                    person4 = person2 + Integer.parseInt(AL4.get(d).getPerson());
                                    price4 = price2 + Integer.parseInt(AL4.get(d).getPrice());
                                }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price4 <= BudgetIn) {
                                    if (person4 == PersonIn) {



                                        if (!AL2.isEmpty()) {
                                            combineList.add(AL2.get(b));
                                        }
                                        if (!AL4.isEmpty()){
                                            combineList.add(AL4.get(d));
                                        }
                                        break loop2;
                                    }

                                }

                            }


                        }
                    }
            private void load4() {


                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 =Integer.parseInt(AL3.get(c).getPerson());
                                price3 =Integer.parseInt(AL3.get(c).getPrice());
                            }

                            loop4:
                            for (int d = 0; d < AL4Size; d++) {
                                if (!AL4.isEmpty()) {
                                    person4 = person3 + Integer.parseInt(AL4.get(d).getPerson());
                                    price4 = price3 + Integer.parseInt(AL4.get(d).getPrice());
                                }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price4 <= BudgetIn) {
                                    if (person4 == PersonIn) {



                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }
                                        if (!AL4.isEmpty()){
                                            combineList.add(AL4.get(d));
                                        }
                                        break loop3;
                                    }

                                }

                            }


                        }
                    }

            private void load1() {

                    loop2:
                    for (int b = 0; b < AL2Size; b++) {

                        if (!AL2.isEmpty()) {

                            person2 =Integer.parseInt(AL2.get(b).getPerson());
                            price2 = Integer.parseInt(AL2.get(b).getPrice());
                        }
                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 = person2 + Integer.parseInt(AL3.get(c).getPerson());
                                price3 = price2 + Integer.parseInt(AL3.get(c).getPrice());
                            }

                            loop4:
                            for (int d = 0; d < AL4Size; d++) {
                                if (!AL4.isEmpty()) {
                                    person4 = person3 + Integer.parseInt(AL4.get(d).getPerson());
                                    price4 = price3 + Integer.parseInt(AL4.get(d).getPrice());
                                }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price4 <= BudgetIn) {
                                    if (person4 == PersonIn) {

                                        if (!AL2.isEmpty()) {
                                            combineList.add(AL2.get(b));
                                        }
                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }
                                        if (!AL4.isEmpty()){
                                            combineList.add(AL4.get(d));
                                        }
                                        break loop2;
                                    }

                                }

                            }


                        }
                    }
                }

            private void load2() {

                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }


                        loop3:
                        for (int c = 0; c < AL3Size; c++) {

                            if (!AL3.isEmpty()) {
                                person3 = person1 + Integer.parseInt(AL3.get(c).getPerson());
                                price3 = price1 + Integer.parseInt(AL3.get(c).getPrice());
                            }

                            loop4:
                            for (int d = 0; d < AL4Size; d++) {
                                if (!AL4.isEmpty()) {
                                    person4 = person3 + Integer.parseInt(AL4.get(d).getPerson());
                                    price4 = price3 + Integer.parseInt(AL4.get(d).getPrice());
                                }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price4 <= BudgetIn) {
                                    if (person4 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }
                                        if (!AL3.isEmpty()) {
                                            combineList.add(AL3.get(c));
                                        }
                                        if (!AL4.isEmpty()){
                                            combineList.add(AL4.get(d));
                                        }
                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
                }

            private void load3() {

                loop1:
                for (int a = 0; a < AL1Size; a++) {
                    if (!AL1.isEmpty()) {
                        person1 = Integer.parseInt(AL1.get(a).getPerson());
                        price1 = Integer.parseInt(AL1.get(a).getPrice());
                    }

                    loop2:
                    for (int b = 0; b < AL2Size; b++) {

                        if (!AL2.isEmpty()) {

                            person2 = person1 + Integer.parseInt(AL2.get(b).getPerson());
                            price2 = price1 + Integer.parseInt(AL2.get(b).getPrice());
                        }


                            loop4:
                            for (int d = 0; d < AL4Size; d++) {
                                if (!AL4.isEmpty()) {
                                    person4 = person2 + Integer.parseInt(AL4.get(d).getPerson());
                                    price4 = price2 + Integer.parseInt(AL4.get(d).getPrice());
                                }

                                PersonIn = Double.parseDouble(Person);
                                BudgetIn = Double.parseDouble(Budget);


                                if (price4 <= BudgetIn) {
                                    if (person4 == PersonIn) {


                                        if (!AL1.isEmpty()) {
                                            combineList.add(AL1.get(a));
                                        }
                                        if (!AL2.isEmpty()) {
                                            combineList.add(AL2.get(b));
                                        }
                                        if (!AL4.isEmpty()){
                                            combineList.add(AL4.get(d));
                                        }
                                        break loop1;
                                    }

                                }

                            }


                        }
                    }
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
