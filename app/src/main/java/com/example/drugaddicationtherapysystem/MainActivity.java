package com.example.drugaddicationtherapysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity  {
    private TextInputLayout textEmail;
    private TextInputLayout textPassword;
     EditText logedit,logpass;
    Button logging, singingup;
    TextView ForgetPassword,SignIn;
    FirebaseAuth mAuth;
    private String email;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textEmail = findViewById(R.id.text_input_email);
        textPassword = findViewById(R.id.text_input_password);
        logging = (Button) findViewById(R.id.logging);
        singingup = (Button) findViewById(R.id.singingup);
        SignIn=(TextView)findViewById(R.id.Therapist);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeTherapist.class));
            }
          });
        logedit = findViewById(R.id.login_editt);
        mAuth = FirebaseAuth.getInstance();
        ForgetPassword=(TextView)findViewById(R.id.Forgetpassword);
        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Forgetpassword.class));
            }
        });
        logpass = findViewById(R.id.password_editt);

        logging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });

        singingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registering.class));
            }
        });
    }
    private void LogIn() {
        String email = Objects.requireNonNull(textEmail.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(textPassword.getEditText()).getText().toString().trim();
        if (email.isEmpty()) {
            textEmail.setError("Field can't be empty");
            return;
        }
       /* else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        }*/
        else  if (password.isEmpty()) {
            textPassword.setError("Field can't be empty");
        }
        mAuth.signInWithEmailAndPassword(logedit.getText().toString(),logpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT);
            }
        });
    }
    }
