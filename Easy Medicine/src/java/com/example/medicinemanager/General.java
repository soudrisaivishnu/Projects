package com.example.medicinemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class General extends AppCompatActivity {

    String personName;
    String personEmail;
    String personId;
    int i;
    FirebaseDatabase database;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        database = FirebaseDatabase.getInstance();
        linearLayout = findViewById(R.id.linearlayout);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(General.this);
        if (acct != null) {
            personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
             personEmail = acct.getEmail();
             personId = acct.getId();

        }

        for ( i = 0; i <30; i++) {
            DatabaseReference myRef = database.getReference(personId+"/"+"General/Prescription"+i);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                { final TextView rowTextView = new TextView(General.this);
                    final TextView rowTextView2 = new TextView(General.this);
                    final TextView rowTextView1 = new TextView(General.this);
                    rowTextView1.setTextSize(24);
                    rowTextView1.setText("");
                    rowTextView1.setTextColor(getResources().getColor(R.color.colorAccent));
                    rowTextView2.setText("Tablet name : "+dataSnapshot.child("Tablet name1").getValue());
                    rowTextView2.setTextColor(getResources().getColor(R.color.colorAccent));
                    rowTextView.setText("" + dataSnapshot.getValue());
                    rowTextView2.setTextSize(24);
                    rowTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                    ;
                    if (dataSnapshot.getValue() != null)
                    {
                        linearLayout.addView(rowTextView2);
                        linearLayout.addView(rowTextView);
                        linearLayout.addView(rowTextView1);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error)
                {
                    Log.w("tag", "Failed to read value.", error.toException());
                }
            });

        }
    }
}

