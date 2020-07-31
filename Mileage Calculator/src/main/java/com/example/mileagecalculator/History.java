package com.example.mileagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mileagecalculator.MyDBHelper;


public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TableLayout tableLayout = (TableLayout)findViewById(R.id.tablelayout);

        MyDBHelper dbHelper = new MyDBHelper(History.this);
        dbHelper.getRecords();
        Log.d("Test", String.valueOf(MyDBHelper.distance_id[13]));
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        TextView tv = (TextView)findViewById(R.id.tv);
        for(int i=0;i<10;i++) {
            String go ="  "+ MyDBHelper.distance_id[i] + "      " + MyDBHelper.petrol_id[i] + "        " + MyDBHelper.mileage_id[i];
            TextView tv1 = new TextView(History.this);
            tv1.setText(go);
            tv1.setPadding(30,30,30,30);
            Log.d("Test",go);
            tv1.setText(go);
            linearLayout.addView(tv1);
        }
        //        for (int i= 0;i<10;i++)
//        {
//            TableRow row = new TableRow(History.this);
//            for(int j=0;j<3;j++)
//            {
//                TextView tv = new TextView(History.this);
//                tv.setText(" "+ MyDBHelper.distance_id[i]);
//                row.addView(tv);
//            }
//            tableLayout.addView(row);
//        }


    }
}