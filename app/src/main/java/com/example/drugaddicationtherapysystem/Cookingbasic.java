package com.example.drugaddicationtherapysystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class Cookingbasic extends AppCompatActivity {
    PDFView pdfview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookingbasic);
        pdfview=findViewById(R.id.pdfviewopen);
        pdfview.fromAsset("Cooking.pdf").load();
    }
}