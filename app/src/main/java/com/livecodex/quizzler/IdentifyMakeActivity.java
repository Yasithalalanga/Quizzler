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
    private Spinner mSpinner;
    private Dialog resultDialog;

    private String spinnerCarMakes;
    private int makeTestRand;
    private final Selections selections = new Selections();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_make);

        resultDialog = new Dialog(this);

        randomImageView = (ImageView) findViewById(R.id.imageView) ;
        mSpinner = (Spinner) findViewById(R.id.makeSpinner);

        if(mSpinner != null){
            mSpinner.setOnItemSelectedListener(this);
        }

        // Spinner items loaded from the string array
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, selections.getMakes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(mSpinner != null){
            mSpinner.setAdapter(adapter);
        }

        makeTestRand = randomImage();
    }

    public int randomImage(){
        Random rand = new Random();
        int randomImageNo = rand.nextInt(10);
        String imageUri = "img_"+ randomImageNo;
        int resource_id = getResources().getIdentifier(imageUri,"drawable", "com.livecodex.quizzler");

        randomImageView.setImageResource(resource_id);
        return randomImageNo;
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
            makeTestRand = randomImage();

            mSpinner.setEnabled(true);

        }else{

            mSpinner.setEnabled(false);

            if(spinnerCarMakes.equals(selections.getCarMake(makeTestRand))){
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