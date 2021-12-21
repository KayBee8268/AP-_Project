package com.example.final_project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class OrcClass {
    //protected PositionClass position;

    @FXML

    protected ImageView orc;
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

    }

    public ImageView getImageView(){
        return orc;
    }
}
