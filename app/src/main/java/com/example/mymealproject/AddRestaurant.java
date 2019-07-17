package com.example.mymealproject;

import android.Manifest;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mymealproject.AdminOpenRestaurant.AdminOpenRestaurant;
import com.example.mymealproject.AdminOpenRestaurant.addMenu;
import com.example.mymealproject.DiscoverDishes.DiscoverDishes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class AddRestaurant extends AppCompatActivity implements LocationListener {
    private EditText mRestName, mRestAdress, mRestContact;
    private Button mDone;
    private ImageButton mRestImage,mBtnLocation;
    private String CurrentUID, imageUrl;
    private Uri filePath;
    private double lat,lon;
    private String city = "";
    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 5000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;

    final int REQUEST_CODE = 123;

    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;

    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    LocationManager mLocationManager;
    LocationListener mLocationListener;

    private final int PICK_IMAGE_REQUEST = 10;


    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        mRestName = findViewById(R.id.RestName);
        mRestAdress = findViewById(R.id.RestAdress);
        mRestContact = findViewById(R.id.RestContact);
        mRestImage = findViewById(R.id.RestImage);
        mBtnLocation = findViewById(R.id.locationbtn);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        CurrentUID = Auth.getCurrentUser().getUid();

    }

    public void RestImage(View view) {
        chooseImage();
    }

    public void RestDone(View view) {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
            createRestaurant();
        }

        else {
            Toast.makeText(this, "Enable Location", Toast.LENGTH_SHORT).show();
        }



//Image
    }

    private void createRestaurant() {

        //REstDetails
        final String name = mRestName.getText().toString();
        final String adress = mRestAdress.getText().toString();
        final String contact = mRestContact.getText().toString();
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (filePath != null && !name.isEmpty() && !adress.isEmpty() && !contact.isEmpty()) {




                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                final StorageReference ref = storageReference.child("images/" + CurrentUID);
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                            progressDialog.dismiss();
                                Toast.makeText(AddRestaurant.this, "Uploaded", Toast.LENGTH_SHORT).show();

                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        imageUrl = uri.toString();
                                        dataModelRest rest = new dataModelRest(name, adress, contact, imageUrl, city);
                                        mDatabaseReference.child("Restaurant").child(CurrentUID).setValue(rest);
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(AddRestaurant.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                                Intent intent = new Intent(AddRestaurant.this, AdminOpenRestaurant.class);
                                finish();
                                startActivity(intent);
                            }
                        });

        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }

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
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mRestImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnLocation(View view) {

        onGPS();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//
//        } else {
//            getLocation();
//        }
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (mLocationManager != null) mLocationManager.removeUpdates(mLocationListener);
//    }





    @Override
    protected void onResume() {
        super.onResume();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if ( locationManager != null &&  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mBtnLocation.setBackgroundResource(R.drawable.loc_on);
            getLocation();
        }
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mBtnLocation.setBackgroundResource(R.drawable.loc_off);
            city = "no";
        }
    }
    protected void getLocation() {
        if (isLocationEnabled(AddRestaurant.this)) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
//            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));

            //You can still do this if you like, you might get lucky:
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                           int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                getCity();
                Toast.makeText(AddRestaurant.this, ""+city, Toast.LENGTH_SHORT).show();
            } else {
                //This is what you need:
                Toast.makeText(this, "no network", Toast.LENGTH_SHORT).show();
//                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
            }
        } else {
            //prompt user to enable location....
            //.................


        }
    }
    public void getCity() {
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationManager.removeUpdates(this);

        //open the map:
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public static boolean isLocationEnabled(Context context) {
        //...............
        return true;
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable Location").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
