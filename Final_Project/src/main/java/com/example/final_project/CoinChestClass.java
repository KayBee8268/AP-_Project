package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChestClass {
//    private PositionClass position;
//
//    public CoinChestClass(double x,double y,double xdimension, double ydimension){
//        position=new PositionClass(x,y,xdimension,ydimension);
//    }
    private ImageView chest;

    public CoinChestClass(double x,double y,double xDimension, double yDimension){
//        position=new PositionClass(x,y,xDimension,yDimension);

        chest = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestClosed.png");
        chest.setImage(image);
        chest.setX(x);
        chest.setY(y);

        chest.setFitHeight(yDimension);
        chest.setFitWidth(xDimension);
    }

    public ImageView getImageView() {
        return chest;
    }
}

