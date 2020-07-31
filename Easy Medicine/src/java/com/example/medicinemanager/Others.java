package com.example.medicinemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Others extends AppCompatActivity {

    String personName;
    String personEmail;
    String personId;
    int i;
    TextView tv1;
    FirebaseDatabase database;
    LinearLayout linearLayout,linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Others.this);
        if (acct != null) {
            personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
             personEmail = acct.getEmail();
             personId = acct.getId();
        }

        database = FirebaseDatabase.getInstance();
        linearLayout = findViewById(R.id.linearlayout5);
        //  linearLayout2 = findViewById(R.id.linearlayout3);
        final long[] j = new long[1];
        final TextView[] myTextViews = new TextView[(int) j[0]];
        for ( i = 0; i <30; i++) {
            DatabaseReference myRef = database.getReference(personId+"/"+"Other/Prescription"+i);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final TextView rowTextView = new TextView(Others.this);
                    final TextView rowTextView2 = new TextView(Others.this);
                    final TextView rowTextView1 = new TextView(Others.this);
                    rowTextView1.setText("");
                    rowTextView1.setTextColor(getResources().getColor(R.color.colorAccent));
                    rowTextView1.setTextSize(24);
                    rowTextView2.setText("Tablet name : "+dataSnapshot.child("Tablet name1").getValue());
                    rowTextView2.setTextColor(getResources().getColor(R.color.colorAccent));
                    rowTextView2.setTextSize(24);
                    rowTextView.setText("" + dataSnapshot.getValue());
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
