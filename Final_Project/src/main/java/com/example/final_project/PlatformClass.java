package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlatformClass {
//  private final PositionClass position;
    private ImageView platform;


    public PlatformClass(double x,double y,double xDimension, double yDimension){
//        position=new PositionClass(x,y,xDimension,yDimension);

        platform = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands1.png");
        platform.setImage(image);
        platform.setX(x);
        platform.setY(y);

        platform.setFitHeight(yDimension);
        platform.setFitWidth(xDimension);
    }

    public ImageView getPlatform() {
        return platform;
    }
}
