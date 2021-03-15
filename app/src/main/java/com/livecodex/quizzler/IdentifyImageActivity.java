package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.livecodex.quizzler.utils.Services;

import java.util.Random;

public class IdentifyImageActivity extends AppCompatActivity {

    private ImageButton imageButtonOne;
    private ImageButton imageButtonTwo;
    private ImageButton imageButtonThree;
    private TextView randomCarName;
    private TextView timerViewIdentify;
    private Dialog resultDialog;

    private String[] currentCarMakes;
    private String correctCarMake;
    private boolean timerMode;
    private CountDownTimer downTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_image);

        Intent identifyIntent = getIntent();
        timerMode = identifyIntent.getBooleanExtra("SwitchStatus", false);

        imageButtonOne = findViewById(R.id.imageBtnOne);
        imageButtonTwo = findViewById(R.id.imageBtnTwo);
        imageButtonThree = findViewById(R.id.imageBtnThree);
        randomCarName = findViewById(R.id.randomCarName);
        timerViewIdentify = findViewById(R.id.timerViewIdentify);

        resultDialog = new Dialog(IdentifyImageActivity.this);
        resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setRandomImages();

        if (timerMode) {
            countdownTimer();
        }

    }

    public void setRandomImages() {
        currentCarMakes = new String[3];

        //Displaying three different images
        currentCarMakes[0] = Services.randomizedImage(imageButtonOne, getApplicationContext());

        do {
            currentCarMakes[1] = Services.randomizedImage(imageButtonTwo, getApplicationContext());
        } while (currentCarMakes[1].equalsIgnoreCase(currentCarMakes[0]));

        do {
            currentCarMakes[2] = Services.randomizedImage(imageButtonThree, getApplicationContext());
        } while (currentCarMakes[2].equalsIgnoreCase(currentCarMakes[1]) || currentCarMakes[2].equalsIgnoreCase(currentCarMakes[0]));

        correctCarMake = randomizedCarMake(currentCarMakes);
    }

    public String randomizedCarMake(String[] currentMakes) {
        Random rand = new Random();
        int randomNum = rand.nextInt(currentMakes.length);

        String randomCarMake = currentMakes[randomNum];
        randomCarName.setText(randomCarMake);
        return randomCarMake;
    }

    public void imageOneSelected(View view) {
        if (currentCarMakes[0].equals(correctCarMake)) {
            resultDialog.setContentView(R.layout.correctpopup);
            view.setBackgroundResource(R.drawable.round_button_green);
            cancelTimer();
        } else {
            resultDialog.setContentView(R.layout.wrongpopup);
            showCorrectImage();
        }
        resultDialog.show();

        imageButtonOne.setEnabled(false);
        imageButtonTwo.setEnabled(false);
        imageButtonThree.setEnabled(false);
    }

    public void imageTwoSelected(View view) {
        if (currentCarMakes[1].equals(correctCarMake)) {
            resultDialog.setContentView(R.layout.correctpopup);
            view.setBackgroundResource(R.drawable.round_button_green);
            cancelTimer();
        } else {
            resultDialog.setContentView(R.layout.wrongpopup);
            showCorrectImage();
        }
        resultDialog.show();

        imageButtonOne.setEnabled(false);
        imageButtonTwo.setEnabled(false);
        imageButtonThree.setEnabled(false);
    }

    public void imageThreeSelected(View view) {
        if (currentCarMakes[2].equals(correctCarMake)) {
            resultDialog.setContentView(R.layout.correctpopup);
            view.setBackgroundResource(R.drawable.round_button_green);
            cancelTimer();
        } else {
            resultDialog.setContentView(R.layout.wrongpopup);
            showCorrectImage();
        }
        resultDialog.show();

        imageButtonOne.setEnabled(false);
        imageButtonTwo.setEnabled(false);
        imageButtonThree.setEnabled(false);
    }

    public void showCorrectImage() {
        TextView dialogCorrectText = resultDialog.findViewById(R.id.correct_answer_view);
        dialogCorrectText.setText(R.string.imageWrongDisplayMsg);

        cancelTimer();

        if (currentCarMakes[0].equals(correctCarMake)) {
            imageButtonOne.setBackgroundResource(R.drawable.round_button_orange);
        } else if (currentCarMakes[1].equals(correctCarMake)) {
            imageButtonTwo.setBackgroundResource(R.drawable.round_button_orange);
        } else {
            imageButtonThree.setBackgroundResource(R.drawable.round_button_orange);
        }
    }

    public void cancelTimer() {
        if (timerMode && downTimer != null) {
            downTimer.cancel();
            timerViewIdentify.setText("");
        }
    }


    public void nextImageSet(View view) {

        if (timerMode) {
            if (downTimer != null) {
                downTimer.cancel();
                timerViewIdentify.setText("");
            }
            countdownTimer();
        }
        setRandomImages();

        imageButtonOne.setEnabled(true);
        imageButtonTwo.setEnabled(true);
        imageButtonThree.setEnabled(true);

        imageButtonOne.setBackgroundResource(R.drawable.round_button);
        imageButtonTwo.setBackgroundResource(R.drawable.round_button);
        imageButtonThree.setBackgroundResource(R.drawable.round_button);
    }

    public void countdownTimer() {
        downTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long milliSeconds) {
                long seconds = milliSeconds / 1000;
                timerViewIdentify.setText(String.valueOf(seconds));
            }

            @Override
            public void onFinish() {

                if (timerMode && downTimer != null) {
                    downTimer.cancel();
                    timerViewIdentify.setText("");
                }

                imageButtonOne.setEnabled(false);
                imageButtonTwo.setEnabled(false);
                imageButtonThree.setEnabled(false);

                resultDialog.setContentView(R.layout.wrongpopup);
                resultDialog.show();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (timerMode) downTimer.cancel();
        resultDialog.cancel();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (timerMode) downTimer.cancel();
        resultDialog.cancel();
        return super.onSupportNavigateUp();
    }
}