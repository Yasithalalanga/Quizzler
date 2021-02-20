package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void identifyMake(View view) {
        Intent makeIntent = new Intent(getApplicationContext(), IdentifyMakeActivity.class);
        startActivity(makeIntent);
    }

    public void hintCars(View view) {
        Intent hintIntent = new Intent(getApplicationContext(), HintActivity.class);
        startActivity(hintIntent);
    }

    public void identifyImage(View view) {
        Intent identifyImage = new Intent(getApplicationContext(), IdentifyImage.class);
        startActivity(identifyImage);
    }

    public void advancedLevel(View view) {
        Intent advancedLevel = new Intent(getApplicationContext(), AdvancedLevel.class);
        startActivity(advancedLevel);
    }
}