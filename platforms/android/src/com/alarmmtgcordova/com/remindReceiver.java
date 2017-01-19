package com.alarmmtgcordova.com;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;
import java.lang.Object;
/**
 * Created by mayank on 7/1/17.
 */
public class remindReceiver extends BroadcastReceiver {
    
    Uri ring_pointer;
    Ringtone ring_tone;
    MainActivity main_obj;

    @Override
    public void onReceive(Context context, Intent intent) {
        String remindText = intent.getStringExtra("text");
        int receiverID = intent.getIntExtra("AlrmId", 0);
        String url=intent.getStringExtra("url");
        //Toast.makeText(context, remindText, Toast.LENGTH_SHORT).show();

        //Notification Display
        main_obj=new MainActivity();
        main_obj.setUrl(url);
        Intent main_intent= new Intent(context, MainActivity.class);
        PendingIntent main_pending=PendingIntent.getActivity(context, 0, main_intent, 0);

        //we can get the url Now over here.....
        Notification noti = new Notification.Builder(context)
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setContentTitle("Reminder")
                .setContentIntent(main_pending)
                .setAutoCancel(true)
                .setContentText(remindText).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noti);

        //Ringtone Playing
        ring_pointer = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ring_tone = RingtoneManager.getRingtone(context, ring_pointer);
        ring_tone.play();


        Toast.makeText(context, remindText, Toast.LENGTH_LONG).show();

    }


}