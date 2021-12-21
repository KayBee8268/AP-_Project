package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerClass {

    private ImageView player;


    public PlayerClass(double x, double y, double xDimension, double yDimension) {

        player = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Helmet3.png");
        player.setImage(image);
        player.setX(x);
        player.setY(y);

        player.setFitHeight(yDimension);
        player.setFitWidth(xDimension);
    }

    public ImageView getImageview() {
        return player;
    }

}