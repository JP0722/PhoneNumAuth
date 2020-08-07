package com.example.phonenumberauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText phone_number,password,string1,string2;
    Button sign_in_but,sign_up_but,add_to_fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone_number=findViewById(R.id.phone_number);
        password=findViewById(R.id.password);
        sign_in_but=findViewById(R.id.sign_in_but);
        sign_up_but=findViewById(R.id.sign_up_but);


        sign_in_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sign_up_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authInt=new Intent(MainActivity.this,PhoneAuthAct.class);
                startActivity(authInt);
            }
        });


    }



}
