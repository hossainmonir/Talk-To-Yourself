package com.example.talkyourself.Services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.talkyourself.MedicalRecord.Prescription;
import com.example.talkyourself.R;

public class MedicalRecord extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

    }

    public void PrescriptionList(View view) {
        startActivity(new Intent(this, Prescription.class));
    }
}