package com.example.medicinemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.CookieHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Upload extends AppCompatActivity {
    int i;
    long j;
    public static ArrayList<String> dates = new ArrayList<String>();
    String[] country = { "General", "Psychiatrist", "Surgery", "Dermatology", "Other"};
    String value,name,days,date;
    CheckBox ch, ch1, ch2;
    String personName;
    String personEmail;
    String personId;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Upload.this);
        if (acct != null) {
            personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
             personId = acct.getId();
          //  Toast.makeText(Upload.this,personId,Toast.LENGTH_LONG).show();
        }

        ch  = (CheckBox) findViewById(R.id.cbmorning);
        ch1 = (CheckBox) findViewById(R.id.cbnoon);
        ch2 = (CheckBox) findViewById(R.id.cbnight);
        Button upload = (Button) findViewById(R.id.uploadbtn);
        database = FirebaseDatabase.getInstance();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value=country[i];
                // Toast.makeText(getApplicationContext(),country[i] , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                EditText ed = (EditText) findViewById(R.id.edname);
                EditText ed1 = findViewById(R.id.eddays);
                name = ed.getText().toString();
                days = ed1.getText().toString();
                DatabaseReference myRef3 = database.getReference(personId+"/"+value);
                myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("i").getValue()==null || Integer.parseInt(dataSnapshot.child("i").getValue().toString()) ==0 )
                            j=0;
                        else
                            j = dataSnapshot.child("i").getValue(long.class);

                        final DatabaseReference myRef1 = database.getReference(personId+"/"+value);
                        final Map<String, Object> userUpdates1 = new HashMap<>();
                        j++;
                        final DatabaseReference myRef = database.getReference(personId+"/"+value+"/Prescription"+j);
                        final Map<String, Object> userUpdates = new HashMap<>();
                        userUpdates.put("Tablet name1", name);
                        userUpdates.put("Date",date);
                        userUpdates.put("No of days",days);
                        if (ch.isChecked()) {
                            userUpdates.put("Morning", "Yes");
                        } else {
                            userUpdates.put("Morning", "No");
                        }
                        if (ch1.isChecked()) {
                            userUpdates.put("Afternoon", "Yes");
                        } else {
                            userUpdates.put("Afternoon", "No");
                        }
                        if (ch2.isChecked()) {
                            userUpdates.put("Night", "Yes");
                        } else {
                            userUpdates.put("Night", "No");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(Upload.this);
                        builder.setTitle("CONFIRMATION")
                                .setMessage("Are you sure you want to upload this data? ")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        userUpdates1.put("i",j);
                                        myRef1.updateChildren(userUpdates1);

                                        myRef.setValue(userUpdates);
                                        dialogInterface.dismiss();
                                        Toast.makeText(Upload.this,"Data uploaded on "+ date, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("tag", "Failed to read value.", error.toException());
                    }
                });

//
            }
        });



    }






}
