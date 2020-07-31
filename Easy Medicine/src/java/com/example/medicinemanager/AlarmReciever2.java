package com.example.medicinemanager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;
import  com.example.medicinemanager.Alarm2;



public class AlarmReciever2 extends BroadcastReceiver {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        // String data = intent.getStringExtra("msg");
        //   Toast.makeText(context, data, Toast.LENGTH_LONG).show();
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        //  Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        // ringtone.play();

        Alarm2.tts();

    }
}
