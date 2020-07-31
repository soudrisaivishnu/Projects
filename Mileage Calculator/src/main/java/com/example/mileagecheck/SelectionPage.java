package com.example.mileagecheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SelectionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);
        TextView tvtips = findViewById(R.id.tvtips);
        Button btnhistory = findViewById(R.id.btnhistory);
        Button btnmileageaverage = findViewById(R.id.btnaveragemileage);
        Button btnfuellogger=  findViewById(R.id.btnfuellogger);
        btnfuellogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectionPage.this,FuelLogger.class);
                startActivity(i);
            }
        });
        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SelectionPage.this);
                builder.setTitle("SELECTION");
                builder.setMessage("SELECT THE NUMBER OF ENTRIES TO BE SHOWN")
                        .setPositiveButton("5", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //send 5 to retrive data
                                Log.d("test",""+i);
                                Intent j = new Intent(SelectionPage.this,History.class);
                                j.putExtra("count",5);
                                startActivity(j);
                            }
                        })
                        .setNegativeButton("10", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                           //send 10 to retrive data
                                int k=10;

                                Intent j = new Intent(SelectionPage.this,History.class);
                                j.putExtra("count",k);
                                startActivity(j);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
        Button average = (Button)findViewById(R.id.btnaveragemileage);
        average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper dbHelper = new MyDBHelper(SelectionPage.this);
                int a = dbHelper.cursorValue();
                float b = dbHelper.mileageSum();
                float averagevalue = (float) b / a;
                AlertDialog.Builder builder = new AlertDialog.Builder(SelectionPage.this);
                builder.setTitle("AVERAGE MILEAGE")
                        .setMessage("YOUR AVERAGE MILEAGE IS " + averagevalue)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }



}
