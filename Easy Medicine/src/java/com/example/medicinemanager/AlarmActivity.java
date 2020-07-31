package com.example.medicinemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm2);
        AlertDialog.Builder builder = new AlertDialog.Builder(AlarmActivity.this);
        builder.setTitle("INSTRUCTION")
                .setMessage("Please ensure that your app is running in background for your alarm to ring.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Show me the demo!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(AlarmActivity.this,tutorial.class);
                        startActivity(j);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

        ImageView imgmorning = findViewById(R.id.imgmorning);
        imgmorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this,Alarm.class);
                startActivity(i);
            }
        });
        ImageView imgafternoon = findViewById(R.id.imgafternoon);
        imgafternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this,Alarm1.class);
                startActivity(i);
            }
        });
        ImageView imgevening = findViewById(R.id.imgevening);
        imgevening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this,Alarm2.class);
                startActivity(i);
            }
        });
        ImageView imgnight = findViewById(R.id.imgnight);
        imgnight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this,Alarm3.class);
                startActivity(i);
            }
        });
    }


    }

