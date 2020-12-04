package com.example.prayfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calendar.SQLiteCalendarHandler;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PrayerActivity extends AppCompatActivity {

    //Variables for calendarActivity
    private SQLiteCalendarHandler dbHandler;
    private EditText editText;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        //declaration for calendarActivity
        editText = (EditText)findViewById(R.id.event_text);
        calendarView = (CalendarView)findViewById(R.id.calendarView);

//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
//                ReadDatabase(view);
//            }
//        });

//        try{
//            dbHandler = new SQLiteCalendarHandler(this, "CalendarDatabase", null, 1);
//            sqLiteDatabase = dbHandler.getReadableDatabase();
//            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(Date TEXT, Event TEXT)");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

        //initialization of layout
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabLayoutMediator tableLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("Prayer");
                        tab.setIcon(R.drawable.prayer);
                        break;
                    }
                    case 1:{
                        tab.setText("Alarm");
                        tab.setIcon(R.drawable.alarm);
                        break;
                    }
                    case 2:{
                        tab.setText("Calendar");
                        tab.setIcon(R.drawable.calendar);
                        break;
                    }case 3:{
                        tab.setText("About");
                        tab.setIcon(R.drawable.about);
                        break;
                    }
                }
            }
        }
        );
        tableLayoutMediator.attach();
    }

    //fab
    public void AddAlarm(View view) {
        Intent toAddAlarmActivity = new Intent(PrayerActivity.this, SetAlarmActivity.class);
        startActivity(toAddAlarmActivity);
    }

    public void AddCalendar(View view) {
//        Intent toAddCalendarActivity = new Intent(PrayerActivity.this, AddCalendarActivity.class);
//        startActivity(toAddCalendarActivity);
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date",selectedDate);
        contentValues.put("Event",editText.getText().toString());
        sqLiteDatabase.insert("EventCalendar",null,contentValues);
    }

    public void  ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date =" +selectedDate;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query,null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            editText.setText("");
        }
    }





}

