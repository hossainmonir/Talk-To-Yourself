package com.example.talkyourself.MedicalRecord;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.talkyourself.DoctorAppointment.DoctorAppointmentAdapter;
import com.example.talkyourself.DoctorAppointment.DoctorAppointmentModel;
import com.example.talkyourself.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Prescription extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<PrescriptionModel> mlist;
    PrescriptionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = findViewById(R.id.preslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        Query query;
        query = FirebaseDatabase.getInstance().getReference().child("Prescription").child(userId.getUid())
                .orderByChild("name")
                .limitToFirst(100);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mlist = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mlist.add(ds.getValue(PrescriptionModel.class));
                }
                adapter = new PrescriptionAdapter(Prescription.this, mlist);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Prescription.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}