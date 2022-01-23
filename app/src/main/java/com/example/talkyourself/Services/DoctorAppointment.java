package com.example.talkyourself.Services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;
import com.example.talkyourself.DoctorAppointment.DoctorAppointmentAdapter;
import com.example.talkyourself.DoctorAppointment.DoctorAppointmentModel;
import com.example.talkyourself.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorAppointment extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<DoctorAppointmentModel> mlist;
    DoctorAppointmentAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        searchView = findViewById(R.id.searchbar);
        recyclerView = findViewById(R.id.doctorlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference().child("Psychiatrist");



        Query query;
        query = FirebaseDatabase.getInstance().getReference().child("Psychiatrist")
                .orderByChild("name")
                .limitToFirst(100);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mlist = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mlist.add(ds.getValue(DoctorAppointmentModel.class));
                }
                adapter = new DoctorAppointmentAdapter(DoctorAppointment.this, mlist);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DoctorAppointment.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void search(String newText) {
        ArrayList <DoctorAppointmentModel> mylist =  new ArrayList<>();
        for(DoctorAppointmentModel object : mlist){
            if(object.getDoctorName().toLowerCase().contains(newText.toLowerCase()) ||
                    object.getDoctorQuali().toLowerCase().contains(newText.toLowerCase()) ||
                    object.getDoctorConselling().toLowerCase().contains(newText.toLowerCase())){
                mylist.add(object);
            }

        }
        DoctorAppointmentAdapter adapterClass = new DoctorAppointmentAdapter(mylist);
        recyclerView.setAdapter(adapterClass);
    }

}


