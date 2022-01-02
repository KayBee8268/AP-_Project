package com.example.final_project;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class OrcClass extends ItemsClass implements Serializable {
    //protected PositionClass position;
    private int val;
    private int HP;
    private boolean isDead;
//    @FXML
//    protected ImageView orc;

    public void jump(){
        if(val>0 && !isDead){
            super.getObjectImage().setY(super.getObjectImage().getY()-2);
            val+=2;
            if(val>=250) val=-1;
        }
    }

    public void drop(){
        if(val<0 && !isDead) {
            if (!PlatformClass.checkPlatform(super.getObjectImage(),4)){
                super.getObjectImage().setY(super.getObjectImage().getY() + 4);
            }
            if(PlatformClass.checkPlatform(super.getObjectImage(),4)) val=1;

        }
        if(super.getObjectImage().getY()>=720 && !isDead){
            isDead=true;
            Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/DeadOrc.jpg");
            super.getObjectImage().setImage(image);
        }

        if(super.getObjectImage().getY()<720 && isDead){
            super.getObjectImage().setY(super.getObjectImage().getY() + 5);
        }

    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
        if(HP<=0) {
            isDead=true;
            Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/DeadOrc.jpg");
            super.getObjectImage().setImage(image);
        }
    }

    public OrcClass(double xCoordinate , double yCoordinate, double xDimension, double yDimension, String filepath){
        //this.position = new PositionClass(xCoordinate,yCoordinate,xDimension,yDimension);
//        orc = new ImageView();
        Image image = new Image(filepath);
        super.setObjectImage(image);
        super.getObjectImage().setX(xCoordinate);
        super.getObjectImage().setY(yCoordinate);
        super.getObjectImage().setPreserveRatio(true);
        super.getObjectImage().setFitHeight(yDimension);
        super.getObjectImage().setFitWidth(xDimension);
        val=1;
        isDead=false;
        HP=100;

    }

    public void setisDead(boolean data){
        this.isDead=data;
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/DeadOrc.jpg");
        super.getObjectImage().setImage(image);
    }

    public boolean getisDead(){
        return this.isDead;
    }



//    public ImageView getImageView(){
//        return orc;
//    }
}
