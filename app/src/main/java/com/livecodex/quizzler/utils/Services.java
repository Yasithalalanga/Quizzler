package com.livecodex.quizzler.utils;

import android.content.Context;
import android.widget.ImageView;

import java.util.Random;

public class Services {

    public static String randomizedImage(ImageView randomImageView, Context context){
        Random rand = new Random();
        int randomImageNo = rand.nextInt(5) + 1;
        int randomMakeIndex = rand.nextInt(Selections.getSize());
        String randomCarMake = Selections.getSelectedMake(randomMakeIndex);

        String imageUri = randomCarMake.replace(" ", "").toLowerCase() + "_" + randomImageNo;
        int resource_id = context.getResources().getIdentifier(imageUri,"drawable", context.getApplicationInfo().packageName);

        randomImageView.setImageResource(resource_id);
        return randomCarMake;
    }

}
