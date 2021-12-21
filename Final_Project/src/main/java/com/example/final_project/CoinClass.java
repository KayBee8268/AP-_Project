package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinClass {
//    private PositionClass position;
//
//    public CoinClass(double x,double y,double xdimension, double ydimension){
//        position=new PositionClass(x,y,xdimension,ydimension);
//    }

    private ImageView coin;


    public CoinClass(double x,double y,double xDimension, double yDimension){
//        position=new PositionClass(x,y,xDimension,yDimension);

        coin = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Coin.png");
        coin.setImage(image);
        coin.setX(x);
        coin.setY(y);

        coin.setFitHeight(yDimension);
        coin.setFitWidth(xDimension);
    }

    public ImageView getImageView() {
        return coin;
    }

}
