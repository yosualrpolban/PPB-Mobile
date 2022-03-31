package com.example.mommayells;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSchedule extends AppCompatActivity {
    private AppCompatButton mImageButton, mButtonCancel;
    private EditText mAddNameSchedule, mAddDateSchedule, mAddKeteranganSchedule;
    final Calendar calendar = Calendar.getInstance();
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
        mImageButton = (AppCompatButton) findViewById(R.id.ButtonWhats);
        final Button mButtonAdd = (Button) findViewById(R.id.buttonSubmit);

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
                updateLabel();
            }
        };
        mAddDateSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddSchedule.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mButtonAdd.setOnClickListener(view ->{
            Intent replyIntent = new Intent(this,MainActivity.class);
            if(TextUtils.isEmpty(mAddNameSchedule.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            }else{
                String scheduleName = mAddNameSchedule.getText().toString();
                String scheduleDate = mAddDateSchedule.getText().toString();
                String scheduleDetail = mAddKeteranganSchedule.getText().toString();
                Schedule schedule = new Schedule(scheduleName,scheduleDate,scheduleDetail);
                replyIntent.putExtra("scheduleName",scheduleName);
                replyIntent.putExtra("scheduleDate",scheduleDate);
                replyIntent.putExtra("scheduleDetail",scheduleDetail);
                setResult(RESULT_OK,replyIntent);
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mAddDateSchedule.setText(sdf.format(calendar.getTime()));
    }
}

