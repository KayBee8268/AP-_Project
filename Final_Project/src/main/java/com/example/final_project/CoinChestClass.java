package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChestClass extends ChestClass{
    private int coins;

    public CoinChestClass(double x,double y,double xDimension, double yDimension,int data){
        super(x,y,xDimension,yDimension);
        coins=data;
    }

    @Override
    public void awardItem(PlayerClass player) {
        player.addCoins(coins);
    }
}

