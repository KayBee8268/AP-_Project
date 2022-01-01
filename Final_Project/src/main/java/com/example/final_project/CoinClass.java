package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class CoinClass extends ObjectClass implements Serializable {
//    private PositionClass position;
//
//    public CoinClass(double x,double y,double xdimension, double ydimension){
//        position=new PositionClass(x,y,xdimension,ydimension);
//    }

//    private ImageView coin;


    public CoinClass(double xCoordinate,double yCoordinate,double xDimension, double yDimension){
//        position=new PositionClass(x,y,xDimension,yDimension);

        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Coin.png");
        super.setObjectImage(image);
        super.getObjectImage().setX(xCoordinate);
        super.getObjectImage().setY(yCoordinate);
        super.getObjectImage().setFitHeight(yDimension);
        super.getObjectImage().setFitWidth(xDimension);
    }

//    public ImageView getImageView() {
//        return coin;
//    }

}
