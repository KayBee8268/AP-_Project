package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerClass {

    private ImageView player;
    private int val;
    private boolean isDead;
    private boolean isRessurected;
    private int coins;

    public void jump(){
        if(val>0)
        {
            player.setY(player.getY()-1);
            val++;
        }
        if(val==200) val=-1;
    }

    public void setVal(int val){
        this.val=val;
    }

    public void drop(){
        if(val<0) {
            if (!PlatformClass.checkPlatform(player)){
                player.setY(player.getY() + 1);
            }
            if(PlatformClass.checkPlatform(player) || ObstacleClass.checkFallingPlatform(player)) val=1;

        }
        if(player.getY()==720){
            System.out.println("Player Dead");
            isDead=true;
        }

        if(player.getY()<720 && isDead){
            player.setY(player.getY() + 5);
        }

    }

    public void addCoins(int data){
        coins+=data;
    }


    public PlayerClass(double x, double y, double xDimension, double yDimension) {

        player = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Helmet3.png");
        player.setImage(image);
        player.setX(x);
        player.setY(y);

        player.setFitHeight(yDimension);
        player.setFitWidth(xDimension);
        val=1;
        isDead=false;
        isRessurected=false;
        coins=0;
    }

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

    public ImageView getImageview() {
        return player;
    }

}