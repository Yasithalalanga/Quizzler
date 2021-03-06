package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HintActivity extends AppCompatActivity {

    private ImageView mHintImageView;
    private TextView mHintTextView;
    private EditText mHintInput;
    private int mRandomImageNum;
    private String carHintText;

    private final Selections selection = new Selections();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mHintImageView = (ImageView) findViewById(R.id.hintImageView);
        mHintTextView  = (TextView) findViewById(R.id.hintText);
        mHintInput     = (EditText) findViewById(R.id.hintLetterField);

        mRandomImageNum = randomImage();

        carHintText =  new String(new char[selection.getCarMake(mRandomImageNum).length()]).replace("\0", " _ ");
        mHintTextView.setText(carHintText);
    }

    public int randomImage(){
        Random rand = new Random();
        int randomImageNo = rand.nextInt(10);
        String imageUri = "img_"+ randomImageNo;
        int resource_id = getResources().getIdentifier(imageUri,"drawable", "com.livecodex.quizzler");

        mHintImageView.setImageResource(resource_id);
        return randomImageNo;
    }


    public void identifyHint(View view) {

        String hintInput = mHintInput.getText().toString().trim();
        Toast.makeText(this, hintInput, Toast.LENGTH_LONG).show();

        String selectedCar = selection.getCarMake(mRandomImageNum).toLowerCase();
        StringBuilder modifiedCarMake = new StringBuilder(carHintText);

        if(selectedCar.contains(hintInput.toLowerCase())) {
            for (int position = 0; position < selectedCar.length(); position++) {
                if(selectedCar.charAt(position)  ==  hintInput.charAt(0)){
                    modifiedCarMake.setCharAt(position,hintInput.charAt(0));
                }
                carHintText = modifiedCarMake.toString();
                mHintTextView.setText(modifiedCarMake);
            }

        }else{
            Toast.makeText(this, "Letter is not available", Toast.LENGTH_LONG).show();
        }


    }
}