package com.example.medicinemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Locale;

public class Alarm extends AppCompatActivity {
    private static Context context;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
     static EditText edalarm;
     static int l=0;
    private static TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Alarm.context = getApplicationContext();
        edalarm = findViewById(R.id.edalarm);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void OnToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(Alarm.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            String data = edalarm.getText().toString();
            Intent intent = new Intent(this, AlarmReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }

            alarmManager.setRepeating( AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(Alarm.this, "ALARM OFF", Toast.LENGTH_SHORT).show();

        }
    }

    public static void sms()
    {
        String phoneNo = "9900056898";
        String sms = "Take your medicine "+edalarm.getText().toString();;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void tts()
    {
        final String data = "Take the medicine "+edalarm.getText().toString();
        Log.i("TTS", "button clicked: " + data);
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");

                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            final String keyword = utteranceId;

                            Toast.makeText(context, "Started" + keyword, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onDone(String utteranceId) {

                            Toast.makeText(context, "Done ", Toast.LENGTH_SHORT).show();
                            if ( l< 3) {
                                int speechStatus2 = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null, "Hi");
                              //  Toast.makeText(context,"loop",Toast.LENGTH_LONG).show();

                                if (speechStatus2 == TextToSpeech.ERROR)
                                    Log.e("TTS", "Error in converting Text to Speech!");
                                l++;
                            }


                        }

                        @Override
                        public void onError(String utteranceId) {

                            //  Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();

                        }
                    });
                    l=0;
                    int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null,"Hi");
                 //   Toast.makeText(context, "speechStatus", Toast.LENGTH_SHORT).show();
                //    int speechStatus1 = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null,"Hi");
                  //  int speechStatus2 = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null,"Hi");
                    if (speechStatus== TextToSpeech.ERROR)
                        Log.e("TTS", "Error in converting Text to Speech!");
                } else {
                   // Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


        }

