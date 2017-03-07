package com.example.dev.proj3gui;

/**
 * Created by Daniel on 3/8/2016.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * This Is The Receiver which once the alarm is set on
 * It Will Show a toast message
 * followed by Opening the MainActivity Wether you are using the app or not As A Reminder
 * To Check Your To Do List
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * Show Message Saying The alarm is on to Check Your To Do List
         *
         */
        Toast.makeText(context,
                "Time Is Up Check Your ToDo List",
                Toast.LENGTH_LONG).show();
        Intent i = new Intent();
        /**
         * create the intent Package and Activity to open
         */
        i.setClassName("com.example.dev.proj3gui","com.example.dev.proj3gui.MainActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        //startActivity(new Intent(context,Test2.class);

    }



}