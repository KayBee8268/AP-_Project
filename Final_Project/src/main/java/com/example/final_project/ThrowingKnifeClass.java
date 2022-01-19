package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class ThrowingKnifeClass implements Weapon, Serializable {
    private static int level=0;
    private int distance;
    private static int damage=50;

    private ImageView knife;

    public static void setLevel(int l){
        level=l;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDistance(){
        return distance;
    }

    public ThrowingKnifeClass(ImageView player){
        knife = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/WeaponKnife.png");
        knife.setImage(image);
        knife.setFitHeight(10);
        knife.setFitWidth(40);
        knife.setY(player.getY()+(player.getFitHeight())/2);
        knife.setX(player.getX());
        if(level==1)
            distance=400;
        if(level==2)
            distance=600;
    }

    @Override
    public ImageView getImageView(){
        return knife;
    }

    @Override
    public void move(){
        if(distance>0){
            knife.setX(knife.getX()+10);
            distance-=10;
        }
    }
}
