/**
 * Alerts Class To Control Alert Functions
 */
package com.example.dev.proj3gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;
public class Alerts extends AppCompatActivity {

    Chronometer chronometer;
    Button btnSetNoCheck;
    final static int RQS_1 = 1;
    public EditText numMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        btnSetNoCheck = (Button) findViewById(R.id.setnocheck);
        btnSetNoCheck.setOnClickListener(onClickListener);


    }



    @SuppressLint("NewApi")
    OnClickListener onClickListener = new OnClickListener(){



        @Override
        public void onClick(View v) {
            /**
             * Get The Number of Minutes to Set the alarm for
             * Get the Input as a String then Convert it into An Int To Pass it to
             * Function
             */
            numMin=(EditText)findViewById(R.id.minutesInput);
            String s1;
            s1=numMin.getText().toString();
            int n1=Integer.parseInt(s1);
            /**
             * Start the Chrometer Starting from 0;
             */
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();

            //10 seconds later
            Calendar cal = Calendar.getInstance();
            //cal.add(Calendar.SECOND, n1);
            cal.add(Calendar.MINUTE,n1);


            /**
             * Time is Up Now The Alarm will trigger and the intent will call
             * the activity to open using the AlarmReceiver
             */
            Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntent =
                    PendingIntent.getBroadcast(getBaseContext(),
                            RQS_1, intent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager =
                    (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            /**
             * If the Button Was Checked Set the Alarm To On And Timer COunts++
             */
            if (v == btnSetNoCheck) {
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(), pendingIntent);
                Toast.makeText(getBaseContext(),
                        "Alarm Set To On!!",
                        Toast.LENGTH_LONG).show();
            }
            else{
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
                {
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), pendingIntent);
                    Toast.makeText(getBaseContext(),
                            "",
                            Toast.LENGTH_LONG).show();
                }else{
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), pendingIntent);
                    Toast.makeText(getBaseContext(),
                            "",
                            Toast.LENGTH_LONG).show();
                }
            }

        }
    };
}
