package com.example.talkyourself.Services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.talkyourself.MentalHealthBlog.MHealthBlogAdapter;
import com.example.talkyourself.MentalHealthBlog.mHealthBlogModel;
import com.example.talkyourself.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class MentalHealthBlog extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<mHealthBlogModel> mlist;
    MHealthBlogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_health_blog);


            recyclerView = findViewById(R.id.mhblog);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reference = FirebaseDatabase.getInstance().getReference().child("MentalHealthBlog");



            Query query;
            query = FirebaseDatabase.getInstance().getReference().child("MentalHealthBlog")
                    .orderByChild("title")
                    .limitToFirst(100);


            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mlist = new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        mlist.add(ds.getValue(mHealthBlogModel.class));
                    }
                    adapter = new MHealthBlogAdapter(MentalHealthBlog.this, mlist);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MentalHealthBlog.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }