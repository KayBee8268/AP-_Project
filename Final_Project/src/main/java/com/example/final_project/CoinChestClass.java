package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class CoinChestClass extends ChestClass implements Serializable {
    private int coins;

    public CoinChestClass(double x,double y,double xDimension, double yDimension,int data){
        super(x,y,xDimension,yDimension);
        coins=data;
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public void awardItem(PlayerClass player) {
        player.setCoins(player.getCoins()+coins);
    }
}

