package com.example.mommayells;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView mNameEvent, mDateEvent, mDetailEvent;
    String sNameEvent, sDateEvent, sDetailEvent;
    private static final String TAG = "DetailActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mNameEvent = (TextView) findViewById(R.id.nameEvent);
        mDateEvent = (TextView) findViewById(R.id.dateEvent);
        mDetailEvent = (TextView) findViewById(R.id.detailEvent);

        getItemIntent();
        setItemIntent();


    }

    private void getItemIntent() {
        if(getIntent().hasExtra("nameEvent") && getIntent().hasExtra("dateEvent")){
             sNameEvent = getIntent().getStringExtra("nameEvent");
             sDateEvent = getIntent().getStringExtra("dateEvent");
             sDetailEvent = getIntent().getStringExtra("detailEvent");

        }
    }
    private void setItemIntent(){
        mNameEvent.setText(sNameEvent);
        mDateEvent.setText(sDateEvent);
        mDetailEvent.setText(sDetailEvent);
    }
}
