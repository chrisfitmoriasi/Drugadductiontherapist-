package com.example.drugaddicationtherapysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class Dashboard extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mainGrid=(GridLayout)findViewById(R.id.mainGrid);
        MaterialCardView resources = (MaterialCardView) findViewById(R.id.resources);
        MaterialCardView Learningskills=(MaterialCardView)findViewById(R.id.learningskills);
        MaterialCardView profile=(MaterialCardView)findViewById(R.id.profilee);
        MaterialCardView selftrack=(MaterialCardView)findViewById(R.id.selftracking);
        MaterialCardView associates= (MaterialCardView)findViewById(R.id.associates);
        MaterialCardView chatchat=(MaterialCardView)findViewById(R.id.chatrooom);
        chatchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChatRoom.class));
            }
        });

        associates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),messaging.class));
            }
        });
        selftrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Monitering.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Profile.class));

            }
        });
        Learningskills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LearningHere.class));
            }
        });
        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Reasources.class));
            }
        });

        //setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //loop all child item in the Grid
        int i;
        for(i = 0; i<mainGrid.getChildCount(); i++);
        {
            MaterialCardView cardView = (MaterialCardView) mainGrid.getChildAt(i);
            final int finalI = i;
             cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) // open an activity
                    {
                        Intent intent =new Intent(Dashboard.this,Reasources.class);
                        startActivity(intent);
                    }
                    else if (finalI==1)
                    {
                        Intent intent =new Intent(Dashboard.this,LearningSkills.class);
                        startActivity(intent);
                    }
                    else if (finalI==2)
                    {
                        Intent intent =new Intent(Dashboard.this,Associates.class);
                        startActivity(intent);
                    }
                    //Toast.makeText(Dashboard.this, "Clicked at index" + finalI,Toast.LENGTH_LONG).show();


                }
            });
        }
    }
}