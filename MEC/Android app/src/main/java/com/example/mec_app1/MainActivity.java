package com.example.mec_app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
//import android.os.Handler;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,5000);

    }

    @TargetApi(Build.VERSION_CODES.N)
    void setTriggerto0()
    {
        Log.d("In OK","Entered the setTrigger");
        Map<String, Object> user = new HashMap<>();
        user.replace("trigger", "2");
        db.collection("users");
                db.document("users/B2hzw68AZfJ0Hv0h4ORa")
                    .update("trigger",0);
//                  .add(user)
//                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error adding document", e);
//                    }
//                });
    }
    @TargetApi(Build.VERSION_CODES.N)
    void setTrigger1to0()
    {
        Log.d("In OK","Entered the setTrigger");
        Map<String, Object> user = new HashMap<>();
        user.replace("trigger1", "0");
        db.collection("users");
        db.document("users/B2hzw68AZfJ0Hv0h4ORa")
                .update("trigger1",0);
//                  .add(user)
//                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error adding document", e);
//                    }
//                });
    }


    void setText(QueryDocumentSnapshot document)
    {
        TextView tv = (TextView)findViewById(R.id.waterlevelTV);
        tv.setText(""+document.get("level"));
        tv.setTextColor(getResources().getColor(R.color.colorAccent));
        TextView tvinflow = (TextView)findViewById(R.id.InflowTV);
        tvinflow.setText(""+document.get("inflow"));
        tvinflow.setTextColor(getResources().getColor(R.color.colorAccent));
        TextView tvoutflow = (TextView)findViewById(R.id.OutflowTV);
        tvoutflow.setText(""+document.get("outflow"));
        tvoutflow.setTextColor(getResources().getColor(R.color.colorAccent));
        TextView tvlevelcanal = (TextView)findViewById(R.id.LevelTV);
        tvlevelcanal.setText(""+document.get("level_in_canal"));
        tvlevelcanal.setTextColor(getResources().getColor(R.color.colorAccent));
        TextView link = (TextView)findViewById(R.id.linkTV);
        link.setText("https://docs.google.com/forms/d/e/1FAIpQLSeDpZ68NTP8N6ikdZPQ3jYSreuwaBqG-6RcZyMS4p7aq_aSGQ/viewform?vc=0&c=0&w=1");
        link.setTextColor(getResources().getColor(R.color.colorAccent));
    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    setText(document);
                                    String s = String.valueOf(document.get("trigger"));
                                    String s1 = String.valueOf(document.get("trigger1"));
                                    if(s.equals("1"))
                                    {
                                        Log.d("trigger","triggered");
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("ATTENTION");
                                        builder.setMessage("Gates of Dam2 will open in 30 minutes");
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                setTriggerto0();
                                                finish();
                                            }
                                        });

                                        AlertDialog dialog = builder.create();
                                        dialog.show();

                                    }
                                    //     Log.d("Read", document.getId() + " => " + document.getData());
                                    if(s1.equals("1"))
                                    {
                                        Log.d("trigger1","triggered1");
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("ATTENTION");
                                        builder.setMessage("Gates of Dam1 will open in 30 minutes");
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                setTrigger1to0();
                                                finish();
                                            }
                                        });

                                        AlertDialog dialog = builder.create();
                                        dialog.show();

                                    }
                                }
                            } else {
                                Log.w("Read", "Error getting documents.", task.getException());
                            }
                        }
                    });
            //Toast.makeText(MainActivity.this,"In refresh mode",Toast.LENGTH_LONG).show();
            MainActivity.this.mHandler.postDelayed(m_Runnable, 5000);
        }

    };//runnable
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        finish();

    }
}
