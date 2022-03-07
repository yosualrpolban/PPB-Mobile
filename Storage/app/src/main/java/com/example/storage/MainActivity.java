package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button internalStorage,eksternalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internalStorage = findViewById(R.id.internalStorage);
        eksternalStorage = findViewById(R.id.externalStorage);
        internalStorage.setOnClickListener(this);
        eksternalStorage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.internalStorage:
                Intent internal = new Intent(MainActivity.this, InternalActivity.class);
                startActivity(internal);
                break;
            case R.id.externalStorage:
                Intent eksternal = new Intent(MainActivity.this, ExternalActivity.class);
                startActivity(eksternal);
                break;
        }

    }

}