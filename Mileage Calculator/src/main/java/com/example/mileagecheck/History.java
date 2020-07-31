package com.example.mileagecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent k = getIntent();
        int count = k.getIntExtra("count", 0);

        Toast.makeText(History.this, "" + count, Toast.LENGTH_LONG).show();
        TextView tv = new TextView(History.this);
        MyDBHelper dbHelper = new MyDBHelper(History.this);
        dbHelper.getRecords();
        String distance1 = "" + MyDBHelper.distance_id;
        String petrol1 = "" + MyDBHelper.petrol_id;
        String amount1 = "" + MyDBHelper.amount_id;
        String mileage1 = "" + MyDBHelper.mileage_id;
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        Toast.makeText(this, "" + distance1, Toast.LENGTH_SHORT).show();
        ArrayList<Float> list = new ArrayList<>();
        int b = dbHelper.cursorValue();
        Toast.makeText(this, "" + b, Toast.LENGTH_LONG).show();
        if (b > count) {
            for (int i = b-1; i >= b - count; i--) {
                TableRow r = new TableRow(History.this);
                list.add(0, MyDBHelper.distance_id[i]);
                list.add(1, MyDBHelper.petrol_id[i]);
                list.add(2, MyDBHelper.amount_id[i]);
                list.add(3, MyDBHelper.mileage_id[i]);
                Log.d("Test1234", "" + MyDBHelper.mileage_id[i]);
                for (int j = 0; j < 4; j++) {
                    TextView tv1 = new TextView(History.this);
                    tv1.setText("" + list.get(j));
                    tv1.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv1.setPadding(0, 35, 35, 35);
                    r.addView(tv1);
                }
                table.addView(r);
            }

        }
        else
        {
            for (int i = b-1; i >=0; i--) {
                TableRow r = new TableRow(History.this);
                list.add(0, MyDBHelper.distance_id[i]);
                list.add(1, MyDBHelper.petrol_id[i]);
                list.add(2, MyDBHelper.amount_id[i]);
                list.add(3, MyDBHelper.mileage_id[i]);
                Log.d("Test1234", "" + MyDBHelper.mileage_id[i]);
                for (int j = 0; j < 4; j++) {
                    TextView tv1 = new TextView(History.this);
                    tv1.setText("" + list.get(j));
                    tv1.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv1.setPadding(0, 35, 35, 35);
                    r.addView(tv1);
                }
                table.addView(r);
            }
        }
    }
}
