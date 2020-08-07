package com.example.phonenumberauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText etName,password1,password2,etCalender;
    ImageButton but_date;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button but_create_acc;
    String phoneNumber;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        phoneNumber=getIntent().getStringExtra("phoneNumber");
        but_date=findViewById(R.id.but_date);
        etCalender=findViewById(R.id.etCalender);
        radioGroup=findViewById(R.id.radioGroup);
        etName=findViewById(R.id.etName);
        password1=findViewById(R.id.password1);
        password2=findViewById(R.id.password2);
        but_create_acc=findViewById(R.id.but_create_acc);
        databaseReference= FirebaseDatabase.getInstance().getReference("BasicUserData");
        but_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        but_create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(CreateProfile.this, etName.getText().toString(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(CreateProfile.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(CreateProfile.this, etCalender.getText().toString(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(CreateProfile.this,password1.getText().toString(), Toast.LENGTH_SHORT).show();

                if(password1.getText().toString().equals(password2.getText().toString())) {
                    String id = databaseReference.push().getKey();
                    SaveProfileClass obj = new SaveProfileClass(id, phoneNumber, etName.getText().toString(),
                            etCalender.getText().toString(), radioButton.getText().toString(), password1.getText().toString());

                    databaseReference.child(id).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CreateProfile.this, "ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CreateProfile.this, "ACCOUNT CREATION FAILED", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Intent main_int = new Intent(CreateProfile.this, MainActivity.class);
                    startActivity(main_int);
                }
                else
                {
                    Toast.makeText(CreateProfile.this, "PASSWORDS DIDN'T MATCH TRY AGAIN", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= Integer.toString(dayOfMonth)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year);
        etCalender.setText(currentDateString);

    }

    public void checkButton(View v)
    {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
