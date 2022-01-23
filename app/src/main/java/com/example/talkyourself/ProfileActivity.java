package com.example.talkyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talkyourself.MedicalRecord.PatientModel;
import com.example.talkyourself.Services.DoctorAppointment;
import com.example.talkyourself.Services.MedicalRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private TextView name, email, phone, lastappoint, nextappoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();

        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Patient").child(muser.getUid());

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                PatientModel patientModel = dataSnapshot.getValue(PatientModel.class);

                name = findViewById(R.id.userName);
                email = findViewById(R.id.userEmail);
                phone = findViewById(R.id.userPhone);
                lastappoint = findViewById(R.id.lastaapointment);
                nextappoint = findViewById(R.id.nextappointment);


                name.setText(patientModel.getPatientName());
                email.setText(patientModel.getPatientEmail());
                phone.setText(patientModel.getPatientPhone());
                lastappoint.setText(patientModel.getPatientPrevApp());
                nextappoint.setText(patientModel.getPatientNextApp());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(ProfileActivity.this, "Couldn't get data.", Toast.LENGTH_SHORT).show();
            }
        };
        mDatabase.addValueEventListener(postListener);
    }


    public void AppointmentBtn(View view) {
        startActivity(new Intent(ProfileActivity.this, DoctorAppointment.class));
        ProfileActivity.this.finish();
    }

    public void logOutBtn(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        ProfileActivity.this.finish();

    }

    public void QuitApp(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void mMedicalRecord(View view) {
        startActivity(new Intent(ProfileActivity.this, MedicalRecord.class));
        ProfileActivity.this.finish();

    }
}
