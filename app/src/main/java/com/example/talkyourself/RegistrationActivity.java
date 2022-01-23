package com.example.talkyourself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talkyourself.MedicalRecord.PatientModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    EditText editName, editEmail, editPhone, editBlood, editAge, editAddress, editPassword;
    Button SignUpBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editName = findViewById(R.id.editTextName);
        editEmail = findViewById(R.id.editTextEmail);
        editPhone = findViewById(R.id.editTextPhone);
        editBlood = findViewById(R.id.editTextBlood);
        editAge = findViewById(R.id.editTextAge);
        editAddress = findViewById(R.id.editTextAddress);
        editPassword = findViewById(R.id.editTextPassWord);
        SignUpBtn = findViewById(R.id.Regbutton);

        auth = FirebaseAuth.getInstance();

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String age = editAge.getText().toString().trim();
                String blood = editBlood.getText().toString().trim();
                String address = editAddress.getText().toString().trim();




                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(getApplicationContext(), "Enter Fu;; Address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(blood)) {
                    Toast.makeText(getApplicationContext(), "Enter Blood Group!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(getApplicationContext(), "Enter Age Number!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, Enter Minimum 6 Characters!", Toast.LENGTH_LONG).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegistrationActivity.this, "Add Member Successfully :" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                                // Warning

                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed. Try Again" + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {

                                    String user_id = auth.getCurrentUser().getUid();
                                    DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("Patient");

                                    // User Details

                                    String patientPrevApp=" N/A";
                                    String patientNextApp=" N/A";


                                    PatientModel model = new PatientModel(name, email,  age,
                                            blood, address, phone,
                                             patientPrevApp,  patientNextApp);

                                    DB.child(user_id).setValue(model);

                                    Intent intent = new Intent(RegistrationActivity.this, ProfileActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                        });
            }
        });





    }

    public void gotologin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}