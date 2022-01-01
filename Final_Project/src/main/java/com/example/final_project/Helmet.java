package com.example.final_project;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class Helmet extends ObjectClass implements Serializable {
    private int weapon1lvl=0;
    private int weapon2lvl=0;
    private int typeSelected=3;

    public void select(int type){
        typeSelected=type;
    }
    public int getType(){return typeSelected;}

    public int getWeapon1lvl() {
        return weapon1lvl;
    }

    public void setWeapon1lvl(int weapon1lvl) {
        this.weapon1lvl = weapon1lvl;
        ThrowingKnifeClass.setLevel(weapon1lvl);
    }

    public int getWeapon2lvl() {
        return weapon2lvl;
    }

    public void setWeapon2lvl(int weapon2lvl) {
        this.weapon2lvl = weapon2lvl;
        ShurikenClass.setLevel(weapon2lvl);
    }

    //    public void useWeapon(ImageView player, Scene s) {
//        if (typeSelected == 1) {
//            ThrowingKnifeClass knife = new ThrowingKnifeClass(player);
//            knifes.add(knife);
//            ((AnchorPane) s.getRoot()).getChildren().add(knife.getKnife());
//
//        }
//    }

//    public void moveWeapon(Scene s) {
//        for (ThrowingKnifeClass temp : knifes) {
//            if (temp.getDistance() > 0) {
//                temp.move();
//                temp.setDistance(temp.getDistance() - 10);
//            }
////            if(temp.getDistance() <=0){
////                knifes.remove(temp);
////                ((AnchorPane) s.getRoot()).getChildren().remove(temp.getKnife());
////            }
//        }
//    }
}
