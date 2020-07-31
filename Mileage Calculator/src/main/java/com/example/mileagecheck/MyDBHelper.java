package com.example.mileagecheck;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    public static float[] Id = new float[15];
    public static float[] distance_id = new float[15];
    public static float[] petrol_id = new float[15];
    public static float[] mileage_id = new float[15];
    public static float[] amount_id = new float[15];
    //database details
    private static final String DATABASE_NAME = "RecordsDB";
    private static final int DATABASE_VERSION= 1;
    //table details
    private static final String TABLE_NAME = "Records";
    // private static final String ID = "Id";
    private static final String ID = "id";
    private static final String DISTANCE_ID = "Distance";
    private static final String PETROL_ID = "Petrol";
    private static final String MILAGE_ID = "Milage";
    private static final String AMOUNT_ID = "Amount";
    String id ="1";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createRecords = "CREATE TABLE "+ TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DISTANCE_ID+" FLOAT, "+PETROL_ID+" FLOAT, "+AMOUNT_ID+" FLOAT, "+MILAGE_ID+" FLOAT )";
        Log.d("TAG",createRecords);
        db.execSQL(createRecords);
        Log.d("TAG",createRecords);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  void addRecords(Records records)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DISTANCE_ID,records.getDistance());
        values.put(PETROL_ID,records.getPetrol());
        values.put(AMOUNT_ID,records.getAmount());
        values.put(MILAGE_ID,records.getMilage());
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d("Test db", "addRecords: "+DISTANCE_ID+" "+PETROL_ID+" "+AMOUNT_ID+" "+MILAGE_ID);

    }


    public void getRecords()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Log.d("Cursor",""+cursor.getCount());
        cursor.moveToFirst();
        for(int i=0;i< cursor.getCount();i++)
        {
            float  distance_id1 = cursor.getFloat(1);
            float   petrol_id1 = cursor.getFloat(2);
            float amount_id1 = cursor.getFloat(3);
            float   mileage_id1 = cursor.getFloat(4);
           // float asd = cursor.getFloat(5);
            Records c = new Records(distance_id1,petrol_id1,amount_id1,mileage_id1);
            Log.d("Test", distance_id1+" "+ petrol_id1+" "+ mileage_id1+" "+amount_id1);
            distance_id[i] = distance_id1;
            petrol_id[i] = petrol_id1;
            mileage_id[i] = mileage_id1;
            amount_id[i] = amount_id1;
            Log.d("TestA", String.valueOf(distance_id[i]));

            cursor.moveToNext();

        }
        Log.d("cursor value", "getRecords: "+cursor.getCount());
    }

    public void updaterecords(Records r,int i) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        float l=0,j=0,k=0;
        values.put(DISTANCE_ID,l);
        values.put(PETROL_ID,j);
        values.put(MILAGE_ID,k);
        database.update(TABLE_NAME,values,ID+" = "+i,null);
        database.close();
    }
    public void deleteitem(int i)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME,ID+" = "+i,null);
        Log.d("Test1234", "delete item :"+i);
        database.close();
    }
    public int cursorValue() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int a = cursor.getCount();
        return a;
    }
    public float mileageSum() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            float distance_id1 = cursor.getFloat(1);
            float petrol_id1 = cursor.getFloat(2);
            float amount_id1 = cursor.getFloat(3);
            float mileage_id1 = cursor.getFloat(4);
            Records c = new Records(distance_id1, petrol_id1, amount_id1, mileage_id1);
            distance_id[i] = distance_id1;
            petrol_id[i] = petrol_id1;
            mileage_id[i] = mileage_id1;
            amount_id[i] = amount_id1;
            cursor.moveToNext();

        }
        float sum=0;
        for(int j=0;j<cursor.getCount();j++)
        {
            sum+=mileage_id[j];
        }
        return sum;
    }

}