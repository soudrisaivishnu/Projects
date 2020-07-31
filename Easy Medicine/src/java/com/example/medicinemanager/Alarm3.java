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

public class Alarm3 extends AppCompatActivity {
    private static Context context;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent1;
    AlarmManager alarmManager;
    static EditText edalarm;
    static int l=0;
    private static TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm4);
        Alarm3.context = getApplicationContext();
        edalarm = findViewById(R.id.edalarm3);
        alarmTimePicker = findViewById(R.id.timePicker3);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void OnToggleClicked3(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(Alarm3.this, "ALARM1 ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            Intent intent = new Intent(this, AlarmReciever3.class);

            pendingIntent1 = PendingIntent.getBroadcast(this, 0, intent, 0);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }

            alarmManager.setRepeating( AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent1);
        } else {
            alarmManager.cancel(pendingIntent1);
            Toast.makeText(Alarm3.this, "ALARM1 OFF", Toast.LENGTH_SHORT).show();

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
                    if (speechStatus== TextToSpeech.ERROR)
                        Log.e("TTS", "Error in converting Text to Speech!");
                } else {
                    // Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}

