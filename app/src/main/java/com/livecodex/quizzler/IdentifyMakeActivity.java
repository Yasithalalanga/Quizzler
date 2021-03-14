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
import android.widget.TextView;
import android.widget.Toast;

import com.livecodex.quizzler.utils.Selections;
import com.livecodex.quizzler.utils.Services;


public class IdentifyMakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView randomImageView;
    private Spinner mSpinner;
    private Dialog resultDialog;

    private String spinnerCarMakes;
    private String makeTestRand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_make);

        resultDialog = new Dialog(this);

        randomImageView = findViewById(R.id.imageView) ;
        mSpinner = findViewById(R.id.makeSpinner);

        if(mSpinner != null){
            mSpinner.setOnItemSelectedListener(this);
        }

        // Spinner items loaded from the string array
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Selections.getMakes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(mSpinner != null){
            mSpinner.setAdapter(adapter);
        }

        makeTestRand = Services.randomizedImage(randomImageView,getApplicationContext());
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
            makeTestRand = Services.randomizedImage(randomImageView,getApplicationContext());

            mSpinner.setEnabled(true);

        }else{

            mSpinner.setEnabled(false);

            if(spinnerCarMakes.equals(makeTestRand)){
                // Showing the correct dialog
                resultDialog.setContentView(R.layout.correctpopup);

            }else{
                resultDialog.setContentView(R.layout.wrongpopup);
                TextView dialogCorrectText = resultDialog.findViewById(R.id.correct_answer_view);
                dialogCorrectText.setText(makeTestRand);

            }
            resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            resultDialog.show();
            makeButton.setText(R.string.next_item);
        }



    }
}