package com.example.prayfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.calendar.SQLiteCalendarHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCalendarActivity extends AppCompatActivity {

    private static final String TAG = "AddCalendarActivity";
//    start date variable
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
//    end date variable
    private TextView mDisplayDateE;
    private DatePickerDialog.OnDateSetListener mDateSetListenerE;
//    start time varible
    private TextView mDisplayTimes;
    int tHour,tMinute;
    //    start time varible
    private TextView mDisplayTimesE;
    int tHourE,tMinuteE;

    //checkbox
    CheckBox chkbox;
    ImageView imageb;
    //
    //Variables for calendarActivity
    private SQLiteCalendarHandler dbHandler;
    private EditText editText;
    private CalendarView calendarView;
    private  String selectedDate;
    private SQLiteDatabase sqLiteDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar);

        mDisplayDate = (TextView) findViewById(R.id.edit_date_viewS);
        mDisplayDateE = (TextView) findViewById(R.id.edit_date_viewE);

        mDisplayTimes = (TextView) findViewById(R.id.edit_time_viewS);
        mDisplayTimesE = (TextView) findViewById(R.id.edit_time_viewE);

        chkbox = (CheckBox) findViewById(R.id.checkBox_allday);
        imageb = (ImageView) findViewById(R.id.imageView12);



//        TODO: Start date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddCalendarActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.setTitle("Start Date");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.updateDate(year,month,day);
            }

        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
            Log.d(TAG, "onDateSet: date: " + month + "/" + day + "/" + year);
            String date = month + "/" + day + "/" + year;
            mDisplayDate.setText(date);
            }
        };

//        TODO: End date
        mDisplayDateE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calE = Calendar.getInstance();
                int yearE = calE.get(Calendar.YEAR);
                int monthE = calE.get(Calendar.MONTH);
                int dayE = calE.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogE = new DatePickerDialog(
                        AddCalendarActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerE,
                        yearE,monthE,dayE);
                dialogE.setTitle("End Date");
                dialogE.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogE.show();

            }

        });
        mDateSetListenerE = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearE, int monthE, int dayE) {
                monthE = monthE + 1;
                Log.d(TAG, "onDateSet: date: " + monthE + "/" + dayE + "/" + yearE);
                String dateE = monthE + "/" + dayE + "/" + yearE;
                mDisplayDateE.setText(dateE);

            }

        };


// TODO: Start Time
        mDisplayTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time in dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddCalendarActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //initiallize hour and minute
                                tHour = hourOfDay;
                                tMinute = minute;
                                //store in string
                                String time = tHour + ":" + tMinute;
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
                                    mDisplayTimes.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //displayed previous selected time
                timePickerDialog.updateTime(tHour,tMinute);
                //show dialog
                timePickerDialog.show();
            }
        });

// TODO: End Time
        mDisplayTimesE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time in dialog
                TimePickerDialog timePickerDialogE = new TimePickerDialog(
                        AddCalendarActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDayE, int minuteE) {
                                //initiallize hour and minute
                                tHourE = hourOfDayE;
                                tMinuteE = minuteE;
                                //store in string
                                String timeE = tHourE + ":" + tMinuteE;
                                //initialize 24 hr time format
                                SimpleDateFormat f24HoursE = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date dateE = f24HoursE.parse(timeE);
                                    //initialize 12hr time format
                                    SimpleDateFormat f12HoursE = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    mDisplayTimesE.setText(f12HoursE.format(dateE));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //set transparent background
                timePickerDialogE.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //displayed previous selected time
                timePickerDialogE.updateTime(tHourE,tMinuteE);
                //show dialog
                timePickerDialogE.show();
            }
        });
    }

    //If all day checkbox id selected
    public void alldayselected(View view) {
        if (chkbox.isChecked()){
            mDisplayTimes.setEnabled(false);
            mDisplayTimesE.setEnabled(false);
        }else {
            mDisplayTimes.setEnabled(true);
            mDisplayTimesE.setEnabled(true);
        }
    }

    public void backtoCalendarFragment(View view) {
        Intent backtoCalendar = new Intent(AddCalendarActivity.this, CalendarFragment.class);
        startActivity(backtoCalendar);
    }


}