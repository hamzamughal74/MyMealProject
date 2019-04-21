package com.example.mymealproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private EditText mName;

    private  EditText mEmailadress;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private DatabaseReference mDatabaseReference;
    private ProgressBar mProgressBar;


    // Firebase instance veriable
    private FirebaseAuth mAuth;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button button = findViewById(R.id.btnLoginPanel);
        mName = findViewById(R.id.name);
        mEmailadress = findViewById(R.id.emailadress);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirmPassword);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);

            }
        });

        //TODO: get hold of an instance of firebase auth
        mAuth = FirebaseAuth.getInstance();

    }
public void attemptSignUp(View view){

register();

}
private  void register(){
String email =mEmailadress.getText().toString();
String password = mPassword.getText().toString();
String name = mName.getText().toString();

    mEmailadress.setError(null);
    mPassword.setError(null);



    boolean cancel = false;
    View focusView = null;
    if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
        mPassword.setError(getString(R.string.error_invalid_password));
        focusView = mPassword;
        cancel = true;
    }
    if (TextUtils.isEmpty(email)) {
        mEmailadress.setError(getString(R.string.error_field_required));
        focusView = mEmailadress;
        cancel = true;
    } else if (!isEmailValid(email)) {
        mEmailadress.setError(getString(R.string.error_invalid_email));
        focusView = mEmailadress;
        cancel = true;
    }

    if (cancel) {
        // There was an error; don't attempt login and focus the first
        // form field with an error.
        focusView.requestFocus();
    } else {
        // TODO: Call create FirebaseUser() here
        createFirebaseUser(email,password,name);

    }
}


    private  boolean isEmailValid(String email){
        return  email.contains("@");


    }
   private  boolean isPasswordValid(String password){
         String confirmPassword = mConfirmPassword.getText().toString();
         return confirmPassword.equals(password)&& password.length()>4;



   }
    //TODO : Create Firebase user
    private  void   createFirebaseUser(String email, String password, final String name){

    mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                   Users user = new Users(
                      name
                   ) ;

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);



                    Intent intent = new Intent(SignUp.this, SignIn.class);
                    finish();
                    startActivity(intent);
                }
                }
            });

    }
    //TODO: alert dialog to show in case registration failed
    private void  showErrorDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}