package com.example.final_project;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class PlayerClass extends ObjectClass implements Serializable {

//    private ImageView player;
    private String playerName;
    private int g;
    private int val;
    private boolean isDead;
    private boolean isRessurected;
    private int coins;
    private Helmet helmet;
    private int totalScore;
    private int totalGamesPlayed;

    public int getWeapon1lvl() {
        return helmet.getWeapon1lvl();
    }

    public int getWeapon2lvl() {
        return helmet.getWeapon2lvl();
    }
    public void selectWeapon(int type){
        if(helmet.getWeapon1lvl()>0 && type ==1){
            helmet.select(1);
        }

        else if(helmet.getWeapon2lvl()>0 && type ==2){
            helmet.select(2);
        }

        else if(type==3){
            helmet.select(3);
        }
    }

    public void obtainWeapon(int type){
        if(type==1 && helmet.getWeapon1lvl()<2 ) {
            helmet.setWeapon1lvl(helmet.getWeapon1lvl() + 1);
        }
        if(type==2 && helmet.getWeapon2lvl()<2) {
            helmet.setWeapon2lvl(helmet.getWeapon2lvl()+1);
        }
    }

    public int getType(){
        return helmet.getType();
    }

    public void jump(){
        if(val>0)
        {   int temp=(200-val)/20+1;
            super.getObjectImage().setY(super.getObjectImage().getY()-temp);
            val+=temp;
        }
        if(val>=200) {
            val=-1;
            g=30;
        }
    }

    public void setVal(int val){
        this.val=val;
    }

    public void drop(){
        if(val<0) {
            if (!PlatformClass.checkPlatform(super.getObjectImage(),g)){
                int temp=g/40+1;
                super.getObjectImage().setY(super.getObjectImage().getY() + temp);

                if(g<360) g+=temp;
            }
            if(PlatformClass.checkPlatform(super.getObjectImage(),g) || ObstacleClass.checkFallingPlatform(super.getObjectImage(),g)) val=1;

        }
//        if(super.getObjectImage().getY()==720){
//            System.out.println("Player Dead");
//            isDead=true;
//        }

        if(super.getObjectImage().getY()<720 && isDead){
            super.getObjectImage().setY(super.getObjectImage().getY() + 5);
        }

    }


    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCoins() {
        return coins;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public PlayerClass(double xCoordinate, double yCoordinate, double xDimension, double yDimension) {

//        player = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Helmet3.png");
        super.setObjectImage(image);
        super.getObjectImage().setX(xCoordinate);
        super.getObjectImage().setY(yCoordinate);
        super.getObjectImage().setFitHeight(yDimension);
        super.getObjectImage().setFitWidth(xDimension);
        val=1;
        isDead=false;
        isRessurected=false;
        coins=0;
        helmet=new Helmet();
        totalScore=0;
    }

//    public void useWeapon(Scene s){
//        helmet.useWeapon(player,s);
//    }

//    public void moveWeapon(Scene s){
//        helmet.moveWeapon(s);
//    }


    public boolean getDead(){
        return this.isDead;
    }
    public boolean getRessurected(){
        return this.isRessurected;
    }

    public void setDead(boolean data){
        this.isDead=data;
    }
    public void setRessurected(boolean data){
        this.isRessurected=data;
    }

//    public ImageView getImageview() {
//        return player;
//    }

}