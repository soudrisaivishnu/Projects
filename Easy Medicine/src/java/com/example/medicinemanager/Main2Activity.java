package com.example.medicinemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main2Activity extends AppCompatActivity {

    String personName;
    String personEmail;
    String personId;
    String value;
    long j;
    public static ArrayList<String> dates = new ArrayList<String>();
    String[] country = { "General", "Psychiatrist", "Surgery", "Dermatology", "Other"};
    FirebaseDatabase database;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Button upload = (Button)findViewById(R.id.btnupload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this,Upload.class);
                startActivity(i);
            }
        });
        Button history = (Button)findViewById(R.id.btnhistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this,History.class);
                startActivity(i);
            }
        });
        Button alarm = findViewById(R.id.btnalarm);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,AlarmActivity.class);
                startActivity(i);
            }
        });
        // to delete

        Spinner spin = findViewById(R.id.spinner2);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Main2Activity.this);
        if (acct != null) {
            personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            personId = acct.getId();
        }

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value=country[i];
                Toast.makeText(getApplicationContext(),"You have selected "+country[i] , Toast.LENGTH_SHORT).show();
              // Toast.makeText(Main2Activity.this,personId+"/"+value,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
         database = FirebaseDatabase.getInstance();
        Button btndelete = findViewById(R.id.btndelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference myRef = database.getReference(personId+"/"+value);
                final Map<String, Object> userUpdates = new HashMap<>();
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("i").getValue()==null || Integer.parseInt(dataSnapshot.child("i").getValue().toString()) ==0 ) {
                            j = 0;
                            Toast.makeText(Main2Activity.this,"There is no data available to be deleted",Toast.LENGTH_LONG).show();
                        }
                            else {
                            j = dataSnapshot.child("i").getValue(long.class);
                           // Toast.makeText(Main2Activity.this,""+j,Toast.LENGTH_LONG).show();
                            final DatabaseReference myRef1 = database.getReference(personId+"/"+value+"/Prescription"+j);

                            myRef1.removeValue();
                            j--;
                            userUpdates.put("i",j);
                            myRef.updateChildren(userUpdates);
                           Toast.makeText(Main2Activity.this,"Data is successfully deleted",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(Main2Activity.this,"There is an error, please try after some time ",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
Button btntutorial = findViewById(R.id.btntutorial);
btntutorial.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(Main2Activity.this,tutorial.class);
        startActivity(i);
    }
});


    }
}
