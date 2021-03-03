package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class IdentifyMakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView randomImageView;
    private String spinnerCarMakes;
//    private Button makeSubmitBtn;

    private Dialog resultDialog;

    private int makeTestRand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_make);

        resultDialog = new Dialog(this);

//        makeSubmitBtn = (Button) findViewById(R.id.makeSubmit);
        randomImageView = (ImageView) findViewById(R.id.imageView) ;
        Spinner mSpinner = (Spinner) findViewById(R.id.makeSpinner);

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
        int randomImageNo = rand.nextInt(10);
        String imageUri = "img_"+ randomImageNo;
        int resource_id = getResources().getIdentifier(imageUri,"drawable", "com.livecodex.quizzler");

        randomImageView.setImageResource(resource_id);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerCarMakes = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), spinnerCarMakes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void checkMakeOnSubmit(View view) {

        Button makeButton = (Button) view;
        String buttonContent = makeButton.getText().toString();

        if(buttonContent.equals("Next")){
            makeButton.setText(R.string.make_submit);
            randomImage();

        }else{
            if(spinnerCarMakes.equals("BMW")){
                // Showing the correct dialog
                resultDialog.setContentView(R.layout.correctpopup);

            }else{
                resultDialog.setContentView(R.layout.wrongpopup);

            }
            resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            resultDialog.show();
            makeButton.setText(R.string.next_item);
        }



    }
}