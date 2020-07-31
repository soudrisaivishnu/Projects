package com.example.mileagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mileagecalculator.MyDBHelper;


public class activity_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        TableLayout tableLayout = (TableLayout)findViewById(R.id.tablelayout);

        MyDBHelper dbHelper = new MyDBHelper(activity_history.this);
        dbHelper.getRecords();
        Log.d("Test", String.valueOf(MyDBHelper.distance_id[13]));
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        TextView tv = (TextView)findViewById(R.id.tv);
        for(int i=0;i<10;i++) {
            String go ="  "+ MyDBHelper.distance_id[i] + "      " + MyDBHelper.petrol_id[i] + "        " + MyDBHelper.mileage_id[i];
            TextView tv1 = new TextView(activity_history.this);
            tv1.setText(go);
            tv1.setPadding(30,30,30,30);
            tv1.setTextColor(getResources().getColor(R.color.colorAccent));
            Log.d("Test",go);
            tv1.setText(go);
            linearLayout.addView(tv1);
        }

        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=10;i>0;i--){
//                Records r = new Records(0,0,0);
//                MyDBHelper db = new MyDBHelper(activity_history.this);
//                db.updaterecords(r,i);
                    MyDBHelper db = new MyDBHelper(activity_history.this);
                    db.deleteitem(i);
                    Log.d("Delete","in delete");
            }
                }
        });

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