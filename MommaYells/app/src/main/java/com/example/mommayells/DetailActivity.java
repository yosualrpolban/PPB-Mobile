package com.example.mommayells;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView mNameEvent, mDateEvent,mTimeEvent, mDetailEvent;
    String sNameEvent, sDateEvent,sTimeEvent, sDetailEvent;
    AppCompatButton mButtonDelete;
    ScheduleViewModel scheduleViewModel;
    private static final String TAG = "DetailActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mNameEvent = (TextView) findViewById(R.id.nameEvent);
        mDateEvent = (TextView) findViewById(R.id.dateEvent);
        mTimeEvent = (TextView) findViewById(R.id.timeEvent);
        mDetailEvent = (TextView) findViewById(R.id.detailEvent);
        mButtonDelete = (AppCompatButton) findViewById(R.id.deleteButton);
        getItemIntent();
        setItemIntent();

        scheduleViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ScheduleViewModel.class);
        mButtonDelete.setOnClickListener(View -> {
            scheduleViewModel.deleteOne(sNameEvent);
            finish();
        });


    }


    private void getItemIntent() {
        if(getIntent().hasExtra("nameEvent") && getIntent().hasExtra("dateEvent")){
             sNameEvent = getIntent().getStringExtra("nameEvent");
             sDateEvent = getIntent().getStringExtra("dateEvent");
             sTimeEvent = getIntent().getStringExtra("timeEvent");
             sDetailEvent = getIntent().getStringExtra("detailEvent");

        }
    }
    private void setItemIntent(){
        mNameEvent.setText(sNameEvent);
        mDateEvent.setText(sDateEvent);
        mTimeEvent.setText(sTimeEvent);
        mDetailEvent.setText(sDetailEvent);
    }
}
