package com.example.mommayells;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSchedule extends AppCompatActivity {
    private AppCompatButton mImageButton, mButtonCancel;
    private EditText mAddNameSchedule, mAddDateSchedule, mAddTimeSchedule, mAddKeteranganSchedule;
    final Calendar calendar = Calendar.getInstance();
    private ImageView mButtonDate, mButtonTime;
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    public AddSchedule() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        mAddNameSchedule = (EditText) findViewById(R.id.addNameEvent);
        mAddDateSchedule = (EditText) findViewById(R.id.addDateEvent);
        mAddKeteranganSchedule = (EditText) findViewById(R.id.addKeteranganEvent);
        mAddTimeSchedule = (EditText) findViewById(R.id.addTimeEvent);
        mImageButton = (AppCompatButton) findViewById(R.id.ButtonWhats);
        final Button mButtonAdd = (Button) findViewById(R.id.buttonSubmit);
        mButtonDate = (ImageView) findViewById(R.id.buttonDate);
        mButtonTime = (ImageView) findViewById(R.id.buttonTime);


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFragmentDisplayed){
                    displayFragment();
                }else{
                    closeFragment();
                }
            }
        });

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mImageButton.setText(R.string.close);
            }
        }

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabelDate();
            }
        };
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddSchedule.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSchedule.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mAddTimeSchedule.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();
            }
        });


        mButtonAdd.setOnClickListener(view ->{
            Intent replyIntent = new Intent(this,MainActivity.class);
            String scheduleName = mAddNameSchedule.getText().toString();
            String scheduleDate = mAddDateSchedule.getText().toString();
            String scheduleTime = mAddTimeSchedule.getText().toString();
            String scheduleDetail = mAddKeteranganSchedule.getText().toString();
            if(TextUtils.isEmpty(mAddNameSchedule.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            }else{
                Schedule schedule = new Schedule(scheduleName,scheduleDate,scheduleTime,scheduleDetail);
                ScheduleViewModel scheduleViewModel = new ViewModelProvider(AddSchedule.this).get(ScheduleViewModel.class);
                replyIntent.putExtra("scheduleName",scheduleName);
                replyIntent.putExtra("scheduleDate",scheduleDate);
                replyIntent.putExtra("scheduleTime",scheduleTime);
                replyIntent.putExtra("scheduleDetail",scheduleDetail);
                setResult(RESULT_OK,replyIntent);
                scheduleViewModel.insert(schedule);
            }
            finish();
        });
    }

    public void displayFragment() {
        NotifFragment notifFragment = NotifFragment.newInstance();
        // TODO: Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // TODO: Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container,
                notifFragment).addToBackStack(null).commit();
        mImageButton.setText(R.string.close);
        isFragmentDisplayed = true;

    }
    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        NotifFragment notifFragment = (NotifFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (notifFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(notifFragment).commit();
        }
        // Update the Button text.
        mImageButton.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void updateLabelDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mAddDateSchedule.setText(sdf.format(calendar.getTime()));
    }

//    private void updateLabelTime() {
//        String myFormat = "hh:mm:ss"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        mAddTimeSchedule.setText(sdf.format(calendar.getTime()));
//    }
}

