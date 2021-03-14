package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.livecodex.quizzler.utils.Services;

import java.util.Random;

public class IdentifyImage extends AppCompatActivity {

    private ImageButton imageButtonOne;
    private ImageButton imageButtonTwo;
    private ImageButton imageButtonThree;
    private TextView randomCarName;
    private Dialog resultDialog;

    private String[] currentCarMakes;
    private String correctCarMake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_image);

        imageButtonOne = findViewById(R.id.imageBtnOne);
        imageButtonTwo = findViewById(R.id.imageBtnTwo);
        imageButtonThree = findViewById(R.id.imageBtnThree);
        randomCarName = findViewById(R.id.randomCarName);

        resultDialog = new Dialog(this);
        resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setRandomImages();

    }

    public void setRandomImages(){
        currentCarMakes = new String[3];

        //Displaying three different images
        currentCarMakes[0] = Services.randomizedImage(imageButtonOne, getApplicationContext());

        do{
            currentCarMakes[1] = Services.randomizedImage(imageButtonTwo, getApplicationContext());
        }while (currentCarMakes[1].equalsIgnoreCase(currentCarMakes[0]));

        do{
            currentCarMakes[2] = Services.randomizedImage(imageButtonThree, getApplicationContext());
        }while (currentCarMakes[2].equalsIgnoreCase(currentCarMakes[1]) || currentCarMakes[2].equalsIgnoreCase(currentCarMakes[0]));

        correctCarMake = randomizedCarMake(currentCarMakes);
        Toast.makeText(this,correctCarMake,Toast.LENGTH_SHORT).show();
    }

    public String randomizedCarMake(String[] currentMakes){
        Random rand = new Random();
        int randomNum = rand.nextInt(currentMakes.length);

        String randomCarMake = currentMakes[randomNum];
        randomCarName.setText(randomCarMake);
        return  randomCarMake;
    }

    public void imageOneSelected(View view) {
        if(currentCarMakes[0].equals(correctCarMake)){
            resultDialog.setContentView(R.layout.correctpopup);
        }else {
            resultDialog.setContentView(R.layout.wrongpopup);
        }
        resultDialog.show();

        imageButtonOne.setEnabled(false);
        imageButtonTwo.setEnabled(false);
        imageButtonThree.setEnabled(false);
    }

    public void imageTwoSelected(View view) {
        if(currentCarMakes[1].equals(correctCarMake)){
            resultDialog.setContentView(R.layout.correctpopup);
        }else {
            resultDialog.setContentView(R.layout.wrongpopup);
        }
        resultDialog.show();

        imageButtonOne.setEnabled(false);
        imageButtonTwo.setEnabled(false);
        imageButtonThree.setEnabled(false);
    }

    public void imageThreeSelected(View view) {
        if(currentCarMakes[2].equals(correctCarMake)){
            resultDialog.setContentView(R.layout.correctpopup);
        }else {
            resultDialog.setContentView(R.layout.wrongpopup);
        }
        resultDialog.show();

        imageButtonOne.setEnabled(false);
        imageButtonTwo.setEnabled(false);
        imageButtonThree.setEnabled(false);
    }


    public void nextImageSet(View view) {

        setRandomImages();

        imageButtonOne.setEnabled(true);
        imageButtonTwo.setEnabled(true);
        imageButtonThree.setEnabled(true);
    }
}