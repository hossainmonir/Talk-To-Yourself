package com.example.talkyourself.DoctorAppointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talkyourself.MedicalRecord.PatientModel;
import com.example.talkyourself.ProfileActivity;
import com.example.talkyourself.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity{

    TextView dfname, dqualify, dconselling;
    private FirebaseAuth mAuth;

    EditText editEmail;
    EditText editPass;
    Button btn_login;
    AlertDialog.Builder alert;

    EditText patientName;
    EditText patientEmail;
    EditText patientPhone;

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        mAuth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String qualify = i.getStringExtra("qualify");
        String conselling = i.getStringExtra("conselling");



        dfname = findViewById(R.id.name);
        dfname.setText(name);

        dqualify = findViewById(R.id.qualify);
        dqualify.setText(qualify);

        dconselling = findViewById(R.id.conselling);
        dconselling.setText(conselling);

        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();

        progressDialog = new ProgressDialog(DoctorDetails.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Patient").child(muser.getUid());

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                PatientModel patientModel = dataSnapshot.getValue(PatientModel.class);

                patientName = findViewById(R.id.namePatient);
                patientEmail = findViewById(R.id.emailPatient);
                patientPhone = findViewById(R.id.phonePatient);

                patientName.setText(patientModel.getPatientName());
                patientEmail.setText(patientModel.getPatientEmail());
                patientPhone.setText(patientModel.getPatientPhone());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(DoctorDetails.this, "Couldn't get data.", Toast.LENGTH_SHORT).show();
            }
        };
        mDatabase.addValueEventListener(postListener);
    }


    public AlertDialog BookAppointment(View view) {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            openDialog();
            
        } else {
            Appointment();
        }

        return null;
    }


    private void openDialog() {

        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_signin, null);

        editEmail = view.findViewById(R.id.username);
        editPass = view.findViewById(R.id.password);
        btn_login = view.findViewById(R.id.dilogLogin);

        alert.setView(view);
        alert.setCancelable(false);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilogLogin();
            }
        });


        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    private void dilogLogin(){

        String email = editEmail.getText().toString();
        String password = editPass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(DoctorDetails.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                editEmail.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(DoctorDetails.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            startActivity(getIntent());
                        }
                    }
                });

    }
    private void Appointment() {

        String user_id = mAuth.getCurrentUser().getUid();
        String key =  mDatabase.push().getKey();
        DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("Appointment").child(key);

        // User Details

        String pName = patientName.getText().toString();
        String pEmail = patientEmail.getText().toString();
        String dName = dfname.getText().toString();
        String aime = " ";


        HashMap<String, Object> newPost = new HashMap<>();

        newPost.put("patientId", user_id);
        newPost.put("patientName", pName);
        newPost.put("patientEmail", pEmail);
        newPost.put("doctorName", dName);
        newPost.put("appointmentTime", aime);

        DB.setValue(newPost);

        Intent intent = new Intent(DoctorDetails.this, ProfileActivity.class);
        startActivity(intent);
        finish();


    }
}