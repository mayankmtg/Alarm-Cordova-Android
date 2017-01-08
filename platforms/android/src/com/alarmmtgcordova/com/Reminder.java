package com.alarmmtgcordova.com;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Reminder  {
    private Context context;
    public Reminder(Context context){
        this.context=context;
    }

    @JavascriptInterface
    public void addReminder(String mHour, String mMinute){
        //display("1");
        Calendar c = Calendar.getInstance();
        //display("2");
        //set Reminder time and date into calendar object

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH, month);//Don't use exact numeric value of the month, use one minus.Ex: April=>as 3
        c.set(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mHour));
        c.set(Calendar.MINUTE, Integer.parseInt(mMinute));
        c.set(Calendar.SECOND, 0);
        //Unique Alarm ID creation,
        int alarm_id=0;

        //display("3");

        alarm_id=Integer.parseInt(month+""+day+""+mHour+""+mMinute);
        //Alarm task creation
        Intent in=new Intent(context,remindReceiver.class);
        in.putExtra("text", "You have a Reminder!");
        in.putExtra("AlrmId", alarm_id);

        //display("4");

        PendingIntent pi;

        pi = PendingIntent.getBroadcast( context, alarm_id, in,PendingIntent.FLAG_UPDATE_CURRENT );

        //display("5");

        AlarmManager alarm_manager;

        alarm_manager = (AlarmManager)(context.getSystemService( Context.ALARM_SERVICE ));
        display(""+c.getTime());
        alarm_manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pi);
        //display("6");

    }

    @JavascriptInterface
    public void display(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

}
