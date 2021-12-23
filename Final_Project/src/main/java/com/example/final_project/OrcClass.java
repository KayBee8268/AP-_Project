package com.example.final_project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class OrcClass {
    //protected PositionClass position;
    private int val;
    private boolean isDead;
    @FXML
    protected ImageView orc;

    public void jump(){
        if(val>0 && !isDead){
            orc.setY(orc.getY()-1);
            val++;
            if(val==300) val=-1;
        }
    }

    public void drop(){
        if(val<0 && !isDead) {
            if (!PlatformClass.checkPlatform(orc)){
                orc.setY(orc.getY() + 1);
            }
            if(PlatformClass.checkPlatform(orc)) val=1;

        }
        if(orc.getY()==720 && !isDead){
            System.out.println("Orc Dead");
            isDead=true;
        }

        if(orc.getY()<720 && isDead){
            orc.setY(orc.getY() + 5);
        }

    }


    public OrcClass(double xCoordinate ,double yCoordinate,double xDimension,double yDimension,String filepath){
        //this.position = new PositionClass(xCoordinate,yCoordinate,xDimension,yDimension);
        orc = new ImageView();
        Image image = new Image(filepath);
        orc.setImage(image);
        orc.setX(xCoordinate);
        orc.setY(yCoordinate);
        orc.setPreserveRatio(true);
        orc.setFitHeight(yDimension);
        orc.setFitWidth(xDimension);
        val=1;
        isDead=false;

    }

    public void setisDead(boolean data){
        this.isDead=data;
    }

    public boolean getisDead(){
        return this.isDead;
    }



    public ImageView getImageView(){
        return orc;
    }
}
