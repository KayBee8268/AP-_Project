package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShurikenClass implements Weapon{
    private static int level=0;
    private int distance;
    private static int damage=50;

    private ImageView shuriken;

    public static void setLevel(int l){
        level=l;
        if(level>1) damage=100;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDistance(){
        return distance;
    }

    public ShurikenClass(ImageView player){
        shuriken = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/WeaponShuriken.png");
        shuriken.setImage(image);
        shuriken.setFitHeight(30);
        shuriken.setFitWidth(30);
        shuriken.setY(player.getY()+(player.getFitHeight())/2);
        shuriken.setX(player.getX());
        distance=400;
    }

    @Override
    public ImageView getImageView(){
        return shuriken;
    }

    @Override
    public void move(){
        if(distance>0){
            shuriken.setX(shuriken.getX()+10);
            distance-=10;
        }
    }
}
