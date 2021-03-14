package com.livecodex.quizzler.utils;

public class Selections {

    private static String makes [] = {
            "Alfa Romeo",
            "Aston Martin",
            "Audi",
            "BMW",
            "Cadillac",
            "Chevrolet",
            "Ferrari",
            "Lamborghini",
            "Mercedes",
            "Porsche",
            "Rolls Royce",
            "Volkswagen"
    };

    public static String[] getMakes(){
        return makes;
    }

    public static String getSelectedMake(int index) {
        return makes[index];
    }

    public void setMakes(String[] makes) {
        this.makes = makes;
    }

    public static int getSize(){
        return makes.length;
    }

}
