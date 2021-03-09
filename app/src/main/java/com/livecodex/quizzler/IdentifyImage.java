package com.livecodex.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdentifyImage extends AppCompatActivity {

    private ImageButton imageButtonOne;
    private ImageButton imageButtonTwo;
    private ImageButton imageButtonThree;
    private final Selections selections = new Selections();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_image);

        imageButtonOne = (ImageButton) findViewById(R.id.imageBtnOne);
        imageButtonTwo = (ImageButton) findViewById(R.id.imageBtnTwo);
        imageButtonThree = (ImageButton) findViewById(R.id.imageBtnThree);

        threeRandomImages(11);

    }

    public int randomImage(ImageButton view) {
        Random rand = new Random();
        int randomImageOneNo = rand.nextInt(10);

        String imageUri = "img_" + randomImageOneNo;
        int resource_id = getResources().getIdentifier(imageUri, "drawable", "com.livecodex.quizzler");

        view.setImageResource(resource_id);
        return randomImageOneNo;
    }

    public void threeRandomImages(int noImages) {

        Random rand = new Random();
        int randomImageOneNo = rand.nextInt(noImages);
        int randomImageTwoNo;
        int randomImageThreeNo;

        do{
            randomImageTwoNo = rand.nextInt(noImages);
            Log.d("Random Two", String.valueOf(randomImageTwoNo) + " " + selections.getCarMake(randomImageOneNo));
        }while (selections.getCarMake(randomImageOneNo).equals(selections.getCarMake(randomImageTwoNo)));

        do {
            randomImageThreeNo = rand.nextInt(noImages);
            Log.d("Random Three", String.valueOf(randomImageThreeNo) + " " + selections.getCarMake(randomImageThreeNo));
        }while (selections.getCarMake(randomImageOneNo).equals(selections.getCarMake(randomImageTwoNo)) &
                selections.getCarMake(randomImageOneNo).equals(selections.getCarMake(randomImageOneNo)));

        Toast.makeText(this,selections.getCarMake(randomImageOneNo) + " " +
                selections.getCarMake(randomImageTwoNo) + " " + selections.getCarMake(randomImageThreeNo), Toast.LENGTH_LONG ).show();

        int resource_idOne   = getResources().getIdentifier("img_" + randomImageOneNo, "drawable", "com.livecodex.quizzler");
        int resource_idTwo   = getResources().getIdentifier("img_" + randomImageTwoNo, "drawable", "com.livecodex.quizzler");
        int resource_idThree = getResources().getIdentifier("img_" + randomImageThreeNo, "drawable", "com.livecodex.quizzler");

        imageButtonOne.setImageResource(resource_idOne);
        imageButtonTwo.setImageResource(resource_idTwo);
        imageButtonThree.setImageResource(resource_idThree);

    }


}