package com.example.final_project;

import java.io.Serializable;

public class WeaponChestClass extends ChestClass implements Serializable {
    private int weaponType;

    public int getWeaponType() {
        return weaponType;
    }

    public WeaponChestClass(double x, double y, double xDimension, double yDimension, int weapon){
        super(x,y,xDimension,yDimension);
        weaponType=weapon;
    }

    @Override
    public void awardItem(PlayerClass player) {
        player.obtainWeapon(weaponType);
    }
}
