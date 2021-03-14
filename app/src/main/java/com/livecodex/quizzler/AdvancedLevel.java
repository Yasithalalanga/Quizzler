package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.livecodex.quizzler.utils.Services;

public class AdvancedLevel extends AppCompatActivity {

    private ImageView imageViewOne;
    private ImageView imageViewTwo;
    private ImageView imageViewThree;

    private EditText textFieldOne;
    private EditText textFieldTwo;
    private EditText textFieldThree;
    private Dialog resultDialog;
    private TextView scoreCount;
    private TextView timerViewAdvanced;
    private Button submitBtn;

    private CountDownTimer downTimer;
    private boolean timerMode;
    private String[] currentCarMakes;
    private int totalAttempts = 3;
    private int currentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        Intent advancedIntent = getIntent();
        timerMode = advancedIntent.getBooleanExtra("SwitchStatus", false);

        imageViewOne = findViewById(R.id.imageViewOne);
        imageViewTwo = findViewById(R.id.imageViewTwo);
        imageViewThree = findViewById(R.id.imageViewThree);

        textFieldOne = findViewById(R.id.imageOneField);
        textFieldTwo = findViewById(R.id.imageTwoField);
        textFieldThree = findViewById(R.id.imageThreeField);
        scoreCount = findViewById(R.id.scoreCount);
        timerViewAdvanced = findViewById(R.id.timerViewAdvanced);
        submitBtn = findViewById(R.id.submitBtn);

        resultDialog = new Dialog(this);
        resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setRandomImages();

        if(timerMode){
            countdownTimer();
        }
    }

    public void setRandomImages(){
        currentCarMakes = new String[3];

        //Displaying three different images
        currentCarMakes[0] = Services.randomizedImage(imageViewOne, getApplicationContext());

        do{
            currentCarMakes[1] = Services.randomizedImage(imageViewTwo, getApplicationContext());
        }while (currentCarMakes[1].equalsIgnoreCase(currentCarMakes[0]));

        do{
            currentCarMakes[2] = Services.randomizedImage(imageViewThree, getApplicationContext());
        }while (currentCarMakes[2].equalsIgnoreCase(currentCarMakes[1]) || currentCarMakes[2].equalsIgnoreCase(currentCarMakes[0]));

    }

    public void checkOnSubmit(View view) {

        Button submitButton = (Button) view;
        String buttonContent = submitButton.getText().toString();

        if(buttonContent.equals("Next")){

            textFieldOne.setText("");
            textFieldTwo.setText("");
            textFieldThree.setText("");
            textFieldOne.setTextColor(Color.BLACK);
            textFieldTwo.setTextColor(Color.BLACK);
            textFieldThree.setTextColor(Color.BLACK);

            textFieldOne.setEnabled(true);
            textFieldTwo.setEnabled(true);
            textFieldThree.setEnabled(true);

            totalAttempts = 3;

            submitButton.setText(R.string.submit);
            setRandomImages();

            if(timerMode){
                if(downTimer != null){
                    downTimer.cancel();
                    timerViewAdvanced.setText("");
                }
                countdownTimer();
            }


        }else {

            if (totalAttempts > 0) {
                totalAttempts--;

                String imageOneGuess = textFieldOne.getText().toString().toLowerCase().trim();
                String imageTwoGuess = textFieldTwo.getText().toString().toLowerCase().trim();
                String imageThreeGuess = textFieldThree.getText().toString().toLowerCase().trim();

                if (imageOneGuess.equalsIgnoreCase(currentCarMakes[0]) & textFieldOne.isEnabled()) {
                    currentScore++;
                    textFieldOne.setEnabled(false);
                    textFieldOne.setTextColor(Color.GREEN);
                } else if (textFieldOne.isEnabled()) {
                    textFieldOne.setTextColor(Color.RED);
                }

                if (imageTwoGuess.equalsIgnoreCase(currentCarMakes[1]) & textFieldTwo.isEnabled()) {
                    currentScore++;
                    textFieldTwo.setEnabled(false);
                    textFieldTwo.setTextColor(Color.GREEN);
                } else if (textFieldTwo.isEnabled()) {
                    textFieldTwo.setTextColor(Color.RED);
                }

                if (imageThreeGuess.equalsIgnoreCase(currentCarMakes[2]) & textFieldThree.isEnabled()) {
                    currentScore++;
                    textFieldThree.setEnabled(false);
                    textFieldThree.setTextColor(Color.GREEN);
                } else if (textFieldThree.isEnabled()) {
                    textFieldThree.setTextColor(Color.RED);
                }

                if(timerMode){
                    if(downTimer != null){
                        downTimer.cancel();
                        timerViewAdvanced.setText("");
                    }
                    countdownTimer();
                }

                Toast.makeText(getApplicationContext(), imageOneGuess + currentCarMakes[0] + " " + imageTwoGuess + " " + imageThreeGuess + currentScore, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Rounds Exceeded", Toast.LENGTH_SHORT).show();

            }

            if (!textFieldOne.isEnabled() & !textFieldTwo.isEnabled() & !textFieldThree.isEnabled()) {
                resultDialog.setContentView(R.layout.correctpopup);
                resultDialog.show();
                submitButton.setText(R.string.next_item);
            } else if (totalAttempts == 0) {
                resultDialog.setContentView(R.layout.wrongpopup);
                resultDialog.show();
                correctAnswers();
                submitButton.setText(R.string.next_item);

                if(timerMode && downTimer != null){
                    downTimer.cancel();
                    timerViewAdvanced.setText("");
                }
            }

            scoreCount.setText(String.valueOf(currentScore));

        }

    }

    private void correctAnswers() {

        if(textFieldOne.isEnabled()){
            textFieldOne.setText(currentCarMakes[0]);
            textFieldOne.setTextColor(Color.RED);
        }

        if(textFieldTwo.isEnabled()){
            textFieldTwo.setText(currentCarMakes[1]);
            textFieldTwo.setTextColor(Color.RED);
        }

        if(textFieldThree.isEnabled()){
            textFieldThree.setText(currentCarMakes[2]);
            textFieldThree.setTextColor(Color.RED);
        }

    }

    public void countdownTimer(){
        downTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long milliSeconds) {
                long seconds = milliSeconds /1000;
                timerViewAdvanced.setText(String.valueOf(seconds));
            }

            @Override
            public void onFinish() {
                if(timerMode && downTimer != null){
                    downTimer.cancel();
                    timerViewAdvanced.setText("");
                }
                submitBtn.performClick();
            }
        }.start();
    }
}