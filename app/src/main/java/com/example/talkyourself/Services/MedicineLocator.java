package com.example.talkyourself.Services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.talkyourself.MedicinLocator.MedicinModel;
import com.example.talkyourself.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MedicineLocator extends AppCompatActivity {

    ListView listview;
    SearchView searchView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    MedicinModel medicalmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_locator);

        listview = findViewById(R.id.medicinlocator);
        searchView = findViewById(R.id.search);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("MedicineLocator");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.medicinelist, R.id.name, list);


        Query query = FirebaseDatabase.getInstance().getReference("MedicineLocator")
                .orderByChild("Name").limitToLast(1000);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    medicalmodel = ds.getValue(MedicinModel.class);
                    list.add(medicalmodel.getName() + ", " + medicalmodel.getWeight() + " - "+ medicalmodel.getCompany()+ '\n'
                             + medicalmodel.getStoreName() + '\n'+ "Expire Date : " + medicalmodel.getExpiredate());
                }
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });







    }
}