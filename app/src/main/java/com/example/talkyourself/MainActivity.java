package com.example.talkyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.talkyourself.Services.DoctorAppointment;
import com.example.talkyourself.Services.MedicineLocator;
import com.example.talkyourself.Services.MentalHealthBlog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void MedicalRecord(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void DoctorAppointment(View view) {
        startActivity(new Intent(this, DoctorAppointment.class));
    }

    public void MentalHealthBlog(View view) {
        startActivity(new Intent(this, MentalHealthBlog.class));

    }

    public void MedicineLocator(View view) {
        startActivity(new Intent(this, MedicineLocator.class));
    }
}