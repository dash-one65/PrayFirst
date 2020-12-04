package com.example.prayfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetAlarmActivity extends AppCompatActivity {

    private static final String TAG = "AddAlarmActivity1";

    // time varible
    private TextView mDisplayTimesA;
    int tHourA,tMinuteA;
    // date variable
    private  TextView mDisplayDayA;

//    TextView DayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        mDisplayTimesA = (TextView)findViewById(R.id.editTextTime);
        mDisplayDayA = (TextView)findViewById(R.id.editTextDay);

        mDisplayDayA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DisplayCheckboxFragment().show(getSupportFragmentManager(),"DisplayCheckboxFragment");

            }
        });

        mDisplayTimesA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time in dialog
                TimePickerDialog timePickerDialogA = new TimePickerDialog(
                        SetAlarmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //initiallize hour and minute
                                tHourA = hourOfDay;
                                tMinuteA = minute;
                                //store in string
                                String time = tHourA + ":" + tMinuteA;
                                //initialize 24 hr time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //initialize 12hr time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    mDisplayTimesA.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //set transparent background
                timePickerDialogA.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //displayed previous selected time
                timePickerDialogA.updateTime(tHourA,tMinuteA);
                //show dialog
                timePickerDialogA.show();
            }
        });

    }


    public void backtoAlarmActivity(View view) {
        Intent backtoCalendar = new Intent(SetAlarmActivity.this, PrayerFragment.class);
        startActivity(backtoCalendar);
    }





}