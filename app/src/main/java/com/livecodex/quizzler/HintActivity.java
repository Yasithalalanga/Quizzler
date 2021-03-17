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

public class HintActivity extends AppCompatActivity {

    private ImageView mHintImageView;
    private TextView mHintTextView;
    private EditText mHintInput;
    private Dialog resultDialog;
    private TextView timerViewHint;
    private Button hintSubmitBtn;

    private String mRandomImageMake;
    private int roundCount;
    private String carHintText;
    private boolean timerMode;
    private CountDownTimer downTimer;
    private int totalAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        Intent hintIntent = getIntent();
        timerMode = hintIntent.getBooleanExtra("SwitchStatus", false);

        mHintImageView = findViewById(R.id.hintImageView);
        mHintTextView = findViewById(R.id.hintText);
        mHintInput = findViewById(R.id.hintLetterField);
        timerViewHint = findViewById(R.id.timerViewHint);
        hintSubmitBtn = findViewById(R.id.hint_submit);
        resultDialog = new Dialog(this);

        mRandomImageMake = Services.randomizedImage(mHintImageView, getApplicationContext());

        carHintText = new String(new char[mRandomImageMake.length()]).replace("\0", "-");
        mHintTextView.setText(carHintText);

        if (timerMode) {
            countdownTimer();
        }
    }


    public void identifyHint(View view) {

        Button hintButton = (Button) view;
        String buttonContent = hintButton.getText().toString();

        if (buttonContent.equals("Next")) {
            hintButton.setText(R.string.submit);
            mRandomImageMake = Services.randomizedImage(mHintImageView, getApplicationContext());

            mHintInput.setEnabled(true);
            mHintTextView.setText("");
            totalAttempts = 3;
            roundCount = 0;

            carHintText = new String(new char[mRandomImageMake.length()]).replace("\0", "-");
            mHintTextView.setText(carHintText);

            if (timerMode) {
                if (downTimer != null) {
                    downTimer.cancel();
                    timerViewHint.setText("");
                }
                countdownTimer();
            }


        } else {

            if (totalAttempts > 0) {

                String hintInput = mHintInput.getText().toString().trim();

                String selectedCar = mRandomImageMake.toLowerCase();
                StringBuilder modifiedCarMake = new StringBuilder(carHintText);

                if ((selectedCar.contains(hintInput.toLowerCase())) && (!hintInput.equals(""))) {
                    for (int position = 0; position < selectedCar.length(); position++) {
                        if (selectedCar.charAt(position) == hintInput.charAt(0)) {
                            modifiedCarMake.setCharAt(position, hintInput.charAt(0));
                        }
                    }
                    carHintText = modifiedCarMake.toString();
                    mHintTextView.setText(modifiedCarMake);
                    mHintInput.setText("");

                    if (carHintText.equals(selectedCar)) {
                        resultDialog.setContentView(R.layout.correctpopup);
                        resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        resultDialog.show();

                        if (timerMode && downTimer != null) {
                            downTimer.cancel();
                            timerViewHint.setText("");
                        }

                        hintButton.setText(R.string.next_item);
                        mHintInput.setEnabled(false);
                    }

                } else {
                    roundCount++;

                    if (roundCount > 2) {

                        resultDialog.setContentView(R.layout.wrongpopup);

                        TextView dialogCorrectText = (TextView) resultDialog.findViewById(R.id.correct_answer_view);
                        dialogCorrectText.setText(mRandomImageMake);

                        resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        resultDialog.show();

                        hintButton.setText(R.string.next_item);
                        mHintInput.setEnabled(false);
                        mHintInput.setText("");

                        if (timerMode && downTimer != null) {
                            downTimer.cancel();
                            timerViewHint.setText("");
                        }

                    } else {

                        totalAttempts--;
                        mHintInput.setText("");
                        Toast.makeText(this, "Letter is not available, Rounds Left: " + (3 - roundCount), Toast.LENGTH_SHORT).show();

                    }
                }

                if(!hintButton.getText().toString().equals("Next")){
                    if (timerMode) {
                        if (downTimer != null) {
                            downTimer.cancel();
                            timerViewHint.setText("");
                        }
                        countdownTimer();
                    }
                }


            } else {
                Toast.makeText(getApplicationContext(), "Limit Exceeded", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void countdownTimer() {
        downTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long milliSeconds) {
                long seconds = milliSeconds / 1000;
                timerViewHint.setText(String.valueOf(seconds));
            }

            @Override
            public void onFinish() {
                if (timerMode && downTimer != null) {
                    downTimer.cancel();
                    timerViewHint.setText("");
                }
                hintSubmitBtn.performClick();
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