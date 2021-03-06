package com.livecodex.quizzler;

public class Selections {

    private String carImages [] = {
            "img_0.png", //Rolls Royce
            "img_1.png", //Rolls Royce
            "img_2.png", //Rolls Royce
            "img_3.png", //Rolls Royce
            "img_4.png", //Rolls Royce
            "img_5.png", //Rolls Royce
            "img_6.png", //Rolls Royce
            "img_7.png", //Rolls Royce
            "img_8.png", //BMW
            "img_9.png", //BMW
            "img_10.png" //Lamborghini
    };

    private String carImageMakes [] = {
            "RollsRoyce",
            "RollsRoyce",
            "RollsRoyce",
            "RollsRoyce",
            "RollsRoyce",
            "RollsRoyce",
            "RollsRoyce",
            "RollsRoyce",
            "BMW",
            "BMW",
            "Lamborghini"
    };

    private String makes [] = {
            "RollsRoyce",
            "Lamborghini",
            "BMW",
            "Toyota",
            "Honda"
    };

    public String[] getCarImages() {
        return carImages;
    }

    public void setCarImages(String[] carImages) {
        this.carImages = carImages;
    }

    public String[] getCarImageMakes() {
        return carImageMakes;
    }

    public void setCarImageMakes(String[] carImageMakes) {
        this.carImageMakes = carImageMakes;
    }

    public String[] getMakes() {
        return makes;
    }

    public void setMakes(String[] makes) {
        this.makes = makes;
    }

    public String getCarMake(int index){
        return carImageMakes[index];
    }
}
