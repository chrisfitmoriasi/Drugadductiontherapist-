package com.example.drugaddicationtherapysystem;

import android.content.Intent;
import android.os.Bundle;
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

public class HomeTherapist extends AppCompatActivity {
    Button signthera;
    TextView RegisterT;
    EditText textemail, password;
    FirebaseAuth mAuth;

    // EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_therapist);
        RegisterT = findViewById(R.id.registertherapist);
        textemail = findViewById(R.id.TherapistEmail);
        password = findViewById(R.id.Therapistpassword);
        signthera = findViewById(R.id.Login_button);
        mAuth = FirebaseAuth.getInstance();
        signthera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });


        RegisterT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterinTherapist.class));
            }
        });

    }
    public void logIn() {
        String email = textemail.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (email.isEmpty()) {
            textemail.setError("Field can't be empty");
            return;
        } else if (Password.isEmpty()) {
            password.setError("Field can't be empty");
        }
        mAuth.signInWithEmailAndPassword(textemail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(),Addtherapist.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            }
        });

    }
}