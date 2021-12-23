package com.example.final_project;

public class WeaponChestClass extends ChestClass{
    private int weaponType;

    public WeaponChestClass(double x,double y,double xDimension, double yDimension,int weapon){
        super(x,y,xDimension,yDimension);
        weaponType=weapon;
    }

    @Override
    public void awardItem(PlayerClass player) {

    }
}
