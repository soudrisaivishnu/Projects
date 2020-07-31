package com.example.mileagecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FuelLogger extends AppCompatActivity {
float distance1;
float petrol1;
float amount1;
float mileage1;
float cost1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_logger);


        Button btncalculate = (Button)findViewById(R.id.btncalculate);
        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                   EditText eddistance = (EditText)findViewById(R.id.eddistance);
                   EditText edpetrol = (EditText)findViewById(R.id.edpetrol);
                   EditText edamount = (EditText)findViewById(R.id.edamount);
                   float distance=Float.parseFloat(eddistance.getText().toString());
                    float petrol = Float.parseFloat(edpetrol.getText().toString());
                 float amount = Float.parseFloat(edamount.getText().toString());
                final TextView tv1 = (TextView)findViewById(R.id.tvMileage);

                final TextView tv2 = (TextView)findViewById(R.id.tvcostperliter);

               float  mileage =  distance/petrol;
         float cost = amount/distance;
         distance1=distance;
         petrol1=petrol;
         amount1=amount;
         mileage1=mileage;
         cost1=cost;
                final String mileage1 = ""+mileage+" KMS";
                final String cpl = ""+cost+" RUPEES";
                tv1.setText(mileage1);
                tv2.setText(cpl);
            }catch (java.lang.NumberFormatException e)
               {
                       Toast.makeText(FuelLogger.this,"Enter the values",Toast.LENGTH_LONG).show();

               }
            }

   });
        Button btnsubmit = (Button)findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db = new MyDBHelper(FuelLogger.this);
                Records records = new Records(distance1,petrol1,amount1,mileage1);

                Log.d("Test values",""+distance1+" "+petrol1+" "+amount1+" "+mileage1);
                Toast.makeText(FuelLogger.this,"SUBMITTED",Toast.LENGTH_LONG).show();
                db.addRecords(records);
            }
        });
    }
}
