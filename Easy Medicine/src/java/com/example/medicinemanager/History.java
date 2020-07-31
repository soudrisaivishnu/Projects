package com.example.medicinemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ImageView img = (ImageView)findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(History.this,General.class);
                startActivity(i);
              //  Toast.makeText(History.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView img2 = (ImageView)findViewById(R.id.surgeryimg);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(History.this,Surgery.class);
                startActivity(i);
            }
        });
        ImageView img3 = (ImageView)findViewById(R.id.imgderm);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(History.this,Dermatology.class);
                startActivity(i);
            }
        });
        ImageView img4 = (ImageView)findViewById(R.id.imgpysc);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(History.this,Psychology.class);
                startActivity(i);
            }
        });
        ImageView img5 = (ImageView)findViewById(R.id.imgother);
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(History.this,Others.class);
                startActivity(i);
            }
        });
    }
}
