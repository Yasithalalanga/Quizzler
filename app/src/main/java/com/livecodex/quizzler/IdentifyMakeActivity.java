package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class IdentifyMakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView randomImageView;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_make);

        randomImageView = (ImageView) findViewById(R.id.imageView) ;
        mSpinner = (Spinner) findViewById(R.id.makeSpinner);

        if(mSpinner != null){
            mSpinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.car_make_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(mSpinner != null){
            mSpinner.setAdapter(adapter);
        }

        randomImage();
    }

    public void randomImage(){
        Random rand = new Random();
        String imageUri = "img_"+rand.nextInt(10);
        int resource_id = getResources().getIdentifier(imageUri,"drawable", "com.livecodex.quizzler");

        randomImageView.setImageResource(resource_id);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerCarMakes = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), spinnerCarMakes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}