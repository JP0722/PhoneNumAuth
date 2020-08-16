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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText phone_number,password,string1,string2;
    Button sign_in_but,sign_up_but,add_to_fb;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("BasicUserData");

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

                final String fin_phno="+91"+phone_number.getText().toString();

               databaseReference.orderByChild("phoneNumber").equalTo(fin_phno).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {

                       if(snapshot.exists())
                       {
                          DataSnapshot data= snapshot.getChildren().iterator().next();


                           String passwordFb=data.child("password").getValue().toString();
                            if(passwordFb.equals(password.getText().toString()))
                            {
                              String uRandGenId=data.child("id").getValue().toString();
                              String userName=data.child("name").getValue().toString();
                              Toast.makeText(MainActivity.this, "SIGNED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                              Intent login_int=new Intent(MainActivity.this,HomePage.class);
                              login_int.putExtra("uRandGenId",uRandGenId);
                              login_int.putExtra("phoneNumber",fin_phno);
                              login_int.putExtra("userName",userName);

                              startActivity(login_int);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "WRONG PASSWORD, TRY AGAIN", Toast.LENGTH_SHORT).show();
                            }
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this, "PHONE NUMBER DOESN'T EXIST IN OUR DATABASE", Toast.LENGTH_SHORT).show();
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });

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
