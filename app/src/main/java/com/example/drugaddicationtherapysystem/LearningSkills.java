package com.example.drugaddicationtherapysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public  class LearningSkills extends AppCompatActivity{
    RadioGroup radioGroup;
    DatabaseReference mdatabase;
    Button logout;
    FirebaseUser user;
    RadioButton Driving,SoftSkills,PhysicalWork,Catering,Business,Profession;
    Button btnSELECT;
    String learnskills;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_skills);
        user = FirebaseAuth.getInstance().getCurrentUser();
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        addListenerOnButtonClick();
        //reference= database.getInstance().getReference().child("Career");
        // career new=Career();

    }
    private void addListenerOnButtonClick() {
        //Getting instance of CheckBoxes and Button from the activity_learning_skills.xml file
        Driving=findViewById(R.id.checkBox);
        SoftSkills= findViewById(R.id.checkBox2);
        PhysicalWork= findViewById(R.id.checkBox3);
        Catering = findViewById(R.id.checkBox4);
        Business= findViewById(R.id.checkBox5);
        Profession= findViewById(R.id.checkBox6);
        radioGroup = findViewById(R.id.radiogroup);

        int selectedid = radioGroup.getCheckedRadioButtonId();
        RadioButton radioradio = (RadioButton) findViewById(selectedid);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioradio = (RadioButton) findViewById(i);
                String learnskills = radioradio.getText().toString();
                Toast.makeText(getApplicationContext(), "You have selected " + learnskills + " as your learning skill", Toast.LENGTH_SHORT).show();
                mdatabase = FirebaseDatabase.getInstance().getReference("DRUGADDCTIONS");
                mdatabase.child("career").child(user.getUid()).child("track").setValue(learnskills).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                mdatabase.child("career").child(user.getUid()).child("Email").setValue(user.getEmail()).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }

}
