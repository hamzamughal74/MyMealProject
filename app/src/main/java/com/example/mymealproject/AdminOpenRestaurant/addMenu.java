package com.example.mymealproject.AdminOpenRestaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class addMenu extends AppCompatActivity {
    private EditText mName,mPrice,mPerson;
    private Spinner mSpinner;
    private ImageButton mDishImage;
    private String rID;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 10;
    FirebaseStorage storage;
    StorageReference storageReference;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth Auth;
    private String dishID;
    private String imageUrl;
    private String restaurantName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);

        mName = findViewById(R.id.dishName);
        mPrice = findViewById(R.id.dishPrice);
        mPerson = findViewById(R.id.dishPerson);
        mSpinner = findViewById(R.id.dishCatagory);

        mDishImage = findViewById(R.id.dishImage);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Auth = FirebaseAuth.getInstance();
        rID = Auth.getCurrentUser().getUid();


    }
    public void DishImage(View view){
        chooseImage();
    }
    public void dishDone(View view){
        Dish();
//        Intent intent = new Intent(addMenu.this, AdminOpenRestaurant.class);
//        startActivity(intent);
    }
    private void Dish(){
//        Intent intent = getIntent();


//        String name = mName.getText().toString();
//        String price = mPrice.getText().toString();
//        String catagory = mSpinner.getSelectedItem().toString();
//        String restaurantName = intent.getStringExtra("restaurantName");
//        mDatabaseReference = mDatabaseReference.child("Menu").push();
//        dishID = mDatabaseReference.getKey();
        uploadImage();
//        MenuModel menu = new MenuModel(
//                name,price,catagory,rID,restaurantName,imageUrl
//        );
//
//        mDatabaseReference.setValue(menu);
        Toast.makeText(addMenu.this, "Details Submitted", Toast.LENGTH_SHORT).show();
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mDishImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage() {

        if(filePath != null)
        {     String CurrentUID = Auth.getCurrentUser().getUid();
            mDatabaseReference.child(CurrentUID).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    restaurantName = dataSnapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            mDatabaseReference = mDatabaseReference.child("Menu").push();
            dishID = mDatabaseReference.getKey();

           final String name = mName.getText().toString();
           final String price = mPrice.getText().toString();
           final  String person = mPerson.getText().toString();
           final String catagory = mSpinner.getSelectedItem().toString();
           final  String Rating = "0";
           final  String ratingCount = "1";
            final  String totalRating = "0";

//           final   String RestName = intent.getStringExtra("restaurantName");

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ dishID);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(addMenu.this, "Uploaded", Toast.LENGTH_SHORT).show();
                         ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                             @Override
                             public void onSuccess(Uri uri) {
                                 imageUrl = uri.toString();

                                 MenuModel menu = new MenuModel(
                                         name,price,catagory,rID,restaurantName,imageUrl,dishID,Rating,ratingCount,totalRating,person
                                 );

                                 mDatabaseReference.setValue(menu);
                            finish();
                             }
                         })   ;

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(addMenu.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

}
