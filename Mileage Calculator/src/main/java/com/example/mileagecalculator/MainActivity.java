package com.example.mileagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float distance;
    float petrol;
    float milage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btncalculate = (Button)findViewById(R.id.btnCaluculate);

        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EditText edpetrol = (EditText) findViewById(R.id.edPetrol);
                    final TextView tvmilage = (TextView) findViewById(R.id.tvMileage);

                    EditText eddistance = (EditText) findViewById(R.id.edDistance);
                    final float distance1 = Float.parseFloat(eddistance.getText().toString());
                    final float petrol1 = Float.parseFloat(edpetrol.getText().toString());
                    final float milage1 = distance1 / petrol1;
                    petrol = petrol1;
                    distance = distance1;
                    milage = milage1;


                    Log.d("Test", String.valueOf(milage1));
                    tvmilage.setTextSize(19);
                    tvmilage.setText("Your Mileage is " + milage1);
                }catch ( java.lang.NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"Enter the values",Toast.LENGTH_LONG).show();
                }

            }
        });
        Button btnhistory = (Button)findViewById(R.id.btnHistroy);
        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,activity_history.class);
                startActivity(i);
            }
        });

        Button btnsubmit = (Button)findViewById(R.id.btnSubmit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper dbHelper = new MyDBHelper(MainActivity.this);
                Records r = new Records(distance,petrol,milage);
                dbHelper.addRecords(r);
                Toast.makeText(MainActivity.this,"Record added",Toast.LENGTH_LONG).show();

            }
        });

    }
}