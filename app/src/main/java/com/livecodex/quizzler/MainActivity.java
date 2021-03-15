package com.livecodex.quizzler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch advancedModeSwitch;
    private boolean advancedSwitched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advancedModeSwitch = findViewById(R.id.advanceMode);

        advancedModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton switchButton, boolean isChecked) {
                if (isChecked) {
                    advancedSwitched = true;
                } else {
                    advancedSwitched = false;
                }
            }
        });

        if(savedInstanceState != null){
            boolean isChecked = savedInstanceState.getBoolean("switch_status");

            if(isChecked){
                advancedModeSwitch.setChecked(true);
            }
        }

    }

    public void identifyMake(View view) {
        Intent makeIntent = new Intent(getApplicationContext(), IdentifyMakeActivity.class);
        makeIntent.putExtra("SwitchStatus", advancedSwitched);
        startActivity(makeIntent);
    }

    public void hintCars(View view) {
        Intent hintIntent = new Intent(getApplicationContext(), HintActivity.class);
        hintIntent.putExtra("SwitchStatus", advancedSwitched);
        startActivity(hintIntent);
    }

    public void identifyImage(View view) {
        Intent identifyImage = new Intent(getApplicationContext(), IdentifyImageActivity.class);
        identifyImage.putExtra("SwitchStatus", advancedSwitched);
        startActivity(identifyImage);
    }

    public void advancedLevel(View view) {
        Intent advancedLevel = new Intent(getApplicationContext(), AdvancedLevelActivity.class);
        advancedLevel.putExtra("SwitchStatus", advancedSwitched);
        startActivity(advancedLevel);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(advancedModeSwitch.isChecked()){
            outState.putBoolean("switch_status", true);
        }

    }
}