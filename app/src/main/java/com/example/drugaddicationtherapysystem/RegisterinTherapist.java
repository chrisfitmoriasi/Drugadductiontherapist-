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

public class RegisterinTherapist extends AppCompatActivity {
    TextView AlredyRegistered;
    EditText textemail;
    EditText password;
    Button Submitting;
    FirebaseDatabase mdata;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerin_therapist);
        textemail=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        Submitting=findViewById(R.id.btnRegister);

        Submitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeTherapist.class));
        }
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering User ...");
        // handle register btn
        Submitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                startActivity(new Intent(RegisterinTherapist.this,HomeTherapist.class));

            }

        });
    }
    private void registerUser() {
        progressDialog.show();

        //inputs
        String Email= textemail.getText().toString().trim();
        String Password= password.getText().toString().trim();
        //Validate
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            //set error
            textemail.setError("invalid email");
            textemail.setFocusable(true);
        }
        else if (password.length()<10){
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
                Toast.makeText(RegisterinTherapist.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        AlredyRegistered=findViewById(R.id.AlreadyRegisteredTherapist);
             AlredyRegistered.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(RegisterinTherapist.this,HomeTherapist.class));
    }
});
    }
}