package com.example.drugaddicationtherapysystem;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registering extends AppCompatActivity   {
     EditText name,email,password,confirm;
    TextView AlreadyRegistered;
    Button register;
    FirebaseDatabase mdata;

    // progress bar
    ProgressDialog progressDialog;
    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering);
        //action bar and its title
        //enable back button
        // init
        name=findViewById(R.id.editTextUsername);
        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        confirm=findViewById(R.id.editTextPasswordConfirm);
        register=findViewById(R.id.buttonRegister);
        AlreadyRegistered=findViewById(R.id.AlreadyRegistered);
        AlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registering.this,MainActivity.class));
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //if registered log in
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering User ...");
        // handle register btn
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                startActivity(new Intent(Registering.this,MainActivity.class));

            }

        });

    }

    private void registerUser() {
        progressDialog.show();

        //inputs
        String Email= email.getText().toString().trim();
        String Password= password.getText().toString().trim();
        //Validate
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            //set error
            email.setError("invalid email");
            email.setFocusable(true);
        }
        else if (password.length()<8){
            //set error
            password.setError("password at least 8 characters");
            password.setFocusable(true);
        }

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Registering.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}